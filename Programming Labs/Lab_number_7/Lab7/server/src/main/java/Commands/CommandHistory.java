package Commands;

import WorkModules.Answer;
import WorkModules.Task;

public class CommandHistory  extends Command{
    @Override
    public Answer commandDo(String key, Task task) {
        Answer answer= new Answer();
        answer.setResult(key);
        return answer;
    }
}
