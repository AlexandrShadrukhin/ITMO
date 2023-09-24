package Commands;

import Collection.Collection;
import Data.Worker;
import WorkModules.Answer;
import WorkModules.Task;

import java.util.Vector;

public class CommandClear extends Command {
    private Collection<Worker> collection;

    public CommandClear(Collection<Worker> collection) {
        this.collection = collection;
    }

    @Override
    public Answer commandDo(String key, Task task) {
        Answer answer = new Answer();
        Vector<Worker> list = new Vector<>();
        try {

            collection.getVector().forEach(it -> {
                        if (it.getOwner() != task.login) {
                            list.add(it);
                        }
                    }
            );
            collection.setVector(list);
            return answer;
        } catch (RuntimeException e) {
            throw e;
        }
    }
}