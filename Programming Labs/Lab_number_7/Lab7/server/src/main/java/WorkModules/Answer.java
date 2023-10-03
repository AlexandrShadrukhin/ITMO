package WorkModules;

import java.io.Serializable;

public class Answer implements Serializable {
    private String result="Success\n---\n";

    public Answer(String result) {
        this.result = result;
    }

    public Answer() {
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }
}
