package Commands;

import Collection.Collection;
import Data.Worker;
import WorkModules.Answer;
import WorkModules.Asker;

import java.util.List;
import java.util.Vector;

public class CommandUpdateId extends Command{
    private Collection<Worker> collection;
    private Asker asker= new Asker();
    public CommandUpdateId(Collection<Worker> collection) {
        this.collection=collection;
    }

    @Override
    public Answer commandDo(String key) {
        Answer answer= new Answer();
        try {
            List<Worker> list= collection.getVector().stream().filter(it -> it.getId() != Integer.parseInt(key)).toList();
            collection.setVector(new Vector<>(list.size()+1));
            list.forEach(it-> collection.getVector().add(it));
            Worker worker= asker.askWorker();
            worker.setId(Integer.parseInt(key));
            collection.getVector().add(worker);
            return answer;
        } catch (RuntimeException e) {
            throw e;
        }
    }
}