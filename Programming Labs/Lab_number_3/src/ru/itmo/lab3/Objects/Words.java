package ru.itmo.lab3.Objects;

public class Words {
    @Override
    public String toString(){

        return (", но слова нельзя было");
    }
    public void takeApart(Words words){
        System.out.print(words.toString() + " разобрать.");
    }
}
