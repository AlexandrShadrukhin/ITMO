package Commands;

import Data.Worker;
import Network.Client;
import WorkModules.Asker;
import WorkModules.Task;
import WorkModules.TokenizatorOfCommands;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.Scanner;

public class CommandExecuteScript {
    private ArrayList<String> listOfPaths= new ArrayList<>();

    public void readScript(String path, TokenizatorOfCommands tokenizatorOfCommands, Client client, Asker asker) throws IOException, ClassNotFoundException {
        FileReader fileReader= new FileReader(path);
        if (!fileReader.ready()){
            System.out.println("Что то непраильно с файлом");
        }
        else {
            Scanner scanner = new Scanner(fileReader);
            ArrayList<String> commandComponents;

            try{
                while (scanner.hasNextLine()) {
                    commandComponents = tokenizatorOfCommands.tokenizateCommand(scanner.nextLine());
                    if (!Objects.equals(commandComponents.get(0), "execute_script")) {
                        Task task = new Task(commandComponents);
                        if (task.describe.contains("exit")) {
                            System.exit(0);
                        }
                        if (task.describe.contains("add")) {
                            addFromFile workerFromFile = new addFromFile();
                            task.worker = workerFromFile.doRead(scanner);}
                        if (task.describe.contains("insert_at") || task.describe.contains("update_id")) {    task.worker = asker.askWorker();
                        }
                        client.sendTask(task);
                    } else {
                        if (!listOfPaths.contains(commandComponents.get(1))) {
                            listOfPaths.add(commandComponents.get(1));
                            readScript(commandComponents.get(1), tokenizatorOfCommands, client, asker);
                        }
                    }
                }
            }
            catch (Exception e){
            }
        }
    }
}
