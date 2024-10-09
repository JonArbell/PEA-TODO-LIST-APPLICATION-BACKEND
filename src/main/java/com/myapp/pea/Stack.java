package com.myapp.pea;

import java.util.Arrays;

public class Stack {

    public static int[] stack = new int[5];
    public static int peak = -1;

    public static boolean isEmpty(){
        return peak == -1;
    }

    public static long size(){
        return Arrays.stream(stack).filter(num -> num != 0).count();
    }

    public static int top(){

        if(!isEmpty())
            return stack[peak];

        return 0;
    }

    public static boolean overflow(){
        return peak >= (stack.length - 1);
    }

    public static void push(int num){

        if(!overflow()){
            stack[++peak] = num;
        }else {
            System.out.println("Stack Overflow!");
        }

    }

    public static void pop(){

        if(!isEmpty()){
            stack[peak] = 0;
            peak--;
        }else{
            System.out.println("Stack Underflow!");
        }

    }

    public static void main(String[] args) {
        push(5);
        push(2);
        push(3);
        push(7);
        push(12);
        System.out.println(top());
    }

}
