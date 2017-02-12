package utils;

import java.io.*;

/**
 * Created by dmitrii on 08.02.17.
 */
public class FileGenerator {

    public static void main(String[] args) {

        for (int i = 1; i<= 10; i++){
            Thread t = new FillFile("files/file"+i+".txt");
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public static void createAndWriteFile(String fileName){
        try(FileOutputStream out=new FileOutputStream(fileName);
            PrintWriter pw = new PrintWriter(out))
        {
            for (int i = -99; i <=1000000; i++){
                if(i%10 == 0){
                    pw.println(i+" ");

                }
                pw.print(i+"  ");

            }
            pw.flush();
            System.out.println("Сгенерирован файл "+ fileName);

        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }
}
