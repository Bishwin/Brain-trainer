package com.w1441879.assignment1;

import java.util.Random;

public class GameLogic {

    Random rng = new Random();

    private int difficulty = 0;
    private int[] numbers;
    private String[] qOperators;

    GameLogic(int diff){
        difficulty = diff;
    }

    public String createQuestion(){
        String Q = "";

        setQuestion();
        getOperator();
        getNum();

        for(int i = 0; i < numbers.length;i++){
            System.out.print(numbers[i] + " " + qOperators[i] + " ");
            String x = Integer.toString(numbers[i]);
            Q = Q.concat(x).concat(qOperators[i]);
        }
        return Q;
    }

    private void setQuestion(){
        switch(difficulty){
            case 0:
                numbers = new int[2];
                break;
            case 1:
                int num1 = rng.nextInt(3-2 +1)+2; //max 3 ints, min 2 ints
                numbers = new int[num1];
                break;
            case 2:
                int num2 = rng.nextInt(4-2 +1)+2; //max 4 ints, min 2 ints
                numbers = new int[num2];
                break;
            case 3:
                int num3 = rng.nextInt(6-4 +1)+4; //max 6 ints, min 4 ints
                numbers = new int[num3];
                break;
        }
    }

    private void getOperator(){
        String[] operator ={"+", "-", "*", "/", "="};
        try {
            qOperators = new String[numbers.length];
        }catch(Exception e){
            System.out.println("numbers length = " + numbers.length);
        }
        for(int i = 0; i < qOperators.length-1;i++){
            qOperators[i]=operator[rng.nextInt(3 +1)];
        }
        qOperators[qOperators.length-1] = "=";
    }

    private void getNum(){
        for(int i = 0; i < numbers.length;i++){
            numbers[i]=rng.nextInt(10 - 1 +1)+1; //max 10, min 1
        }
    }

    public int getAnswer(){
        int questionAnswer;
        questionAnswer = Answer(numbers[0], qOperators[0], numbers[1]);
        for(int i = 2; i < numbers.length; i++){
            questionAnswer = Answer(questionAnswer, qOperators[i-1], numbers[i]);
        }
        return questionAnswer;
    }

    private int Answer(int num1, String op, int num2){
        int answer = 0;

        switch(op){
            case "+":
                answer = num1 + num2;
                return answer;
            case "-":
                answer = num1 - num2;
                return answer;
            case "*":
                answer = num1 * num2;
                return answer;
            case "/":
                double divide = (double)num1 / (double)num2;
                float divideAnswer = Math.round(divide);
                return (int)divideAnswer;
            case "=":
        }
        return answer;
    }
}


