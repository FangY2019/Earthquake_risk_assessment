package cas.XB3.earthquake.collection;

import java.util.Iterator;

public class TestC {
    public static void main(String[] args){
        EarthquakeBag <EarthquakeT> bag = new EarthquakeBag<EarthquakeT>();

        EarthquakeT m1 = new EarthquakeT("Cat", EarthquakeT.ColorRating.BLUE, 2.2);
        EarthquakeT m2 = new EarthquakeT("Dog", EarthquakeT.ColorRating.GREEN, 3.4);
        EarthquakeT m3 = new EarthquakeT("Rat", EarthquakeT.ColorRating.BLUE, 2.0);
        EarthquakeT m4 = new EarthquakeT("Bird", EarthquakeT.ColorRating.BLUE, 2.5);
        EarthquakeT m5 = new EarthquakeT("Snake", EarthquakeT.ColorRating.BLUE, 3.4);
        EarthquakeT m6 = new EarthquakeT("Ant", EarthquakeT.ColorRating.BLUE, 1.4);
        EarthquakeT m7 = new EarthquakeT("Elephant", EarthquakeT.ColorRating.BLUE, 6.0);
        EarthquakeT m8 = new EarthquakeT("Bird", EarthquakeT.ColorRating.BLUE, 2.5);

        bag.add(m1);
        bag.add(m2);
        bag.add(m3);
        bag.add(m4);
        bag.add(m5);
        bag.add(m6);
        bag.add(m7);
        bag.add(m8);

        Iterator iterator = bag.iterator(EarthquakeT.ColorRating.BLUE);

        while (iterator.hasNext()){
            EarthquakeT i = (EarthquakeT) iterator.next();
            System.out.print(i.getEarthquakePlace() + " ");
        }

        System.out.println();

        System.out.println(bag.size());

        System.out.println(bag.sizeC(EarthquakeT.ColorRating.BLUE));

    }
}
