package Commands;

import WorkModules.Answer;
import WorkModules.Task;

import java.io.IOException;
import java.sql.SQLException;

public abstract class Command {
    public Answer commandDo(String key, Task task) throws IOException, SQLException {
        return null;
    }
}
