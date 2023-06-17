package WorkModules;

import Collection.Collection;
import Commands.CommandExecuteScript;
import Commands.WorkHistory;
import Data.Worker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class ReaderOfCommands implements WorkHistory {

    private final ArrayList<String> history = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);
    private final CommandExecuteScript commandExecuteScript = new CommandExecuteScript();


    public void startReadCommands(Collection<Worker> collection, String path) throws IOException {
        TokenizatorOfCommands tokenizatorOfCommands = new TokenizatorOfCommands();
        Printer printer = new Printer();
        String command;
        ChooseCommand chooseCommand = new ChooseCommand(collection);
        while (scanner.hasNextLine()) {
            command = scanner.nextLine();
            ArrayList<String> commandComponents = tokenizatorOfCommands.tokenizateCommand(command, path, history);
            memberCommands(commandComponents);
            if (!Objects.equals(commandComponents.get(0), "execute_script")) {
                printer.printAnswer(chooseCommand.startChooseCommand(commandComponents));
            } else {
                ArrayList<ArrayList<String>> listOfCommands = commandExecuteScript.readScript(commandComponents.get(1), tokenizatorOfCommands, path, history, new ArrayList<>());
                for (ArrayList<String> listOfCommand : listOfCommands) {
                    printer.printAnswer(chooseCommand.startChooseCommand(listOfCommand));
                }
            }
        }
    }


    @Override
    public void memberCommands(ArrayList<String> list) {
        if (history.size() <= 14) {
            history.add(list.get(0));
        } else {
            history.remove(history.size()-1);
            history.add(list.get(0));
        }
    }

}