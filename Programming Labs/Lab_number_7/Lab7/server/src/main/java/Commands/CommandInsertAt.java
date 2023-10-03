package Commands;

import Collection.Collection;
import Data.Worker;
import WorkModules.Answer;
import WorkModules.DatabaseHandler;
import WorkModules.Task;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

public class CommandInsertAt extends Command {
    private Collection<Worker> collection;
    private DatabaseHandler databaseHandler;
    public CommandInsertAt(Collection<Worker> collection, DatabaseHandler databaseHandler) {
        this.collection = collection;
        this.databaseHandler = databaseHandler;
    }

    @Override
    public Answer commandDo(String key, Task task) {
        Answer answer = new Answer();
        ArrayList<Integer> listOfId= new ArrayList<>();
        listOfId.add(0);
        collection.getVector().forEach(it-> listOfId.add(it.getId()));
        task.worker.setId(Collections.max(listOfId)+1);
        try {
            if (collection.getVector().size()<Integer.parseInt(key)+1) {
                Vector<Worker> vector = new Vector<>(collection.getVector().size() + 1);
                vector.addAll(collection.getVector());
                collection.setVector(vector);
            }
            try {
                databaseHandler.putWorker(task.worker);
                collection.getVector().add(task.worker);
            }
            catch (SQLException e){
                System.out.println("The worker didn't inserted");
            }
            return answer;
        } catch (RuntimeException e) {
            throw e;
        }
    }
}