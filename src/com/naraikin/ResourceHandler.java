package com.naraikin;

import com.naraikin.models.Counter;
import com.naraikin.models.Parser;
import com.naraikin.models.Resource;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by dmitrii on 09.02.17.
 *
 * Обработчик ресурса в потоках
 */
public class ResourceHandler extends Thread {

    private Counter counter;
    private Resource resource;

    /**
     * Конструктор обработчика потоков
     * Создает объект ресурса
     *
     * @param ctr   Сумматор
     * @param resourceString    Путь к файлу
     */
    public ResourceHandler(Counter ctr, String resourceString) {
        counter = ctr;
        resource = new Resource(resourceString);
    }

    /**
     * В методе происходит чтение построчно из BufferedReader
     * и проверка поднятия флага в Counter
     *
     * В случае появление невалидных данных выбрасывается исключение
     * и поднятие флаги ошибки в Counter
     *
     */
    @Override
    public void run() {

            try (BufferedReader bufferedInputStream = resource.getBufferedReader()){
                String line;
                while (((line = bufferedInputStream.readLine()) != null)
                        && (!counter.isError())) {
                    int sumInString = Parser.getSum(line);

                    if(!counter.isError()) {
                    counter.addValueAndPrint(sumInString);
                    /*
                        System.out.println(counter.addValue(sumInString) +
                                " -- " + Thread.currentThread().getName() +
                                " -- " + resource.getResourceString()
                        );*/
                    }
                }
            } catch (NumberFormatException ex) {
                counter.setError();
                System.out.println("Not valid symbol "
                        + ex.getMessage() + " in resource " + resource.getResourceString() );
            } catch (IOException ex) {
                counter.setError();
                System.out.println("IO Error: " + ex.getMessage() +" in "  + resource.getResourceString());
            }
    }
}
