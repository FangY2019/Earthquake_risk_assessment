package cas.XB3.earthquake.collection;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import cas.XB3.earthquake.collection.EarthquakeT.*;
import java.util.Iterator;

public class CSVreader {

    public static void readEarthquakes(String filename, EarthquakeBag<EarthquakeT> bag){
        String singleL;
        try {
            BufferedReader bufferedR = new BufferedReader(new FileReader(filename));
            String columnN = bufferedR.readLine();

            while ((singleL = bufferedR.readLine()) != null){
                String[] cell = singleL.split(",");
                cell[0] = cell[0].substring(0, 19);
                DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
                LocalDateTime date = LocalDateTime.parse(cell[0], formatter);
                double cell1 = Double.parseDouble(cell[1]);
                double cell2 = Double.parseDouble(cell[2]);
                double cell3 = Double.parseDouble(cell[3]);
                double cell4 = Double.parseDouble(cell[4]);
                MagType cell5 = MagType.BLANK;
                if (!cell[5].equals(""))
                    cell5 = MagType.valueOf(cell[5]);

                ColorRating clRating = generateColorRating(cell4);
                EarthquakeT eqk = new EarthquakeT(cell[6], date, cell1, cell2, cell3, cell4, cell5, clRating);
                bag.add(eqk);
            }

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private static ColorRating generateColorRating(double cell4) {
        ColorRating clRating = ColorRating.NOCOLOR;
        if (cell4 == 0)
            clRating = ColorRating.ZERO;
        else if (3 <= cell4 && cell4 <= 3.9)
            clRating = ColorRating.PURPLE;
        else if (4 <= cell4 && cell4 <= 4.9)
            clRating = ColorRating.BLUE;
        else if (5 <= cell4 && cell4 <= 5.9)
            clRating = ColorRating.GREEN;
        else if (6 <= cell4 && cell4 <= 6.9)
            clRating = ColorRating.YELLOW;
        else if (7 <= cell4 && cell4 <= 7.9)
            clRating = ColorRating.ORANGE;
        else if (8 <= cell4)
            clRating = ColorRating.RED;

        return clRating;
    }

    public static void main(String[] args){
        EarthquakeBag <EarthquakeT> bag2 = new EarthquakeBag<EarthquakeT>();
        readEarthquakes("./eqarchive-en.csv", bag2);
        Iterator iterator = bag2.iterator();

        while (iterator.hasNext()){
            EarthquakeT i = (EarthquakeT) iterator.next();
            System.out.println(i.getEarthquakePlace());
        }

        System.out.println(bag2.size());
    }
}
