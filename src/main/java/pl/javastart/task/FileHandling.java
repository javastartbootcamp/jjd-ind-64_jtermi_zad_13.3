package pl.javastart.task;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandling {

    public static ArrayList<String> readFileToList(File file) {
        ArrayList<String> listOfLines = new ArrayList<>();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                listOfLines.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Nie można odczytać pliku");
        }
        return listOfLines;
    }

}
