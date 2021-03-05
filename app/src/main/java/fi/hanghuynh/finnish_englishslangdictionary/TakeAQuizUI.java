package fi.hanghuynh.finnish_englishslangdictionary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** Modeling how the quiz questions and their choices will appear to the user **/
public class TakeAQuizUI {
    private List<String> arrayQuestion;
    private List<String> arrayAnswer;

    /** Quiz UI constructor
     * @param arrayQuestion List
     * @param arrayAnswer List **/
    public TakeAQuizUI(List<String> arrayQuestion, List<String> arrayAnswer) {
        this.arrayQuestion = arrayQuestion;
        this.arrayAnswer = arrayAnswer;
    }

    /** Randomize Finnish word from database for the question and get the correct answer
     * @param arrayQuestion List
     * @param arrayAnswer List
     * @return ArrayList containing question word and the correct english translation  **/
    public ArrayList<String> generateQuestionAndCorrectAnswer(List<String> arrayQuestion, List<String> arrayAnswer) {
        int index = (int)(Math.random() * (arrayQuestion.size()));
        ArrayList<String> questionAndAnswer = new ArrayList<>();
        String questionWord = arrayQuestion.get(index);
        String correctAnswer = arrayAnswer.get(index);

        questionAndAnswer.add(questionWord);
        questionAndAnswer.add(correctAnswer);

        return questionAndAnswer;
    }

    /** Randomize quiz options from array list of english word in the database
     * @param correctAnswer String
     * @return ArrayList containing correct answer and 2 random options **/
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

    /** Combining quiz question and its multiple choices
     * @return ArrayList containing question word, the correct answer and 2 random options**/
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
