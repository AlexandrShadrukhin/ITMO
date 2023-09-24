package WorkModules;

public class Printer {

    public void printHint(String message){
        System.out.println(message);
    }

    public void printAnswer(Answer answer){
        System.out.println(answer.getResult());
    }
}
