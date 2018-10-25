package sample;

import java.util.Random;

public class NameGenerator {

    private String[] arrayGrades = new String[] {"Супермутант", "Таракан", "Алкоголик", "Гопник", "Просто человек"};
    private String[] arrayNames = new String[] {"Володя", "Геннадий", "Семен", "Иван", "Демид"};
    private Random random = new Random();

    public String GetName(){
        return String.format("%s %s", arrayGrades[this.random.nextInt(arrayGrades.length - 1)],
                arrayNames[this.random.nextInt(arrayNames.length - 1)]);
    }
}
