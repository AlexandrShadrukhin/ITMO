package ru.itmo.lab4.objects;

public class Words {
    @Override
    public String toString(){

        return (", но слова нельзя было");
    }
    public void takeApart(Words words){
        System.out.println(words.toString() + " разобрать.");
    }
}
