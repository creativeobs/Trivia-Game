package com.example.triviagame;

import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;

import java.util.Arrays;
import java.util.Random;

import static android.content.ContentValues.TAG;

public class Questions {

    Random random = new Random();
    String[][] qa = new String[5][7];
    int rand = 0;
    int[] collection = {-1,-1,-1,-1,-1};

    private String[][] database = {
            {"Fuel, heat and which other ingredient is needed for a fire?","Nitrogen","Helium","Neon","Oxygen","4","3"},
            {"What is the highest mountain on Earth ?", "Kili Man Jaro", "Mount Apo", "Mt. Everest", "Mt. Fuji", "3", "3"},
            {"This is associated with extreme hallucinations.", "PTSD", "Obsessive Compulsory", "Depression", "Schizophrenia", "4", "2"},
            {"What is the fastest animal on land ?", "Cheetah", "Dodo", "Panther", "Tiger", "1", "5"},
            {"Who do you call the Olympus's God of Underworld ?", "Zeus", "Hades", "Poseidon", "Hermes", "2", "1"},
            {"Why do we still stay with someone even though you know that it hurts ?", "Love", "Being an idiot", "Hopeful that one day he/she will change", "Money", "2", "1"},
            {"5 divide by zero is ?","5","1","0","undefined", "4","4"},
            {"Who is the most handome man on Earth?","Justin Bieber","Russel Evangelista","Jungkook","Brad Pitt","2","1"},
            {"Who proposed the theory of Relativity ?", "Elon Musk", "Albert Einstein", "Isaac Newton", "Galileo Galilei","2","1"},
            {"What comes next ? 1, 3, 0, 4, -1 ?", "5", "-2", "6", "7", "3","4"},
            {"What is the name of the person who proposed the laws of motion", "Isaac Newtion", "Charles Darwin ", "Aristotle", "Nikola Tesla", "1","1"},
            {"What is the most strongest animal?","Gorilla", "Crocodile","Beetle", "Bear", "3","5"},
            {"What is the the fifth planet in the Solar System?", "Jupiter", "Earth", "Mars", "Saturn", "1","3"},
            {"12 S of the Z?","12 stars of the zoo","12 streets of the Zimbabwe","12 sheets of the Zebra","12 signs of the Zodiac","4","2"},
            {"What is 5 x 25 = ?","100", "125", "150", "120","2","4"},
            {"How many letters are there in the alphabet?","25", "24", "26","27","4","2"},
            {"There are 5 birds, a man shot one. How many left ?", "4", "5","0","1", "3","2"},
            {"In a website browser address bar, what does “www” stand for?","World Wide Web","World Web Wide","World Wide Weeb","I don't know!","1","2"},
            {"Suppose you are given 2 doors, one has lions that haven't eaten for 2 years, the other is room full of fire which do you choose ?", "First", "Second", "None", "Stay", "1", "2"},
            {"Who is the father of Computer Science ?", "Alan Turing", "Augusta Ada Lovelace", "Jose Heisenbeg", "John Vincent Atanasoff", "1","1"}
    };

    public Questions(){
        boolean cont = true;
        for(int i = 0; i < 5; i++){
            rand = random.nextInt(20);
            for(int j = 0; j < 5; j++){
                if (collection[j] == rand){
                    cont = false;
                    break;
                }
            }
            if (cont == false){
                i -= 1;
                cont = true;
                continue;
            }
            for (int x = 0; x < 7; x++){
                qa[i][x] = this.database[rand][x];
            }

            collection[i] = rand;
        }
    }

    public String getQuestion(int x){
        return qa[x][0];
    }
    public String getchoice1(int x){
        return qa[x][1];
    }
    public String getchoice2(int x){
        return qa[x][2];
    }
    public String getchoice3(int x){
        return qa[x][3];
    }
    public String getchoice4(int x){
        return qa[x][4];
    }
    public String getAnswer(int x){
        return qa[x][5];
    }
    public String getImage(int x){return qa[x][6];}
}
