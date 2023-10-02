package Commands;

import Collection.Collection;
import Data.Worker;
import WorkModules.Answer;
import WorkModules.Task;

import java.time.LocalDateTime;

public class CommandInfo extends Command {
    private Collection<Worker> collection;

    public CommandInfo(Collection<Worker> collection) {
        this.collection = collection;
    }

    @Override
    public Answer commandDo(String key, Task task) {
        Answer answer = new Answer();
        try {
            answer.setResult("Collection size: "+collection.getVector().size()+"\n"+"Type of collection: vector"+"\n"+ LocalDateTime.now());
            return answer;
        } catch (RuntimeException e) {
            throw e;
        }
    }
}
