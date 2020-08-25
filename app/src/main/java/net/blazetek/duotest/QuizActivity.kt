package net.blazetek.duotest

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.widget.TextView
import android.widget.Toast
import android.widget.Toolbar
import androidx.fragment.app.FragmentActivity
import com.microsoft.fluentui.appbarlayout.AppBarLayout

class QuizActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
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

    lateinit var quiz: Quiz;
    var selected_question = 0;
    var selected_answers = HashMap<Int, Int>();

    override fun onStart() {
        super.onStart();
        intent.data!!.host!!.toInt();
        val my_quiz: Quiz? = quizi.get(intent.data!!.host!!.toInt());
        if (my_quiz == null) {
            Toast.makeText(this, "Unable to open quiz: Invalid URL", Toast.LENGTH_SHORT);
            startActivity(Intent(Intent.ACTION_MAIN));
            return;
        }
        quiz = my_quiz;
        findViewById<Toolbar>(R.id.quiz_topbar).title = quiz.name;
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