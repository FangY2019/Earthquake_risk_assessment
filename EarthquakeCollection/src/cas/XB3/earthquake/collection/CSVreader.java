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

                if (!cell[7].equals("BLANK"))
                    cell[7] = fullProvName(cell[7]);
                if (cell[7].endsWith(" Felt") || cell[7].endsWith(" Felt ") || cell[7].endsWith(" Felt."))
                    cell[7] = cell[7].substring(0, cell[7].length() - 5);

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
                
                
                EarthquakeT eqk = new EarthquakeT(cell[6], cell[7], date, cell1, cell2, cell3, cell4, cell5, clRating);
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

                if (!cell[7].equals("BLANK"))
                    cell[7] = fullProvName(cell[7]);
                if (cell[7].endsWith(" Felt") || cell[7].endsWith(" Felt ") || cell[7].endsWith(" Felt."))
                    cell[7] = cell[7].substring(0, cell[7].length() - 5);

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


                EarthquakeT eqk = new EarthquakeT(cell[6], cell[7], date, cell1, cell2, cell3, cell4, cell5, clRating);
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

    private static String fullProvName(String nameP){
        String newPN = nameP;
        if(nameP.length() == 2) {
            switch (nameP) {
                case "ON":
                    newPN = "Ontario";
                    break;
                case "QC":
                case "PQ":
                    newPN = "Quebec";
                    break;
                case "NS":
                    newPN = "Nova Scotia";
                    break;
                case "NB":
                    newPN = "New Brunswick";
                    break;
                case "MB":
                    newPN = "Manitoba";
                    break;
                case "B.":
                case "BC":
                    newPN = "British Columbia";
                    break;
                case "PE":
                    newPN = "Prince Edward Island";
                    break;
                case "SK":
                    newPN = "Saskatchewan";
                    break;
                case "AB":
                    newPN = "Alberta";
                    break;
                case "NL":
                    newPN = "Newfoundland and Labrador";
                    break;
                case "NU":
                    newPN = "Nunavut";
                    break;
                case "NT":
                    newPN = "Northwest Territories";
                    break;
                case "YT":
                    newPN = "Yukon";
                    break;
                case "AK":
                    newPN = "Alaska";
                    break;
                case "WA":
                    newPN = "Washington";
                    break;
                default:
                    newPN = "UNLOCATED";
                    break;
            }
        } else if(nameP.startsWith("LOWER ST. LAWRENCE") || nameP.startsWith("northern Quebec") || nameP.startsWith("NORTHERN QUEBEC") || nameP.startsWith("Quebec") || nameP.startsWith("QC") || nameP.startsWith("QUE") || nameP.startsWith("Que")) {
            newPN = "Quebec";
        } else if(nameP.startsWith("ON") || nameP.startsWith("ONT") || nameP.startsWith("Ont")) {
            newPN = "Ontario";
        } else if(nameP.startsWith("Alberta.") || nameP.startsWith("AB")) {
            newPN = "Alberta";
        } else if(nameP.startsWith("BC.") || nameP.startsWith("B.C.")) {
            newPN = "British Columbia";
        } else if(nameP.startsWith("NEW BRUNSWICK") || nameP.startsWith("NB.") || nameP.startsWith("N.B.")) {
            newPN = "New Brunswick";
        } else if(nameP.startsWith("Nun")) {
            newPN = "Nunavut";
        } else if(nameP.startsWith("US.") || nameP.startsWith("USA") || nameP.startsWith("U.S.")) {
            newPN = "USA";
        } else if(nameP.startsWith("YT.") || nameP.startsWith("Y.T.") || nameP.startsWith("Yukon") || nameP.startsWith("YUKON")) {
            newPN = "Yukon";
        } else if(nameP.startsWith("Wa") || nameP.startsWith("WA") || nameP.startsWith("WASH") || nameP.startsWith("Wash")) {
            newPN = "Washington";
        } else if(nameP.startsWith("SASKATCHEWAN.")) {
            newPN = "Saskatchewan";
        } else if(nameP.startsWith("NEW YORK") || nameP.startsWith("N.Y.")) {
            newPN = "New York";
        } else if(nameP.startsWith("MAINE")) {
            newPN = "Maine";
        } else if(nameP.startsWith("Montana") || nameP.startsWith("MONTANA")) {
            newPN = "Montana";
        } else if(nameP.startsWith("NFLD")) {
            newPN = "Newfoundland and Labrador";
        } else if(nameP.startsWith("Alaska.") || nameP.startsWith("AK") || nameP.startsWith("ALASKA")) {
            newPN = "Alaska";
        } else if(nameP.startsWith("NT.") || nameP.startsWith("N.W.T.")) {
            newPN = "Northwest Territories";
        } else {
            switch (nameP) {
                case "U.S":
                case "U.S.A":
                case "northern U.S.A.":
                case "U.S.A.":
                    newPN = "USA";
                    break;
                case "NEW YORK":
                case "NY.":
                    newPN = "New York";
                    break;
                case "near the ma":
                    newPN = "Massachusetts";
                    break;
                case "CLERMONT":
                    newPN = "Clearmont";
                    break;
                case "Y.T.":
                    newPN = "Yukon";
                    break;
                case "N.W.T":
                case "NWT":
                case "N.W.T.":
                    newPN = "Northwest Territories";
                    break;
                case "N.S.":
                    newPN = "Nova Scotia";
                    break;
                case "QUEBEC":
                case "que.":
                case "lower st.lawrence":
                case "MONT-LAURIER":
                case "NOTHERN QUEBEC":
                case "NORTHERN QUE.":
                case "Northern Quebec.":
                case "WESTERN QUEBEC SEISMIC ZONE.":
                case "Lower Quebec North Shore.":
                case "SOUTHERN QUEBEC REGION. Felt":
                case "Lower St. Lawrence":
                case "Q":
                case "CHARLEVOIX SEISMIC ZONE":
                case "SOUTHERN QUEBEC":
                    newPN = "Quebec";
                    break;
                case "B.C":
                case "BC-Alaska border.":
                case "Alaska - B.C. border.":
                case "near Sidney":
                case "NEAR SIDNEY":
                case "near Victoria":
                case "VANCOUVER ISLAND":
                case "near Nanaimo":
                case  "EAST of SIDNEY":
                    newPN = "British Columbia";
                    break;
                case "CENTRAL N.B.":
                case "Central N.B.":
                    newPN = "New Brunswick";
                    break;
                case "ALBERTA.":
                    newPN = "Alberta";
                    break;
                case "Alaska.":
                case "EAST of JUNEAU.":
                case "SOUTHEASTERN ALASKA.":
                case "SOUTHEASTERN":
                    newPN = "Alaska";
                    break;
                default:
                    newPN = "UNLOCATED";
                    break;
            }
        }
        return newPN;
    }
}