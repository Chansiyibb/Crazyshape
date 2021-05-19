package tw.edu.pu.s1080315.crazyshape

import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_game.*
import org.tensorflow.lite.support.image.TensorImage
import tw.edu.pu.s1080315.crazyshape.ml.Shapes

class GameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        btn.setOnClickListener(object: View.OnClickListener{
            override fun onClick(p0: View?) {
                handv.path.reset()
                handv.invalidate()
            }
        })

        btn1.setOnClickListener(object: View.OnClickListener{
            override fun onClick(p0: View?) {
                finish()
            }
        })

        handv.setOnTouchListener(object: View.OnTouchListener{
            override fun onTouch(p0: View?, event: MotionEvent): Boolean {
                var xPos = event.getX()
                var yPos = event.getY()
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> handv.path.moveTo(xPos, yPos)
                    MotionEvent.ACTION_MOVE -> handv.path.lineTo(xPos, yPos)
                    MotionEvent.ACTION_UP -> {
                        //將handv轉成Bitmap
                        val b = Bitmap.createBitmap(handv.measuredWidth, handv.measuredHeight,
                            Bitmap.Config.ARGB_8888)
                        val c = Canvas(b)
                        handv.draw(c)
                        classifyDrawing(b)
                    }

                }
                handv.invalidate()
                return true
            }
        })
    }

    fun classifyDrawing(bitmap : Bitmap) {
        val model = Shapes.newInstance(this)


        val image = TensorImage.fromBitmap(bitmap)
        /*
        //
        val outputs = model.process(image)
        val probability = outputs.probabilityAsCategoryList
        */
        // Releases model resources if no longer used.

        val outputs = model.process(image)
            .probabilityAsCategoryList.apply {
                sortByDescending { it.score } // 排序，高匹配率優先
            }.take(2)  //取最高的1個

        var Result:String = ""
        for (output in outputs){
            when (output.label) {
                "circle" -> Result += "圓形"
                "square" -> Result += "正方形"
                "star" -> Result += "星形"
                "triangle" -> Result += "三角形"
            }
            Result += ": " + String.format("%.1f%%", output.score * 100.0f) + "; "
        }




        model.close()
        Toast.makeText(this, Result, Toast.LENGTH_SHORT).show()
    }

}