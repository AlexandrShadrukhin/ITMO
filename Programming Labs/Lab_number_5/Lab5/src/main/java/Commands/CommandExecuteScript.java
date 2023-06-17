package Commands;

import WorkModules.TokenizatorOfCommands;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class CommandExecuteScript implements WorkHistory {
    @Override
    public void memberCommands(ArrayList<String> list) {
        if (list.size() <= 14) {
            list.add(list.get(0));
        } else {
            list.remove(0);
            list.add(list.get(0));
        }
    }

    public ArrayList<ArrayList<String>> readScript(String path, TokenizatorOfCommands tokenizatorOfCommands, String pathOfFile, ArrayList<String> history, ArrayList<String> listOfPaths) throws IOException {
        ArrayList<ArrayList<String>> listOfCommands = new ArrayList<>();
        ArrayList<String> listOfLocalPaths = new ArrayList<>(listOfPaths);
        try {
            FileReader fileReader = new FileReader(path);
            if (!fileReader.ready()) {
                System.out.println("Что-то неправильно с файлом");
                return listOfCommands;
            }
            Scanner scanner = new Scanner(fileReader);
            ArrayList<String> commandComponents;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                commandComponents = tokenizatorOfCommands.tokenizateCommand(line, pathOfFile, history);
                history.add(commandComponents.get(1));
                if (!Objects.equals(commandComponents.get(0), "execute_script")) {
                    listOfCommands.add(commandComponents);
                } else {
                    if (!listOfLocalPaths.contains(commandComponents.get(1))) {
                        listOfLocalPaths.add(commandComponents.get(1));
                        listOfCommands.addAll(readScript(commandComponents.get(1), tokenizatorOfCommands, pathOfFile, history, listOfLocalPaths));
                    } else {
                        System.out.println("Обнаружено зацикливание при выполнении скрипта " + commandComponents.get(1));
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Файл не найден");
        }
        return listOfCommands;
    }
}
