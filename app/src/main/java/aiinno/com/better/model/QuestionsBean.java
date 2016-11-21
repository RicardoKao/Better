package aiinno.com.better.model;

/**
 * Created by lbk on 2016/11/21.
 */

public class QuestionsBean {
    private String question;

    private boolean focus;

    public boolean isFocus() {
        return focus;
    }

    public void setFocus(boolean focus) {
        this.focus = focus;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
