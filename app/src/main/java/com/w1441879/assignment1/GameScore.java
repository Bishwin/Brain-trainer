package com.w1441879.assignment1;

public class GameScore {
    private int gameScore;

    GameScore(int score) {
        gameScore = score;
    }

    public void setScore(int time){
        int maxTime = 10;
        if(time==maxTime)gameScore += 100;
        else {
            int timeRemaining = maxTime - time; //works out time remaining
            int score = maxTime - timeRemaining; //calculates first part of formula 10-time remaining
            double subTotal = Math.round(100 / (double) score); //final calculation 100 divided by previous expression
            gameScore +=(int)subTotal;
            System.out.println("subtotal" + subTotal);
            System.out.println("SCORE" + gameScore);
        }
    }
    public int getScore(){
        return gameScore;
    }


}
