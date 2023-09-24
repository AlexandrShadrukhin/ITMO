package WorkModules;

import Commands.CommandExecuteScript;
import Network.Client;

import java.io.IOException;
import java.util.Scanner;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Client client= new Client();
        CommandExecuteScript commandExecuteScript= new CommandExecuteScript();
        Scanner scanner= new Scanner(System.in);
        Asker asker= new Asker();
        TokenizatorOfCommands tokenizatorOfCommands= new TokenizatorOfCommands();
        while (true){
            Task task= new Task(tokenizatorOfCommands.tokenizateCommand(scanner.nextLine()));
            if (client.auth || task.describe.contains("registration") || task.describe.contains("auth")){
                if (task.describe.contains("registration") || task.describe.contains("auth")){
                    System.out.println("PLease enter login");
                    task.login=scanner.nextLine();
                    System.out.println("Please enter password");
                    task.password=getMD2Hash(scanner.nextLine());
                    client.login = task.login ;
                    client.password =  task.password;
                }
                if (task.describe.contains("execute_script"))  {
                    commandExecuteScript.readScript(task.describe.get(1), tokenizatorOfCommands, client, asker);
                } else {
                    if (task.describe.contains("exit")) {
                        System.exit(0);
                    }
                    if (task.describe.contains("add") || task.describe.contains("insert_at") || task.describe.contains("update_id")) {
                        task.worker = asker.askWorker();
                        task.worker.setOwner(client.login);
                    }
                    client.sendTask(task);
                }
            }
            else {
                System.out.println("Please enter \"registration\" or \"auth\"");
            }
        }
    }

    public static String getMD2Hash(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD2");
            md.update(input.getBytes());
            byte[] hash = md.digest();
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xFF & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
