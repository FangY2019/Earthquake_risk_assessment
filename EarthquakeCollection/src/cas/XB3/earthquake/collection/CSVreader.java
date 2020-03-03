package cas.XB3.earthquake.collection;

import sun.nio.cs.ISO_8859_2;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class CSVreader {

    public static void readEarthquakes(String filename){
        String singleL;
        try {
            BufferedReader bufferedR = new BufferedReader(new FileReader(filename));
            String columnN = bufferedR.readLine();

            while ((singleL = bufferedR.readLine()) != null){
                String[] cell = singleL.split(",");
                cell[0] = cell[0].substring(0, 19);
                DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
                LocalDateTime date = LocalDateTime.parse(cell[0], formatter);
                System.out.println(date);
            }

        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        readEarthquakes("./eqarchive-en.csv");
    }
}
