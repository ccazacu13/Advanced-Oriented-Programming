package Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

public class Service {
    public static int get_int (Scanner scanner, String open, String err)
    {
        boolean check = true;
        int value = -1;
        while(check)
        {
            try{
                System.out.print(open);
                value = Integer.parseInt(scanner.nextLine());
                check = false;

            }
            catch(NumberFormatException e)
            {
                System.out.println(err);
            }
        }

        return value;
    }

    public static Date get_date (Scanner scanner, String open, String err)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        boolean check = true;
        Date value = new Date();

        while(check) {
            try{
                System.out.print(open);
                String strDate = scanner.nextLine();
                value = dateFormat.parse(strDate);
                check = false;
            }
            catch (ParseException e)
            {
                System.out.println(err);
            }
        }

        return value;
    }
}
