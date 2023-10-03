package Commands;

import Collection.Collection;
import Data.Worker;
import WorkModules.Answer;
import WorkModules.DatabaseHandler;
import WorkModules.Task;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

public class CommandAdd  extends Command{
    private Collection<Worker> collection;
    private DatabaseHandler databaseHandler;
    public CommandAdd(Collection<Worker> collection, DatabaseHandler databaseHandler) {
        this.collection = collection;
        this.databaseHandler = databaseHandler;
    }

    @Override
    public Answer commandDo(String key, Task task) {
        Answer answer= new Answer();
        try {
            ArrayList<Integer> listOfId= new ArrayList<>();
            listOfId.addAll(collection.getVector().stream().map(it -> it.getId()).toList());
            listOfId.add(0);
            task.worker.setId(Collections.max(listOfId)+1);
            try {
                databaseHandler.putWorker(task.worker);
                collection.getVector().add(task.worker);
            }
            catch (SQLException e){
                System.out.println("Object is not added");
            }
            return answer;
        }
        catch (RuntimeException e){
            throw e;
        }
    }
}
