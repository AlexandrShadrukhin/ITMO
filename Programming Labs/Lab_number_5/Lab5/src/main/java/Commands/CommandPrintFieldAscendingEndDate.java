package Commands;

import Collection.Collection;
import Data.Worker;
import WorkModules.Answer;
import WorkModules.Asker;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CommandPrintFieldAscendingEndDate    extends Command {
    private Collection<Worker> collection;
    private Asker asker = new Asker();

    public CommandPrintFieldAscendingEndDate (Collection<Worker> collection) {
        this.collection = collection;
    }

    @Override
    public Answer commandDo(String key) throws JsonProcessingException {
        Answer answer = new Answer();
        ObjectMapper mapper=(new YAMLMapper()).registerModule(new JavaTimeModule());
        try {
            List<Worker> sortedList = new ArrayList<>();
            collection.getVector().stream().filter(it -> it.getEndDate()!=null).forEach(sortedList::add);
            sortedList.sort(Comparator.comparing(Worker::getEndDate));
            for (int i = 0; i < sortedList.size()-1; i++){
                answer.setResult(answer.getResult()+"\n"+mapper.writeValueAsString(sortedList.get(i)));
            }
            return answer;
        } catch (Exception e) {
            throw e;
        }
    }
}
