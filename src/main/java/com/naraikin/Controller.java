package com.naraikin;

import com.naraikin.models.Counter;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by dmitrii on 09.02.17.
 *
 * Управляет созданием потоков для чтения файлов
 */
public class Controller {

    public static final Logger logger = Logger.getLogger(Controller.class);
    static {
        DOMConfigurator.configure("src/main/resources/log4j.xml");
    }
    private String[] resourcesArray;
    private List<Thread> threads = new ArrayList<>();
    private Counter counter = new Counter();

    /**
     * Class constructor
     * @param resourceStrings
     */
    public Controller(String[] resourceStrings) {
        this.resourcesArray = resourceStrings;
        logger.trace("Контроллер создан с " + resourceStrings.length + " ресурсами");
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
        logger.trace("Запуск потоков в массиве");
    }

    /**
     * Присоединяет массив потоков к главному потоку
     */
    private void joinThreads() {
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                logger.error("InterruptedException");
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
        logger.trace("Создан массив потоков");
    }

    /**
     * Запуск поток
     */
    public void run(){
       createThreads();
       startThreads();
       joinThreads();
       if(!counter.isError()){
           logger.trace("Выполнение завершено успешно");
       }

    }
}
