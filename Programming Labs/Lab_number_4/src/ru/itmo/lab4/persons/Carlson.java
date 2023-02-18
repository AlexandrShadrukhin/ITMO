package ru.itmo.lab4.persons;

import ru.itmo.lab4.exceptions.AgreeException;


public class Carlson extends Person {
    public Carlson(String name) {
        super(name);
    }
    public void choice() {
        //lab4 local class
        class Disagree{
            private final String choice = " не соглашался. ";
            public Disagree(String choice) throws AgreeException {
                if (!choice.equals(" согласился ")) throw new AgreeException("не правильный выбор");
            }
            @Override
            public String toString() {
                return choice;
            }
        }
        try {
            Disagree disagree = new Disagree(" согласился ");
            System.out.print("но " + getName() + disagree);
        }
        catch (AgreeException a){
            System.err.println(a.getMessage());
        }
    }
}
