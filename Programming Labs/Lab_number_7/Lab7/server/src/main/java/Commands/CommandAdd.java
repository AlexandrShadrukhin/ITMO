package Commands;

import Collection.Collection;
import Data.Worker;
import WorkModules.Answer;
import WorkModules.Task;

import java.util.ArrayList;
import java.util.Collections;

public class CommandAdd  extends Command{
    private Collection<Worker> collection;
    public CommandAdd(Collection<Worker> collection) {
        this.collection=collection;
    }

    @Override
    public Answer commandDo(String key, Task task) {
        Answer answer= new Answer();
        try {
            ArrayList<Integer> listOfId= new ArrayList<>();
            listOfId.addAll(collection.getVector().stream().map(it -> it.getId()).toList());
            listOfId.add(0);
            task.worker.setId(Collections.max(listOfId)+1);
            collection.getVector().add(task.worker);
            return answer;
        }
        catch (RuntimeException e){
            throw e;
        }
    }
}
