package cas.XB3.earthquake.collection;

import java.util.Iterator;

public class GeoCollection<Item> implements Iterable<Item>{
    private Item[] itemArray;
    private int size;

    public GeoCollection(){
        itemArray = (Item[]) new Object[5162];
    }

    public void add(Item item){
        itemArray[size++] = item;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size() {
        return size;
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private int i = 0;

        public boolean hasNext() {
            return i < 5162;
        }

        public Item next() {
            return itemArray[i++];
        }
    }
}
