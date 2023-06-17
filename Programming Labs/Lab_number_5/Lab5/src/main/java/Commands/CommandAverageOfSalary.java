package Commands;

import Collection.Collection;
import Data.Worker;
import WorkModules.Answer;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.ArrayList;
import java.util.List;

public class CommandAverageOfSalary  extends Command{
    private Collection<Worker> collection;
    public CommandAverageOfSalary(Collection<Worker> collection) {
        this.collection=collection;
    }

    @Override
    public Answer commandDo(String key) throws JsonProcessingException {
        Answer answer= new Answer();
        ArrayList<Integer> listOfSalary= new ArrayList<>();
        Float sum= (float) 0;
        try {
            List<Integer> list= new ArrayList<>();
            collection.getVector().forEach(it-> list.add(it.getSalary()));
            for (int i = 0; i < list.size()-1; i++){
                sum+= list.get(i);
            }
            answer.setResult(String.valueOf(sum/list.size()));
            return answer;
        }
        catch (Exception e){
            throw e;
        }
    }
}