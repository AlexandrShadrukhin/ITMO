package WorkModules;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TokenizatorOfCommands {

    public ArrayList<String> tokenizateCommand(String command){
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

        return commandComponents2;
    }
}
