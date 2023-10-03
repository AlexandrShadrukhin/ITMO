package Commands;

import Collection.Collection;
import Data.Worker;
import WorkModules.Answer;
import WorkModules.Task;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CommandMaxByStatus extends Command {
    private Collection<Worker> collection;

    public CommandMaxByStatus(Collection<Worker> collection) {
        this.collection = collection;
    }

    @Override
    public Answer commandDo(String key, Task task) throws JsonProcessingException {
        Answer answer = new Answer();
        ObjectMapper mapper = (new YAMLMapper()).registerModule(new JavaTimeModule());
        try {
            List<Worker> sortedList = new ArrayList<>();
            collection.getVector().stream().filter(it -> it.getStatus() != null).forEach(sortedList::add);
            sortedList.sort(Comparator.comparing(Worker::getStatus));
            if (sortedList.size() > 0) {
                answer.setResult(mapper.writeValueAsString(sortedList.get(0)));
            }
            return answer;
        } catch (Exception e) {
            throw e;
        }
    }
}
