package WorkModules;

import Commands.CommandExecuteScript;
import Network.Client;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Client client= new Client();
        CommandExecuteScript commandExecuteScript= new CommandExecuteScript();
        Scanner scanner= new Scanner(System.in);
        Asker asker= new Asker();
        TokenizatorOfCommands tokenizatorOfCommands= new TokenizatorOfCommands();
        while (true){
            Task task= new Task(tokenizatorOfCommands.tokenizateCommand(scanner.nextLine()));
            if (task.describe.contains("execute_script")){
                commandExecuteScript.readScript(task.describe.get(1),tokenizatorOfCommands, client, asker);
            }
            else {
                if (task.describe.contains("exit")){
                    System.exit(0);
                }
                if (task.describe.contains("add") || task.describe.contains("insert_at") || task.describe.contains("update_id")){
                    task.worker=asker.askWorker();
                }
                client.sendTask(task);
            }
        }
    }
}

//execute_script /Users/AlexandrShadrukhin/Documents/untitledFolder/Lab6/script.txt