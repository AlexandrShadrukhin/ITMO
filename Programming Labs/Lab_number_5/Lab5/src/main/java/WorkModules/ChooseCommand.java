package WorkModules;

import Collection.Collection;
import Commands.*;
import Data.Worker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ChooseCommand implements CreateCommands {

    private Collection<Worker> collection;
    private Map<String, Command> listOfCommand;

    public ChooseCommand(Collection<Worker> collection) {
        this.collection = collection;
        this.listOfCommand=createCommands(collection);
    }


    public Answer startChooseCommand(ArrayList<String> commandComponents) throws IOException {
        try {
            return listOfCommand.get(commandComponents.get(0)).commandDo(commandComponents.get(1));
        }
        catch (Exception e){
            Answer answer= new Answer();
            answer.setResult(" Команда введена неверно");
            return answer;
        }

    }


    @Override
    public Map<String, Command> createCommands(Collection<Worker> collection) {
        Map<String, Command> map= new HashMap<>();
        map.put("exit", new CommandExit());
        map.put("show", new CommandShow(collection));
        map.put("add", new CommandAdd(collection));
        map.put("save", new CommandSave(collection));
        map.put("clear", new CommandClear(collection));
        map.put("info", new CommandInfo(collection));
        map.put("history", new CommandHistory());
        map.put("remove_by_id", new CommandRemoveById(collection));
        map.put("insert_at", new CommandInsertAt(collection));
        map.put("remove_last", new CommandRemoveLast(collection));
        map.put("average_of_salary", new CommandAverageOfSalary(collection));
        map.put("max_by_status", new CommandMaxByStatus(collection));
        map.put("print_field_ascending_end_date", new CommandPrintFieldAscendingEndDate(collection));
        map.put("help", new CommandHelp());
        map.put("update id", new CommandUpdateId(collection));
        return map;
    }
}
