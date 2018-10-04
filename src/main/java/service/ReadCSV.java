package service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadCSV {

    public void writeCSV() {

        try {
            String xmlFile = "src/main/webapp/resources/file.csv";
            String absolutePath = new File("").getAbsolutePath();
            String xmlFilePath = absolutePath + File.separator + xmlFile;

            File fXmlFile = new File(xmlFilePath);
            Scanner scanner = new Scanner(fXmlFile);
            scanner.useDelimiter(",");
            while (scanner.hasNext()) {
                System.out.print(scanner.next() + "|");

            }
            scanner.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }


}
