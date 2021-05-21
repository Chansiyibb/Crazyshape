package tw.edu.pu.s1080315.crazyshape

import android.content.Intent
import android.graphics.Picture
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import kotlinx.android.synthetic.main.activity_game.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

@GlideModule
public final class MyAppGlideModule : AppGlideModule()





class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imgNext.setOnClickListener{

            var PictureNo:Int = 0  //目前顯示第幾張圖
            var TotalPictures:Int = 5 //總共幾張圖片(假設僅顯示pu0-pu3)
            var num = (Math.random()*TotalPictures).toInt()

            PictureNo = num

            fun ShowPicture(){
                when (PictureNo ){
                    0 -> imgNext.setImageResource(R.drawable.circle)
                    1 -> imgNext.setImageResource(R.drawable.square)
                    2 -> imgNext.setImageResource(R.drawable.star)
                    3 -> imgNext.setImageResource(R.drawable.triangle)
                }
            }
            ShowPicture()
        }

        val img: ImageView = findViewById(R.id.imgTitle)
        GlideApp.with(this)
            .load(R.drawable.cover)
            .override(800, 600)
            .into(img)

        Toast.makeText(this, "作者： (曾品翰)", Toast.LENGTH_SHORT).show()

        imgNext.setOnLongClickListener{
            var it = Intent(this, GameActivity::class.java);
            startActivity(it)
            true
        }
    }

}