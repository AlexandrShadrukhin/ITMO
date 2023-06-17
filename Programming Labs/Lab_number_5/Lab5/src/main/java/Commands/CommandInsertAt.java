package Commands;

import Collection.Collection;
import Data.Worker;
import WorkModules.Answer;
import WorkModules.Asker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

public class CommandInsertAt extends Command {
    private Collection<Worker> collection;
    private Asker asker = new Asker();

    public CommandInsertAt(Collection<Worker> collection) {
        this.collection = collection;
    }

    @Override
    public Answer commandDo(String key) {
        Answer answer = new Answer();
        Worker worker= asker.askWorker();
        ArrayList<Integer> listOfId= new ArrayList<>();
        listOfId.add(0);
        collection.getVector().forEach(it-> listOfId.add(it.getId()));
        worker.setId(Collections.max(listOfId)+1);
        try {
            if (collection.getVector().size()<Integer.parseInt(key)+1){
                Vector<Worker> vector= new Vector<>(collection.getVector().size()+1);
                vector.addAll(collection.getVector());
                collection.setVector(vector);
                collection.getVector().add(worker);
            }
            else {
                collection.getVector().add(worker);
            }
            return answer;
        } catch (RuntimeException e) {
            throw e;
        }
    }
}