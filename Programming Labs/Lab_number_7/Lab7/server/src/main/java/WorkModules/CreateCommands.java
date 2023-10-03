package WorkModules;

import Collection.Collection;
import Commands.Command;
import Data.Worker;

import java.util.Map;

public interface CreateCommands {

    public Map<String, Command> createCommands(Collection<Worker> collection, DatabaseHandler databaseHandler);
}
