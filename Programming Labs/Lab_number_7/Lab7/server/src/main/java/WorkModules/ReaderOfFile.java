package WorkModules;

import Collection.Collection;
import Data.Worker;
import Network.Server;


import java.io.IOException;
import java.net.SocketException;
import java.sql.SQLException;


public class ReaderOfFile {

    public static void main(String[] args) throws SQLException {
        DatabaseHandler databaseHandler= new DatabaseHandler();
        Collection<Worker> collection= new Collection<>(databaseHandler.getAllWorker());
        new Thread(() ->{
            try {Server server= new Server(collection, databaseHandler);
                server.runServer();
            } catch (SocketException e) {
                throw new RuntimeException(e);
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
}
