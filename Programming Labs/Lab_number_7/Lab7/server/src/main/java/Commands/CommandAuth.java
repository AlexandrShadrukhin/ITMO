package Commands;

import WorkModules.Answer;
import WorkModules.DatabaseHandler;
import WorkModules.Task;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.sql.SQLException;

public class CommandAuth  extends Command {
    private DatabaseHandler databaseHandler;

    public CommandAuth(DatabaseHandler databaseHandler) {
        this.databaseHandler = databaseHandler;
    }

    @Override
    public Answer commandDo(String key, Task task) throws JsonProcessingException, SQLException {
        Answer answer = new Answer();
        try {
            answer.setResult("Failed auth");
            if (databaseHandler.checkUser(task.login, task.password)) {
                answer.setResult("Success auth");
            }
            return answer;
        } catch (Exception e) {
            throw e;
        }
    }
}