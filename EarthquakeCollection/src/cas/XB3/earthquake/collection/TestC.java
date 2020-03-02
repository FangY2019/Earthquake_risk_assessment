package cas.XB3.earthquake.collection;

import java.util.Iterator;

public class TestC {
    public static void main(String[] args){
        EarthquakeBag <EarthquakeT> bag = new EarthquakeBag<EarthquakeT>();

        EarthquakeT m1 = new EarthquakeT("61 km E from Plaster Rock", EarthquakeT.ColorRating.BLUE, 2.2);
        EarthquakeT m2 = new EarthquakeT("26 km E from Isachsen", EarthquakeT.ColorRating.GREEN, 3.4);
        EarthquakeT m3 = new EarthquakeT("32 km SE from Pemberton", EarthquakeT.ColorRating.BLUE, 2.0);
        EarthquakeT m4 = new EarthquakeT("56 km NE from Banff", EarthquakeT.ColorRating.BLUE, 2.5);
        EarthquakeT m5 = new EarthquakeT("228 km SW from Atlin", EarthquakeT.ColorRating.BLUE, 3.4);
        EarthquakeT m6 = new EarthquakeT("227 km S from Isachsen", EarthquakeT.ColorRating.BLUE, 1.4);
        EarthquakeT m7 = new EarthquakeT("10 km NE from Isachsen", EarthquakeT.ColorRating.BLUE, 6.0);
        EarthquakeT m8 = new EarthquakeT("538 km S from Grand Bank", EarthquakeT.ColorRating.BLUE, 2.5);

        bag.add(m1);
        bag.add(m2);
        bag.add(m3);
        bag.add(m4);
        bag.add(m5);
        bag.add(m6);
        bag.add(m7);
        bag.add(m8);

        //This code iterates through the whole bag
        Iterator iterator = bag.iterator();

        while (iterator.hasNext()){
            EarthquakeT i = (EarthquakeT) iterator.next();
            System.out.print(i.getEarthquakePlace() + ", ");
        }

        System.out.println();

        //This code iterates through only blue color-coded earthquakes
        Iterator iteratorblue = bag.iterator(EarthquakeT.ColorRating.BLUE);

        while (iteratorblue.hasNext()){
            EarthquakeT i = (EarthquakeT) iteratorblue.next();
            System.out.print(i.getEarthquakePlace() + ", ");
        }

        System.out.println();

        System.out.println(bag.size());

        System.out.println(bag.sizeC(EarthquakeT.ColorRating.BLUE));

    }
}
