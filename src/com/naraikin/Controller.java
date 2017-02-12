package com.naraikin;

import com.naraikin.models.Counter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by dmitrii on 09.02.17.
 *
 * Управляет созданием потоков для чтения файлов
 */
public class Controller {

    private String[] resourcesArray;
    private List<Thread> threads = new ArrayList<>();
    private Counter counter = new Counter();

    /**
     * Class constructor
     * @param resourceStrings
     */
    public Controller(String[] resourceStrings) {
        this.resourcesArray = resourceStrings;
    }

    /**
     * Возвращение объекта Counter
     * @return Counter
     */
    public Counter getCounter() {
        return counter;
    }

    /**
     * Запускает массив потоки
     */
    private void startThreads() {
        for (Thread thread : threads) {
            thread.start();
        }
    }

    /**
     * Присоединяет массив потоков к главному потоку
     */
    private void joinThreads() {
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Создает массив содержащий потоки
     */
    private void createThreads() {
        for (String res : resourcesArray) {
            Thread t = new ResourceHandler(counter, res);
            threads.add(t);
        }
    }

    /**
     * Запуск поток
     */
    public void run(){
       createThreads();
       startThreads();
       joinThreads();
    }
}
