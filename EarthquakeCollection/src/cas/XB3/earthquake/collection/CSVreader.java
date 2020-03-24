package cas.XB3.earthquake.collection;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import cas.XB3.earthquake.ADT.CityPostT;
import cas.XB3.earthquake.ADT.CityT;
import cas.XB3.earthquake.collection.EarthquakeT.*;

import java.util.ArrayList;
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
                if (!cell[5].isEmpty())
                    cell5 = MagType.valueOf(cell[5]);

                ColorRating clRating = generateColorRating(cell4);
                
                //extract the city name from the string
                if(cell[6].contains(" from ")) {
                	int i = cell[6].indexOf('f');
                	cell[6] = cell[6].substring(i+ 5);
                }else if(cell[6].contains(" of ")) {
                	int i = cell[6].indexOf('o');
                	cell[6] = cell[6].substring(i + 3);                	
                }else if(cell[6].startsWith("NEAR")) {
                	cell[6] = cell[6].substring(5);  
                }
                
                
                EarthquakeT eqk = new EarthquakeT(cell[6], date, cell1, cell2, cell3, cell4, cell5, clRating);
                bag.add(eqk);
            }

            bufferedR.close();

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void readEarthquakesBST(String filename, RedBlackBST<Double, EarthquakeT> bst){
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
                if (!cell[5].isEmpty())
                    cell5 = MagType.valueOf(cell[5]);

                ColorRating clRating = generateColorRating(cell4);

                //extract the city name from the string
                if(cell[6].contains(" from ")) {
                    int i = cell[6].indexOf('f');
                    cell[6] = cell[6].substring(i+ 5);
                }else if(cell[6].contains(" of ")) {
                    int i = cell[6].indexOf('o');
                    cell[6] = cell[6].substring(i + 3);
                }else if(cell[6].startsWith("NEAR")) {
                    cell[6] = cell[6].substring(5);
                }


                EarthquakeT eqk = new EarthquakeT(cell[6], date, cell1, cell2, cell3, cell4, cell5, clRating);
                bst.put(cell1, eqk);
            }

            bufferedR.close();

        } catch (IOException e){
            e.printStackTrace();
        }
    }
    
    public static void readPopulation(String filename, GeoCollection geoCollec){
        String singleL;
        try {
            BufferedReader bufferedR = new BufferedReader(new FileReader(filename));
            String columnN = bufferedR.readLine();

            while ((singleL = bufferedR.readLine()) != null){
                String[] cell = singleL.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
//                for(String str: cell) {
//                	System.out.print(str + " ");
//                }
//                System.out.println();

                cell[1] = rmFirstLastQuote(cell[1]);
                cell[5] = rmFirstLastQuote(cell[5]);

                Double cell26 = 0.0;
                if (!cell[26].isEmpty())
                    cell26 = Double.parseDouble(cell[26]);

                CityT loc = new CityT(cell[1], cell[5], cell26);
                geoCollec.add(loc);
            }

            bufferedR.close();

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * read the each city name, latitude and longitude, instantiate a CityPostT object and add all the objects in a ArrayList
     * @param filename the file name which store the cities' coordinates
     * @param cityPostList A ArrayList of CityPostT
     */
    public static void readCityPosition(String filename, ArrayList<CityPostT> cityPostList){
    	try {
			BufferedReader buffered = new BufferedReader(new FileReader(filename));
			String line = buffered.readLine();;
			while ((line = buffered.readLine()) != null) {
				String[] cell = line.split(",");
				cell[0] = cell[0].split(";")[0];			
				double lat = Double.parseDouble(cell[1]);
				double longi = Double.parseDouble(cell[2]);
				cityPostList.add(new CityPostT(cell[0], lat, longi));
			}
			buffered.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }	
	
    public static String rmFirstLastQuote(String cell){
        String cellq = cell;
        if (cell.startsWith("\"") && cell.endsWith("\""))
            cellq = cellq.substring(1, cellq.length()-1);
        return cellq;
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

        EarthquakeBag<EarthquakeT> EarthquakeBag = new EarthquakeBag<EarthquakeT>();
        RedBlackBST<Double, EarthquakeT> eqBST = new RedBlackBST<Double, EarthquakeT>();

        CSVreader.readEarthquakes("./eqarchive-en.csv", EarthquakeBag);
        CSVreader.readEarthquakesBST("./eqarchive-en.csv", eqBST);

        for(Double lat: eqBST.keys()){
            //System.out.println(lat);
        }

        for(Double lat: eqBST.keys(52.0, 61.0)){
            for(EarthquakeT eq : eqBST.get(lat))
                System.out.print(eq.getPlace() + "            ");
            System.out.println();
        }

        Iterator iterator = EarthquakeBag.iterator();
        while (iterator.hasNext()){
            EarthquakeT i = (EarthquakeT) iterator.next();
            //System.out.println(i.getEarthquakePlace());
        }

    }
}