package Commands;

import Collection.Collection;
import Data.Worker;
import WorkModules.Answer;
import WorkModules.Task;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class CommandShow extends Command{
    private Collection<Worker> collection;
    public CommandShow(Collection<Worker> collection) {
        this.collection=collection;
    }

    @Override
    public Answer commandDo(String key, Task task) throws JsonProcessingException {
        Answer answer= new Answer();
        ObjectMapper mapper=(new YAMLMapper()).registerModule(new JavaTimeModule());
        try {
            Worker[] list= collection.getVector().toArray(new Worker[0]);
            for (int i = 0; i < list.length; i++){
                answer.setResult(answer.getResult()+"\n"+mapper.writeValueAsString(list[i]));
            }
            return answer;
        }
        catch (Exception e){
            throw e;
        }
    }
}
