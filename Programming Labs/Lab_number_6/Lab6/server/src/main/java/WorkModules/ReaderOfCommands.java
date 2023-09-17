package WorkModules;

import Collection.Collection;
import Commands.WorkHistory;
import Data.Worker;

import java.io.IOException;
import java.util.ArrayList;

public class ReaderOfCommands implements WorkHistory {

    private final ArrayList<String> history = new ArrayList<>();

    public Answer startReadCommands(Collection<Worker> collection, String path, Task task) throws IOException {
        TokenizatorOfCommands tokenizatorOfCommands = new TokenizatorOfCommands();
        ChooseCommand chooseCommand = new ChooseCommand(collection);
        ArrayList<String> commandComponents = tokenizatorOfCommands.tokenizateCommand(task.describe, path, history);
        memberCommands(commandComponents);
        return chooseCommand.startChooseCommand(commandComponents, task);
    }


    @Override
    public void memberCommands(ArrayList<String> list) {
        if (history.size() <= 13) {        if(list.get(0).equals("show")){
            history.add(list.get(0));        }
        else if (list.get(0).equals("add")){            history.add(list.get(0));
        }        else if (list.get(0).equals("save")){
            history.add(list.get(0));        }
        else if (list.get(0).equals("clear")){
            history.add(list.get(0));        }
        else if (list.get(0).equals("info")){            history.add(list.get(0));
        }        else if (list.get(0).equals("history")){
            history.add(list.get(0));        }
        else if (list.get(0).equals("remove_by_id")){            history.add(list.get(0));
        }        else if (list.get(0).equals("insert_at")){
            history.add(list.get(0));        }
        else if (list.get(0).equals("average_of_salary")){            history.add(list.get(0));
        }        else if (list.get(0).equals("max_by_status")){
            history.add(list.get(0));        }
        else if (list.get(0).equals("print_field_ascending_end_date")){            history.add(list.get(0));
        }
        else if (list.get(0).equals("help")){            history.add(list.get(0));
        }        else if (list.get(0).equals("update_id")){
            history.add(list.get(0));        }

        } else {        history.remove(0);
            history.add(list.get(0));    }
    }
}