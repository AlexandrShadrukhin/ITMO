package WorkModules;

import Data.Worker;

public class CheckModule {
    public boolean checkWorker(Worker worker) {
        return worker.getName() != "" && worker.getSalary() > 0 && worker.getCoordinates().getX() < 404;
    }
}
