package Commands;

import Collection.Collection;
import Data.Worker;
import WorkModules.Answer;
import WorkModules.Task;

public class CommandRemoveLast extends Command {
    private Collection<Worker> collection;

    public CommandRemoveLast(Collection<Worker> collection) {
        this.collection = collection;
    }

    @Override
    public Answer commandDo(String key, Task task) {
        Answer answer = new Answer();
        try {
            if (task.login == collection.getVector().lastElement().getOwner()) {
                collection.getVector().remove(collection.getVector().size() - 1);
            }
            return answer;
        } catch (RuntimeException e) {
            throw e;
        }
    }
}
