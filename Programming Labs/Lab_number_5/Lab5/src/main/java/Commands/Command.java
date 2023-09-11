package Commands;

import WorkModules.Answer;

import java.io.IOException;

public abstract class Command {
    public Answer commandDo(String key) throws IOException {
        return null;
    }
}
