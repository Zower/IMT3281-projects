/**
 * This code is part of a previous assignment, and is only here due to the requirement for the Snakes and Ladders application to be launched from the Game Center.
 * As such I don't consider it part of "Project 1", nor do I hold it to the same standards as the files related to snakesAndLadders.
 */

package Project1.src;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

public class ReadWriteToFile {

    public ReadWriteToFile() {

    }

    public void writeOutput(int record) {
        try {
            try (FileWriter outputWriter = new FileWriter("record.txt", false)) {
                outputWriter.write(record);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public int readRecord() {
        int record = 0;
        try {
            String path = System.getProperty("user.dir") + "\\IMT3881\\record.txt";
            File file = new File(path);
            Scanner scanner = new Scanner(file);

            record = scanner.nextInt();
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return record;
    }
}
