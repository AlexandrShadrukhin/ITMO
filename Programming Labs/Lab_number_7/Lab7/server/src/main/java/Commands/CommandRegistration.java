package Commands;

import Collection.Collection;
import Data.Worker;
import WorkModules.Answer;
import WorkModules.DatabaseHandler;
import WorkModules.Task;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.sql.SQLException;

public class CommandRegistration extends Command {
    private DatabaseHandler databaseHandler;

    public CommandRegistration(DatabaseHandler databaseHandler) {
        this.databaseHandler = databaseHandler;
    }

    @Override
    public Answer commandDo(String key, Task task) throws JsonProcessingException, SQLException {
        Answer answer = new Answer();
        try {
            databaseHandler.registrate(task.login, task.password);
            answer.setResult("Success registration");
            return answer;
        } catch (Exception e) {
            throw e;
        }
    }
}