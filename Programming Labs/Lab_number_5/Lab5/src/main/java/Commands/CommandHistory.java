package Commands;

import WorkModules.Answer;

public class CommandHistory  extends Command{
    @Override
    public Answer commandDo(String key) {
        Answer answer= new Answer();
        answer.setResult(key);
        return answer;
    }
}
