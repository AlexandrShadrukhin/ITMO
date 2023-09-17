package Commands;

import Collection.Collection;
import Data.Worker;
import WorkModules.Answer;
import WorkModules.Task;

public class CommandAdd  extends Command{
    private Collection<Worker> collection;
    public CommandAdd(Collection<Worker> collection) {
        this.collection=collection;
    }

    @Override
    public Answer commandDo(String key, Task task) {
        Answer answer= new Answer();
        try {
            collection.getVector().add(task.worker);
            return answer;
        }
        catch (RuntimeException e){
            throw e;
        }
    }
}
