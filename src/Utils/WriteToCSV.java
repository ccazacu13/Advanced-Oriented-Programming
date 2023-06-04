package Utils;

import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class WriteToCSV {
    private static final String file = "src\\audit.csv";

    public static void write(String action)
    {
        try {
//            Scanner in = new Scanner(new File("src\\audit.csv"));
            LocalDateTime timestamp = LocalDateTime.now();

            // Define the desired timestamp format
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            // Format the timestamp as a string
            String timestampAsString = timestamp.format(formatter);

            FileWriter fileWriter = new FileWriter(file, true);
            fileWriter.write("\n" + action + "," + timestampAsString);
            fileWriter.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }


}
