package WorkModules;

import Data.*;
import Exceptions.ValueException;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Asker {
    private final Scanner scanner;
    private final Printer printer;


    public Asker() {
        scanner = new Scanner(System.in);
        printer = new Printer();
    }

    public Worker askWorker() {
        int salary = 0;
        Position position = null;
        Status status = null;
        Person person = null;
        Worker worker = null;
        try {
            System.out.println("Enter name");
            String name = askName();
            System.out.println("Enter coordinates");
            Coordinates coordinates = askCoordinates();
            System.out.println("Enter salary");
            salary = Integer.parseInt(scanner.nextLine().trim());
            LocalDateTime creationDate = LocalDateTime.now();
            ZonedDateTime endDate = null;
            if (askNullOrNot("end date")) {
                endDate = askData();
            }
            if (askNullOrNot("position")) {
                position = positionChoose();
            }
            status = statusChoose();
            if (askNullOrNot("person")) {
                person = askPerson();
            }
            worker = new Worker(name, coordinates, LocalDateTime.now(), salary, endDate, position, status, person);
            CheckModule checkModule = new CheckModule();
            if (!checkModule.checkWorker(worker)) {
                throw new Exception("Wrong worker");
            }
        } catch (Exception e) {
            printer.printHint(e.getMessage());
            worker = askWorker();
        }
        return worker;
    }

    public String askName() {
        String name;
        do {
            try {
                printer.printHint("Enter string");
                name = scanner.nextLine();
                if (name == null || name.isEmpty()) {
                    throw new ValueException();
                }
                break;
            } catch (ValueException e) {
                printer.printHint("Wrong string");
            }
        } while (true);
        return name;
    }

    public Coordinates askCoordinates() {
        double x = 0;
        double y = 0;
        Coordinates coordinates = null;
        do {
            try {
                System.out.println("Enter x");
                x = Double.parseDouble(scanner.nextLine().trim());
                System.out.println("Enter y");
                y = Double.parseDouble(scanner.nextLine().trim());
                coordinates = new Coordinates(x, y);
                break;
            } catch (NumberFormatException e) {
                printer.printHint("Invalid input. Please enter a valid number.");
            }
        } while (true);
        return coordinates;
    }

    public ZonedDateTime askData() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy - HH:mm:ss z");
        String inputTime = "";
        ZonedDateTime time = null;
        try {
            printer.printHint("Enter date");
            inputTime = scanner.nextLine();
            time = ZonedDateTime.parse(inputTime, formatter);
        } catch (Exception e) {
            printer.printHint("Wrong date");
            time = askData();
        }
        return time;
    }

    public boolean askNullOrNot(String line) {
        printer.printHint("If you want enter " +line+" print: y");
        return Objects.equals(scanner.nextLine(), "y");
    }

    public Position positionChoose() {
        Position position = null;
        try {
            printer.printHint("Enter: " + Arrays.toString(Position.values()));
            position = Position.valueOf(scanner.nextLine().toUpperCase().trim());
        } catch (ValueException e) {
            printer.printHint("Wrong position");
            position = positionChoose();
        }
        return position;
    }

    public Status statusChoose() {
        Status position = null;
        try {
            printer.printHint("Enter: " + Arrays.toString(Status.values()));
            position = Status.valueOf(scanner.nextLine().toUpperCase().trim());
        } catch (ValueException e) {
            printer.printHint("Wrong status");
            position = statusChoose();
        }
        return position;
    }

    public Person askPerson() {
        double height = 0;
        EyeColor eyeColor = null;
        HairColor hairColor = null;
        Country country = null;
        Location location = null;
        try {
            System.out.println("Enter height");
            height = Double.parseDouble(scanner.nextLine().trim());
            if (askNullOrNot("eye color")) {
                eyeColor = eyeColorChoose();
            }
            if (askNullOrNot("hair color")) {
                hairColor = hairColorChoose();
            }
            if (askNullOrNot("country")) {
                country = countryChoose();
            }
            System.out.println("location");
            location = askLocation();
        } catch (ValueException e) {
            printer.printHint("Wrong person");
        }
        return new Person(height, eyeColor, hairColor, country, location);
    }

    public HairColor hairColorChoose() {
        HairColor position = null;
        try {
            printer.printHint("Enter: " + Arrays.toString(HairColor.values()));
            position = HairColor.valueOf(scanner.nextLine().toUpperCase().trim());
        } catch (ValueException e) {
            printer.printHint("Wrong hair color");
            position = hairColorChoose();
        }
        return position;
    }

    public EyeColor eyeColorChoose() {
        EyeColor position = null;
        try {
            printer.printHint("Enter: " + Arrays.toString(EyeColor.values()));
            position = EyeColor.valueOf(scanner.nextLine().toUpperCase().trim());
        } catch (ValueException e) {
            printer.printHint("Wrong eye color");
            position = eyeColorChoose();
        }
        return position;
    }

    public Country countryChoose() {
        Country position = null;
        try {
            printer.printHint("Enter: " + Arrays.toString(Country.values()));
            position = Country.valueOf(scanner.nextLine().toUpperCase().trim());
        } catch (ValueException e) {
            printer.printHint("Wrong country");
            position = countryChoose();
        }
        return position;
    }

    public Location askLocation() {
        int x = 0;
        float y = 0;
        double z = 0;
        String name = "";
        Location location = null;
        try {
            System.out.println("Enter x");
            x = Integer.parseInt(scanner.nextLine().trim());
            System.out.println("Enter y");
            y = Float.parseFloat(scanner.nextLine().trim());
            System.out.println("Enter z");
            z = Double.parseDouble(scanner.nextLine().trim());
            System.out.println("Enter name");
            name = askName();
            location = new Location(x, y, z, name);
        } catch (ValueException e) {
            location = askLocation();
            printer.printHint("Wrong location");
        }
        return location;
    }


}
