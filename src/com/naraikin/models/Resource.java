package com.naraikin.models;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;

/**
 *  Модель ресурса
 */
public class Resource {

    /**
     *  Переменная хранит строковый путь к файлу
     */
    private String resourceString;

    /**
     * Метод возвращает строковый путь к файлу
     * @return строка путь к файлу
     */
    public String getResourceString() {
        return resourceString;
    }

    /**
     * Конструктор класс ресурса
     * @param resString
     */
    public Resource(String resString) {
        resourceString = resString;
    }

    /**
     * Метод создает bufferedReader для полученной текстовой строки
     * в конструкторе
     *
     * @return BufferedReader Возвращает поток
     * @throws IOException
     */
    public BufferedReader getBufferedReader() throws IOException{

        return new BufferedReader(
                new InputStreamReader(new FileInputStream(resourceString)));
    }

    /**
     * Метод проверяет существования файла на диске
     *
     * @param filePath Путь к файлу на диске
     * @return
     */
    public static boolean validateFilePath(String filePath)  {
        try {
            Paths.get(filePath);
        } catch (InvalidPathException ex) {
                return false;
        }
        return true;
    }
}
