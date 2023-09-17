package WorkModules;

import Data.Worker;

import java.io.Serializable;
import java.util.ArrayList;

public class Task implements Serializable {

    public ArrayList<String> describe;
    public Worker worker;
    public Task(ArrayList<String> describe) {
        this.describe = describe;
    }

    public Task(ArrayList<String> describe, Worker worker) {
        this.describe = describe;
        this.worker = worker;
    }
}
