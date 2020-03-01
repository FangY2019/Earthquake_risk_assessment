package cas.XB3.earthquake.collection;

public class TestC {
    public static void main(String[] args){
        MarbleBag<Marble> bag = new MarbleBag<Marble>();

        Marble m1 = new Marble("Cat", Marble.Color.BLUE, 2.2);
        Marble m2 = new Marble("Dog", Marble.Color.BLACK, 3.4);
        Marble m3 = new Marble("Rat", Marble.Color.BLUE, 2.0);
        Marble m4 = new Marble("Bird", Marble.Color.BLUE, 2.5);
        Marble m5 = new Marble("Snake", Marble.Color.PINK, 3.4);
        Marble m6 = new Marble("Ant", Marble.Color.WHITE, 1.4);
        Marble m7 = new Marble("Elephant", Marble.Color.WHITE, 6.0);
        Marble m8 = new Marble("Bird", Marble.Color.BLUE, 2.5);

        bag.add(m1);
        bag.add(m2);
        bag.add(m3);
        bag.add(m4);
        bag.add(m5);
        bag.add(m6);
        bag.add(m7);
        bag.add(m8);

        System.out.println(bag.size());

        bag.del(m8);

        System.out.println(bag.size());

        System.out.println(bag.sizeC(Marble.Color.BLUE));

        bag.delc(Marble.Color.BLUE);

        System.out.println(bag.sizeC(Marble.Color.BLUE));

    }
}
