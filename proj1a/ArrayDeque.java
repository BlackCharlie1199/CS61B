import javax.swing.*;

public class ArrayDeque<T>{
    private int size;
    private T[] item;
    private int firstIndex;
    private int lastIndex;

    public ArrayDeque() {
        item = (T[]) new Object[8];
        size = 0;
        firstIndex = 7;
        lastIndex = 0;
    }

    public void addLast(T x) {
        if (size == item.length){
            resize(size * 2);
        }
        item[lastIndex] = x;
        lastIndex = (lastIndex + 1) % item.length;
        size += 1;
    }

    public void addFirst(T x) {
        if (size == item.length){
            resize(size * 2);
        }
        item[firstIndex] = x;
        firstIndex = (firstIndex - 1 + item.length) % item.length;
        size += 1;
    }

    public T removeLast() {
        T x = get(size - 1);
        lastIndex = (lastIndex - 1 + item.length) % item.length;
        item[lastIndex] = null;
        size = Math.max(size-1, 0);

        if (item.length / size >= 4) {
            resize(size);
        }
        return x;
    }

    public T removeFirst() {
        T x = get(0);
        firstIndex = (firstIndex + 1) % item.length;
        item[firstIndex] = null;
        size = Math.max(size-1, 0);

        if (item.length / size >= 4) {
            resize(size);
        }
        return x;
    }

    public T get(int index){
        int realIndex = (firstIndex + index + 1) % item.length;
        return item[realIndex];
    }

    public int size() {
        return size;
    }

    private void resize(int resize) {
        T[] a = (T[]) new Object[resize];
        for (int i = 0; i < size; i++) {
            int index = (firstIndex + i + 1) % size;
            a[i] = item[index];
        }
        item = a;
        firstIndex = item.length - 1;
        lastIndex = size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void printDeque() {
        for(int i = 0; i < size; i++) {
            int index = (firstIndex + i + 1) % item.length;
            System.out.print(item[index]);
            System.out.print(" ");
        }
    }
}