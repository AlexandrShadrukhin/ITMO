package Commands;

import WorkModules.Answer;
import WorkModules.Task;

import java.io.IOException;

public abstract class Command {
    public Answer commandDo(String key, Task task) throws IOException {
        return null;
    }
}
