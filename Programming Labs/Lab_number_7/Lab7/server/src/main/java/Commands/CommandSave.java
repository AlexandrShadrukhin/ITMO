package Commands;

import Collection.Collection;
import Data.Worker;
import WorkModules.Answer;
import WorkModules.DatabaseHandler;
import WorkModules.Task;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.SQLException;

public class CommandSave  extends Command {
    private Collection<Worker> collection;
    private DatabaseHandler databaseHandler;

    public CommandSave(Collection<Worker> collection, DatabaseHandler databaseHandler) {
        this.collection = collection;
        this.databaseHandler= databaseHandler;
    }

    @Override
    public Answer commandDo(String key, Task task) throws IOException, SQLException {
        Answer answer = new Answer();
        try {
            databaseHandler.putAllWorkers(collection.getVector());
            return answer;
        } catch (Exception e) {
            throw e;
        }
    }
}