package Commands;

import Collection.Collection;
import Data.Worker;
import WorkModules.Answer;
import WorkModules.Task;

import java.util.List;
import java.util.Vector;

public class CommandRemoveById  extends Command {
    private Collection<Worker> collection;

    public CommandRemoveById(Collection<Worker> collection) {
        this.collection = collection;
    }

    @Override
    public Answer commandDo(String key, Task task) {
        Answer answer = new Answer();
        try {
            List<Worker> list= collection.getVector().stream().filter(it -> it.getId() != Integer.parseInt(key)).toList();
            collection.setVector(new Vector<>(list.size()));
            list.forEach(it-> collection.getVector().add(it));
            return answer;
        } catch (RuntimeException e) {
            throw e;
        }
    }
}
