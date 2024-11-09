package com.myapp.pea;

import java.util.Random;
import java.util.Scanner;

public class RockSissor {
    public static void main(String[] args) {
        var s = new Scanner(System.in);
        var panis = "Angas";

        System.out.println("Input: 1-3 only\n" +
                "1: Scissor\n" +
                "2: Rock\n" +
                "3: Paper\n");

        var myScore = 0;
        var enemyScore = 0;

        for(var i = 0; i < 3; i++){

            System.out.print("Enter : ");

            var myMove = s.nextInt();

            var random = new Random();

            var enemyMove = random.nextInt(3) +1;

            if(myMove == 1 && enemyMove == 3 || myMove == 2 && enemyMove == 1 || myMove == 3 && enemyMove == 2)
                myScore++;
            else if(myMove == enemyMove){
                enemyScore++;
                myScore++;
            }else
                enemyScore++;

            System.out.println("My input : "+convertMove(myMove)+"\nEnemy input : "+convertMove(enemyMove)+"\n\nScore : "+myScore +" | "+enemyScore);
        }

        var winner = (myScore > enemyScore) ? "You win!" : (myScore == enemyScore) ? "Tie!" : "You lose!";

        System.out.println(winner);
    }

    public static String convertMove(int move){
        var convertMove = "";

        switch (move){
            case 1:
                convertMove+="Scissor";
                break;
            case 2:
                convertMove+="Rock";
                break;
            case 3:
                convertMove+="Paper";
                break;

        }

        return convertMove;
    }

}
