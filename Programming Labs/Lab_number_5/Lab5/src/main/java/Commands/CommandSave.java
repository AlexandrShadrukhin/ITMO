package Commands;

import Collection.Collection;
import Data.Worker;
import WorkModules.Answer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class CommandSave  extends Command {
    private Collection<Worker> collection;

    public CommandSave(Collection<Worker> collection) {
        this.collection = collection;
    }

    @Override
    public Answer commandDo(String key) throws IOException {
        Answer answer = new Answer();
        FileOutputStream fileOutputStream= new FileOutputStream(key);
        OutputStreamWriter outputStreamWriter= new OutputStreamWriter(fileOutputStream);
        String line= "";
        ObjectMapper mapper=(new YAMLMapper()).registerModule(new JavaTimeModule());
        try {
            for (int i = 0; i < collection.getVector().size(); i++){
                line= line+(mapper.writeValueAsString(collection.getVector().get(i)))+("\n");
            }
            outputStreamWriter.write(line);
            outputStreamWriter.close();
            return answer;
        } catch (Exception e) {
            throw e;
        }
    }
}