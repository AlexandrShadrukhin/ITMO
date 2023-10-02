package WorkModules;

import Collection.Collection;
import Commands.*;
import Data.Worker;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ChooseCommand implements CreateCommands {

    private Collection<Worker> collection;
    private Map<String, Command> listOfCommand;
    public DatabaseHandler databaseHandler;

    public ChooseCommand(Collection<Worker> collection, DatabaseHandler databaseHandler) {
        this.collection = collection;
        this.databaseHandler= databaseHandler;
        this.listOfCommand=createCommands(collection, databaseHandler);
    }


    public Answer startChooseCommand(ArrayList<String> commandComponents, Task task) throws SQLException, IOException {
        try {
            if (task.describe.contains("clear")){
                task.setLogin(databaseHandler.getLogin());
                return listOfCommand.get(commandComponents.get(0)).commandDo(commandComponents.get(1), task);
            }else {
                return listOfCommand.get(commandComponents.get(0)).commandDo(commandComponents.get(1), task);
            }
        }
        catch (Exception e){
            return new Answer("Команда введена неверно");
        }

    }


    @Override
    public Map<String, Command> createCommands(Collection<Worker> collection, DatabaseHandler databaseHandler) {
        Map<String, Command> map = new HashMap<>();
        map.put("show", new CommandShow(collection));
        map.put("add", new CommandAdd(collection, databaseHandler));
        map.put("save", new CommandSave(collection, databaseHandler));
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
        map.put("update_id", new CommandUpdateId(collection));
        map.put("registration", new CommandRegistration(databaseHandler));
        map.put("auth", new CommandAuth(databaseHandler));
        return map;
    }
}
