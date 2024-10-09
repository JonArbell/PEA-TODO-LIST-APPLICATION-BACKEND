package com.myapp.pea;

public class Queue {

    public static int[] queue = new int[5];

    public static int front = -1;
    public static int back = -1;

    public static boolean isEmpty(){
        return front == -1;
    }

    public static int top(){

        if(!isEmpty())
            return queue[back];

        return 0;
    }

    public static boolean isOverflow(){
        return (queue.length - 1) == back;
    }

    public static void enqueue(int num){

        if(isEmpty()){
            queue[++front] = num;
            ++back;

            System.out.println("Enqueue : "+queue[back]);

        }else if(!isOverflow()){
            queue[++back] = num;
            System.out.println("Enqueue : "+queue[back]);
        }else {
            System.out.println("Queue Overflow!");
        }

    }

    public static void dequeue(){
        if(!isEmpty()){

            if(front == back){
                front = -1;
                back = -1;
                return;
            }
            System.out.println("Dequeue : "+queue[front]);
            queue[front] = 0;
            ++front;

        }else {
            System.out.println("Queue underflow!");
        }
    }

    public static void main(String[] args) {

        enqueue(5);
        enqueue(10);
        enqueue(15);
        enqueue(20);
        enqueue(25);

        dequeue();
        dequeue();
        dequeue();
        dequeue();

        enqueue(35);

        System.out.println(queue[front]);

    }

}
