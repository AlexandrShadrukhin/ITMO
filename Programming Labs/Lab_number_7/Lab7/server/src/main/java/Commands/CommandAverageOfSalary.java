package Commands;

import Collection.Collection;
import Data.Worker;
import WorkModules.Answer;
import WorkModules.Task;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.ArrayList;
import java.util.List;

public class CommandAverageOfSalary  extends Command{
    private Collection<Worker> collection;
    public CommandAverageOfSalary(Collection<Worker> collection) {
        this.collection=collection;
    }

    @Override
    public Answer commandDo(String key, Task task) throws JsonProcessingException {
        Answer answer= new Answer();
        try {
            List<Integer> list= new ArrayList<>();
            collection.getVector().forEach(it-> list.add(it.getSalary()));
            double sum = (list.stream().mapToInt(Integer::intValue).sum());
            answer.setResult(String.valueOf(Double.valueOf(sum / list.size())));
            return answer;
        }
        catch (Exception e){
            throw e;
        }
    }
}