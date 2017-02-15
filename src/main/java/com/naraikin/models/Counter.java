package com.naraikin.models;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by dmitrii on 09.02.17.
 *
 *  Класс хранящий сумму чисел
 *  с блоками synchronized
 */
public class Counter {
    private AtomicLong value = new AtomicLong(0);
    private AtomicBoolean error = new AtomicBoolean(false);

    /**
     * Возвращает флаг ошибки потоков
     * @return  Если флаг установлен в true, в неком потоке произошла
     */
    public synchronized boolean isError() {

        return error.get();
    }

    /**
     * Устанавливает флаг ошибки
     */
    public synchronized void setError() {
        error.set(true);
    }

    /**
     * Метод принимает значение для сложения с текущим результатом
     * и возвращает получившуюся сумму
     *
     * @param val
     * @return  Возвращает результат сложения val с value
     */
    public synchronized long addValue(long val) {
        return value.addAndGet(val);
    }

    /**
     * Метод принимает значение для сложения с текущим результатом
     * и возвращает получившуюся сумму
     *
     * @param val
     * @return  Возвращает результат сложения val с value
     */
    public synchronized void addValueAndPrint(long val) {
        value.addAndGet(val);
        this.printer();
    }

    /**
     * Возвращает сумму
     * @return
     */
    public synchronized long getValue() {
        return value.get();
    }

    /**
     * Вывод на консоль текущей суммы
     */
    public synchronized void printer() {
        System.out.println(value.get());
    }

}
