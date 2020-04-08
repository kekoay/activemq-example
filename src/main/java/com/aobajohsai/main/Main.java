package com.aobajohsai.main;

import com.aobajohsai.producer.Producer;
import com.aobajohsai.consumer.Consumer;

public class Main {
    public static void main(String [] args){
        Producer producer = new Producer();
        Consumer consumer = new Consumer();

        Thread producerThread = new Thread(producer);
        producerThread.start();

        Thread consumerThread = new Thread(consumer);
        consumerThread.start();
    }
}
