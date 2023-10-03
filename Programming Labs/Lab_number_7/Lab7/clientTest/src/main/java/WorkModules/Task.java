package WorkModules;

import Data.Worker;

import java.io.Serializable;
import java.util.ArrayList;

public class Task implements Serializable {
    private static final long serialVersionUID = 7240398079779844380L;
    public ArrayList<String> describe;
    public Worker worker;
    public String login;
    public String password;
    public Task(ArrayList<String> describe) {
        this.describe = describe;
    }

    public Task(ArrayList<String> describe, Worker worker) {
        this.describe = describe;
        this.worker = worker;
    }
    public void  setLogin(String login) {
        this.login = login;
    }
}
