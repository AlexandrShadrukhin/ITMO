package WorkModules;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TokenizatorOfCommands {

    public ArrayList<String> tokenizateCommand(String command, String path, ArrayList<String> history){
        String[] commandComponents1= command.split( " ");
        ArrayList<String> commandComponents2= new ArrayList<>();
        commandComponents2.addAll(List.of(commandComponents1));
        commandComponents2.get(0).toLowerCase();
        if (commandComponents2.size()<2){
            commandComponents2.add("");
        }

        if (commandComponents2.size()==3){
            commandComponents2.set(0, commandComponents2.get(0) +" "+commandComponents2.get(1));
            commandComponents2.set(1, commandComponents2.get(2));
            commandComponents2.remove(2);
        }

        if (Objects.equals(commandComponents2.get(0), "save")){
            commandComponents2.set(1, path);
        }
        if (Objects.equals(commandComponents2.get(0), "history")){
            commandComponents2.set(1, history.toString());
        }
        return commandComponents2;
    }
}
