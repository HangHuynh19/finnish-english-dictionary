package fi.hanghuynh.finnish_englishslangdictionary;

import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TakeAQuizUI {
    private List<String> arrayQuestion;
    private List<String> arrayAnswer;

    public TakeAQuizUI(List<String> arrayQuestion, List<String> arrayAnswer) {
        this.arrayQuestion = arrayQuestion;
        this.arrayAnswer = arrayAnswer;
    }

    public ArrayList<String> generateQuestionAndCorrectAnswer(List<String> arrayQuestion, List<String> arrayAnswer) {
        int index = (int)(Math.random() * (arrayQuestion.size()));
        ArrayList<String> questionAndAnswer = new ArrayList<>();
        String questionWord = arrayQuestion.get(index);
        String correctAnswer = arrayAnswer.get(index);

        questionAndAnswer.add(questionWord);
        questionAndAnswer.add(correctAnswer);

        return questionAndAnswer;
    }

    public ArrayList<String> generateQuizOptions(String correctAnswer) {
        ArrayList<String> options = new ArrayList<>();
        String dummyOption;

        options.add(correctAnswer);

        while (options.size() < 3) {
            dummyOption = arrayAnswer.get((int)(Math.random() * (arrayAnswer.size())));

            if (options.contains(dummyOption) || dummyOption.equals(correctAnswer)) {
                continue;
            }
            options.add(dummyOption);
        }

        Collections.shuffle(options);

        return options;
    }

    public ArrayList<String> generateQuiz() {
        ArrayList<String> questionAndAnswer = generateQuestionAndCorrectAnswer(arrayQuestion, arrayAnswer);
        String question = questionAndAnswer.get(0);
        String correctAnswer = questionAndAnswer.get(1);
        ArrayList<String> options = generateQuizOptions(correctAnswer);
        ArrayList<String> quizInfo = new ArrayList<>();

        quizInfo.add(question);
        quizInfo.add(correctAnswer);
        quizInfo.addAll(options);

        return quizInfo;
    }

}
