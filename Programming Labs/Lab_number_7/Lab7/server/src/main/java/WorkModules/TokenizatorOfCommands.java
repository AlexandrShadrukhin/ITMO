package WorkModules;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TokenizatorOfCommands {

    public ArrayList<String> tokenizateCommand(ArrayList<String> command, String path, ArrayList<String> history){
        command.get(0).toLowerCase();
        if (command.size()<2){
            command.add("");
        }

        if (Objects.equals(command.get(0), "save")){
            command.set(1, path);
        }
        if (Objects.equals(command.get(0), "history")){
            command.set(1, history.toString());
        }
        return command;
    }
}
