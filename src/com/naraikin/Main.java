package com.naraikin;

import java.io.File;

public class Main {

    public static void main(String[] args) {

        File folder = new File("files");
        File[] listOfFiles = folder.listFiles();
        String[] files = new String[listOfFiles.length];
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile())
                files[i] = "files/" + listOfFiles[i].getName();
        }

        Controller controller = new Controller(files);
        controller.run();
        System.out.println(controller.getCounter().isError() ? "<<<Error>>>" : "Sum: " + controller.getCounter().getValue() );
    }
}
