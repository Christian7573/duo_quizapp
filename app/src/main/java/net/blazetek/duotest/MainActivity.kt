package net.blazetek.duotest

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.microsoft.fluentui.popupmenu.PopupMenu
import com.microsoft.fluentui.popupmenu.PopupMenuItem
import com.microsoft.fluentui.snackbar.Snackbar
import com.microsoft.fluentui.widget.Button

private val key_id: String = "selected_quiz_id";

class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {
            selected_quiz = savedInstanceState.getInt(key_id);
        }
    }
    var selected_quiz: Int? = null;

    override fun onStart() {
        super.onStart();
        if (selected_quiz != null) (supportFragmentManager.findFragmentByTag("quiz_picker") as QuizPicker).selected_quiz = selected_quiz!!;
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(key_id, (supportFragmentManager.findFragmentByTag("quiz_picker") as QuizPicker).selected_quiz);
        super.onSaveInstanceState(outState);
    }
}

class QuizPicker : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            println(savedInstanceState!!.getInt(key_id));
        } else println("Yite");
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_quiz_picker, container, false)
        var button: Button = view.findViewById(R.id.quiz_picker_button);
        button.setOnClickListener { view -> show_menu(view); };

        var launch_button: Button = view.findViewById(R.id.quiz_picker_start);
        launch_button.setOnClickListener {
            if (selected_quiz < 0) {
                Snackbar.make(view, "You didn't select a quiz", Snackbar.LENGTH_LONG)
                    .setStyle(Snackbar.Style.ANNOUNCEMENT)
                    .show();
            } else {
                println(Intent.ACTION_RUN);
                println(Uri.parse("quiz://" + selected_quiz));
                requireContext().startActivity(Intent(Intent.ACTION_RUN, Uri.parse("quiz://" + selected_quiz)));
                //startActivity(Intent(context, QuizActivity::class.java));
            }
        };

        if (savedInstanceState != null) {
            _selected_quiz = savedInstanceState.getInt(key_id);
        }

        return view;
    }

    override fun onStart() {
        super.onStart();
        selected_quiz = _selected_quiz;
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(key_id, selected_quiz);
    }

    public val menu_items: ArrayList<PopupMenuItem>;
    init {
        var menu_items = ArrayList<PopupMenuItem>();
        for ((i, quiz) in quizi.withIndex()) menu_items.add(PopupMenuItem(i, quiz.name));
        this.menu_items = menu_items;
    }

    private var _selected_quiz: Int = -1;
    public var selected_quiz
        get() = _selected_quiz;
        set(value) {
            _selected_quiz = value;
            var button: Button = requireView().findViewById(R.id.quiz_picker_button);
            if (_selected_quiz < 0) {
                button.text = "Select quiz";
            } else {
                button.text = quizi[selected_quiz].name;
            }
        }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment QuizPicker.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            QuizPicker().apply {
                arguments = Bundle().apply {
                    putInt(key_id, selected_quiz);
                }
            }
    }

    public fun show_menu(view: View) {
        val click_listener = object : PopupMenuItem.OnClickListener {
            override fun onPopupMenuItemClicked(popupMenuItem: PopupMenuItem) {
                selected_quiz = popupMenuItem.id;
            }
        };
        var popup_menu = PopupMenu(this.requireContext(), view, menu_items, PopupMenu.ItemCheckableBehavior.NONE);
        //popup_menu.setOnItemClickListener({_, _, id, _unknown -> println("Yello"); println(id); });
        popup_menu.onItemClickListener = click_listener;
        popup_menu.show();
    }
}