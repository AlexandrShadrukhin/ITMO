package Commands;

import Collection.Collection;
import Data.Worker;
import WorkModules.Answer;
import WorkModules.Task;

public class CommandClear extends Command {
    private Collection<Worker> collection;

    public CommandClear(Collection<Worker> collection) {
        this.collection = collection;
    }

    @Override
    public Answer commandDo(String key, Task task) {
        Answer answer = new Answer();
        try {

            collection.getVector().clear();
            return answer;
        } catch (RuntimeException e) {
            throw e;
        }
    }
}