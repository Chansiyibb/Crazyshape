package tw.edu.pu.s1080315.crazyshape

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_game.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        Toast.makeText(this, "作者： (曾品翰)", Toast.LENGTH_SHORT).show()

        imageButton.setOnLongClickListener{
            var it = Intent(this, GameActivity::class.java);
            startActivity(it)
            true
        }

    }




}