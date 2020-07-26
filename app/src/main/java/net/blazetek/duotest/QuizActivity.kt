package net.blazetek.duotest

import android.os.Bundle
import android.os.PersistableBundle
import android.view.GestureDetector
import android.view.MotionEvent
import androidx.fragment.app.FragmentActivity
import com.microsoft.fluentui.appbarlayout.AppBarLayout

class QuizActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        println("Not okee dokee");
        //val appbar: AppBarLayout = findViewById(R.id.quiz_appbar);
        gesture_detector = GestureDetector(this, object : GestureDetector.SimpleOnGestureListener() {
            override fun onFling(
                e1: MotionEvent?,
                e2: MotionEvent?,
                velocityX: Float,
                velocityY: Float
            ): Boolean {
                if (velocityX >= 30) {
                    println("Yite==========================================================");
                    return true;
                } else return false;
            }
        });
    }

    var gesture_detector: GestureDetector? = null;
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        System.out.println("Yite-ith");
        return super.onTouchEvent(event);
    }

    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {
        val returnith = super.dispatchTouchEvent(event);
        println(returnith);
        gesture_detector!!.onTouchEvent(event);
        return returnith;
    }
}