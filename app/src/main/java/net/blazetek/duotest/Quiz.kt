package net.blazetek.duotest

public val quizi = listOf(
    Quiz("The Stupid Quiz", listOf(
        QuizQuestion("What is 9 + 10?", listOf("19", "21"), 1),
        QuizQuestion("Is a hotdog a sandwhich?", listOf("Yes", "No"), 0)
    )),
    Quiz("The Second Stupid Quiz", listOf(
        QuizQuestion("Pick option 5", listOf("Option 1", "Option 2", "Option 3", "Option 4", "Option 5", "Option 6"), 4)
    ))
);

public class Quiz(name: String, questions: List<QuizQuestion>) {
    val name: String = name;
    val questions: List<QuizQuestion> = java.util.Collections.unmodifiableList(ArrayList(questions));
}

public class QuizQuestion(question: String, answers: List<String>, correct_answer_index: Int) {
    val question: String = question;
    val answers: List<String> = java.util.Collections.unmodifiableList(ArrayList(answers));
    val correct_answer_index: Int = correct_answer_index;
}