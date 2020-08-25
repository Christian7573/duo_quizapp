package net.blazetek.duotest

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [QuestionList.newInstance] factory method to
 * create an instance of this fragment.
 */
class QuestionList : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_question_list, container, false) as RecyclerView
        view.setHasFixedSize(true);
        view.layoutManager = LinearLayoutManager(context)
        view.adapter = QuestionListAdapter(activity as QuizActivity)
        return view
    }
}

class QuestionListAdapter(activity: QuizActivity) : RecyclerView.Adapter<QuestionListAdapterViewHolder>()  {
    val activity = activity;
    var question_number = 0;
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): QuestionListAdapterViewHolder {
        val view = LayoutInflater.from(activity).inflate(R.layout.fragment_question_list_item, parent, false);
        view.findViewById<TextView>(R.id.question_list_item_header).text = question_number.toString() + ": " + activity.quiz.questions[question_number].question;
        view.findViewById<TextView>(R.id.question_list_item_content).text = "Unanswered"; //quiz.questions.get(question_number).question;
        return QuestionListAdapterViewHolder(view);
    }

    override fun onBindViewHolder(holder: QuestionListAdapterViewHolder, position: Int) {
        //lmao no
    }
    override fun getItemCount() = activity.quiz.questions.count();
}
class QuestionListAdapterViewHolder(layout: View) : RecyclerView.ViewHolder(layout)