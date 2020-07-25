package net.blazetek.duotest

import android.os.Bundle
import android.os.PersistableBundle
import androidx.fragment.app.FragmentActivity
import com.microsoft.fluentui.appbarlayout.AppBarLayout

class QuizActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        println("Not okee dokee");
        //val appbar: AppBarLayout = findViewById(R.id.quiz_appbar);

    }
}