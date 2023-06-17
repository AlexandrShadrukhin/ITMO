package Commands;

import Collection.Collection;
import Data.Worker;
import WorkModules.Answer;
import WorkModules.Asker;

public class CommandAdd  extends Command{
    private Collection<Worker> collection;
    private Asker asker= new Asker();
    public CommandAdd(Collection<Worker> collection) {
        this.collection=collection;
    }

    @Override
    public Answer commandDo(String key) {
        Answer answer= new Answer();
        try {
            Worker worker= asker.askWorker();
            collection.getVector().add(worker);
            return answer;
        }
        catch (RuntimeException e){
            throw e;
        }
    }
}
