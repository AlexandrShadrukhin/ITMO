package Commands;

import Collection.Collection;
import Data.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Scanner;
public class addFromFlle {
    private Collection<Worker> collection;
    /* ZonedDateTimeFormat formatterTime = ZonedDateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");*/
    Scanner scanner;
    ZoneId zoneId = ZoneId.systemDefault();

    addFromFlle(){

    }
    addFromFlle(Scanner scanner){
        this.scanner = scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public Scanner getScanner() {
        return scanner;
    }
    public Scanner doRead(Scanner scanner, Collection<Worker> collection){
        this.collection=collection;
        Worker worker = new Worker();
        String name = "";
        Coordinates coordinates = new Coordinates();
        LocalDateTime creationDate;
        int salary;
        LocalDateTime endDateString = LocalDateTime.now();
        String str = "";
        Position position = null;
        Status status = null;
        Person person = null;


        name = scanner.nextLine();
        coordinates.setX(Double.parseDouble(scanner.nextLine()));
        coordinates.setY(Double.parseDouble(scanner.nextLine()));
        creationDate = LocalDateTime.now();
        salary = Integer.parseInt(scanner.nextLine());
        str = scanner.nextLine();
        if(str.equals("1")){
            endDateString = LocalDateTime.now();
        }
        str = scanner.nextLine();
        if(str.equals("1")){
            position=Position.DIRECTOR;
        } else if (str.equals("2")) {
            position=Position.HEAD_OF_DIVISION;
        }else if (str.equals("3")) {
            position=Position.LEAD_DEVELOPER;
        }
        str = scanner.nextLine();
        if(str.equals("1")){
            status=Status.FIRED;
        } else if (str.equals("2")) {
            status=Status.PROBATION;
        }else if (str.equals("3")) {
            status=Status.REGULAR;
        }
        else if (str.equals("4")) {
            status=Status.RECOMMENDED_FOR_PROMOTION;
        }
        str = scanner.nextLine();
        if(str.equals("1")){
            person = null;
        }


        worker.setName(name);
        worker.setCoordinates(coordinates);
        worker.setCreationDate(creationDate);
        worker.setSalary(salary);
        worker.setEndDate(ZonedDateTime.of(LocalDateTime.now(), zoneId) );
        worker.setPosition(position);
        worker.setStatus(status);
        worker.setPerson(null);

        collection.getVector().add(worker);
        return scanner;
    }
}