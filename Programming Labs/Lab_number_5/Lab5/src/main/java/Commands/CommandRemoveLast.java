package Commands;

import Collection.Collection;
import Data.Worker;
import WorkModules.Answer;
import WorkModules.Asker;

public class CommandRemoveLast   extends Command {
    private Collection<Worker> collection;
    private Asker asker = new Asker();

    public CommandRemoveLast(Collection<Worker> collection) {
        this.collection = collection;
    }

    @Override
    public Answer commandDo(String key) {
        Answer answer = new Answer();
        try {
            collection.getVector().remove(collection.getVector().size()-1);
            return answer;
        } catch (RuntimeException e) {
            throw e;
        }
    }
}
