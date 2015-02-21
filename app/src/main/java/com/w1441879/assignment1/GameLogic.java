package com.w1441879.assignment1;

import java.util.Random;

public class GameLogic {

    Random rng = new Random();
    private String[] operator ={"+", "-", "*", "/", "="};
    //String qEnd = " = ?";
    //int num1,num2,num3, num4, num5,num6;
    //private int difficulty = 0;
    int[] numbers;
    String[] qOperators;

    GameLogic(int diff){
        //int difficulty = diff;

        switch(diff){
            case 0:
                numbers = new int[2];
                break;
            case 1:
                int num1 = rng.nextInt(3-2 +1)+2; //max 3 ints, min 2 ints
                //System.out.println(num1);
                numbers = new int[num1];
                break;
            case 2:
                int num2 = rng.nextInt(4-2 +1)+2; //max 4 ints, min 2 ints
                //System.out.println(num2);
                numbers = new int[num2];
                break;
            case 3:
                int num3 = rng.nextInt(6-4 +1)+4; //max 6 ints, min 4 ints
                //System.out.println(num3);
                numbers = new int[num3];
                break;
        }

    }

    public String createQuestion(){
        String Q = "";
        //System.out.println("getOperator");
        getOperator();
        //System.out.println("getNum");
        getNum();
        //System.out.println(" ");
        for(int i = 0; i < numbers.length;i++){
            System.out.print(numbers[i] + " " + qOperators[i] + " ");
            String x = Integer.toString(numbers[i]);
            //System.out.println("x: "+ x);
            Q = Q.concat(x).concat(qOperators[i]);
        }
        return Q;
        /*int x =0;
        int answer = Calculate(numbers[x], qOperators[x],numbers[x+1]);
        x++;
        answer =+ Calculate(answer, qOperators[x], numbers[x+1]);

        System.out.println(answer);*/

    }


    private void getOperator(){
        qOperators = new String[numbers.length];
        //int x = rng.nextInt(3 +1);
        //String op = operator[x];

        for(int i = 0; i < qOperators.length-1;i++){
            qOperators[i]=operator[rng.nextInt(3 +1)];
            //qOperators[i]=operator[0];
            //System.out.println("index: "+ (i+1) + " " + qOperators[i]);
        }

        qOperators[qOperators.length-1] = "=";

    }

    private void getNum(){
        for(int i = 0; i < numbers.length;i++){
            numbers[i]=rng.nextInt(10 - 1 +1)+1; //max 10, min 1
            //System.out.println("index: "+ (i+1) + " " + numbers[i]);
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
                answer = num1 / num2;
                return answer;
            case "=":
        }


        return answer;
    }

}


