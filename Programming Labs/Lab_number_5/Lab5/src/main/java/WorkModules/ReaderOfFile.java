package WorkModules;

        import Collection.Collection;
        import Data.Worker;
        import com.fasterxml.jackson.databind.ObjectMapper;
        import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
        import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

        import java.io.File;
        import java.io.IOException;
        import java.util.*;

public class ReaderOfFile {

    public static void main(String[] args) throws IOException {
        ArrayList<Integer> listOfId= new ArrayList<>();
        listOfId.add(0);
        ObjectMapper mapper=(new YAMLMapper()).registerModule(new JavaTimeModule());
        String objectsInString= "";
        String buf = "";
        Collection<Worker> collection= new Collection<Worker>(new Vector<>());
        CheckModule checkModule= new CheckModule();
        File file= new File("/Users/AlexandrShadrukhin/Documents/untitledFolder/Lab/src/main/java/testfile.txt");
        if (!file.exists()){
            System.out.println("Нет файла коллекции");
            System.exit(0);
        }
        Scanner scanner= new Scanner(file);
        String path="/Users/AlexandrShadrukhin/Documents/untitledFolder/Lab/src/main/java/testfile.txt";
        while (scanner.hasNextLine()){
            buf=scanner.nextLine();
            if(!Objects.equals(buf, "---")){
                objectsInString+=buf+"\n";
            }
            if ((Objects.equals(buf, "---") || !scanner.hasNextLine()) && objectsInString!="") {
                Worker worker = mapper.readValue(objectsInString, Worker.class);
                worker.setId((Collections.max(listOfId)+1));
                listOfId.add(Collections.max(listOfId)+1);
                objectsInString="";
                if (checkModule.checkWorker(worker)){
                    collection.getVector().add(worker);
                }
            }
        }
        ReaderOfCommands reader= new ReaderOfCommands();
        reader.startReadCommands(collection, path);
    }
}




///home/studs/s367629/FifthLab/testfile.txt