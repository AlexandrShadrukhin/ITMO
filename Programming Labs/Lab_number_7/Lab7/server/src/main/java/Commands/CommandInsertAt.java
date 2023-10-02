package Commands;

import Collection.Collection;
import Data.Worker;
import WorkModules.Answer;
import WorkModules.Task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

public class CommandInsertAt extends Command {
    private Collection<Worker> collection;
    public CommandInsertAt(Collection<Worker> collection) {
        this.collection = collection;
    }

    @Override
    public Answer commandDo(String key, Task task) {
        Answer answer = new Answer();
        ArrayList<Integer> listOfId= new ArrayList<>();
        listOfId.add(0);
        collection.getVector().forEach(it-> listOfId.add(it.getId()));
        task.worker.setId(Collections.max(listOfId)+1);
        try {
            if (collection.getVector().size()<Integer.parseInt(key)+1){
                Vector<Worker> vector= new Vector<>(collection.getVector().size()+1);
                vector.addAll(collection.getVector());
                collection.setVector(vector);
                collection.getVector().add(task.worker);
            }
            else {
                collection.getVector().add(task.worker);
            }
            return answer;
        } catch (RuntimeException e) {
            throw e;
        }
    }
}