package Commands;

import WorkModules.Answer;

public class CommandExit extends Command{
    @Override
    public Answer commandDo(String key) {
        System.exit(0);
        return new Answer();
    }
}
