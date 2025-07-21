public class LinkedListDeque<T> implements Deque<T> {
    private int size;
    private node sentinel;

    private class node {
        /** Create the node of list s.t. we don't need to
         *  understand how linked list is worked when we call LinkedListDeque
         */
        public T item;
        public node nextNode;
        public node previousNode;

        public node(T x, node p, node n) {
            item = x;
            nextNode = n;
            previousNode = p;
        }

        public T findRecursive(int index) {
            if (index == 0){
                return this.item;
            }
            return this.findRecursive((index-1));
        }
    }

    /* return a empty node i.e. the list only contains sentinel node itself */
    public LinkedListDeque() {
        sentinel = new node(null, null, null);
        sentinel.nextNode = sentinel;
        sentinel.previousNode = sentinel;
        size = 0;
    }

    @Override
    /* Add a node at first position */
    public void addFirst (T item) {
        node newNode = new node(item, sentinel, sentinel.nextNode);
        sentinel.nextNode.previousNode = newNode;
        sentinel.nextNode = newNode;
        size += 1;
    }

    @Override
    /* Add a node at last position */
    public void addLast (T item) {
        node newNode = new node(item, sentinel.previousNode, sentinel);
        sentinel.previousNode.nextNode = newNode;
        sentinel.previousNode = newNode;
        size += 1;
    }

    @Override
    public T removeFirst() {
        node firstNode = sentinel.nextNode;
        T x = firstNode.item;
        sentinel.nextNode = firstNode.nextNode;
        sentinel.nextNode.previousNode = sentinel;
        firstNode = null;
        size = Math.max(size-1, 0);
        return x;
    }

    @Override
    public T removeLast() {
        node lastNode = sentinel.previousNode;
        T x = lastNode.item;
        sentinel.previousNode = lastNode.previousNode;
        sentinel.previousNode.nextNode = sentinel;
        lastNode = null;
        size = Math.max(size-1, 0);
        return x;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        node p = sentinel.nextNode;
        while (p!=sentinel) {
            System.out.print(p.item);
            System.out.print(" ");
            p = p.nextNode;
        }
    }

    @Override
    /* use iteration */
    public T get(int index) {
        node p = sentinel.nextNode;
        while (index > 0){
            p = p.nextNode;
            index -= 1;
        }
        return p.item;
    }

    /* use recursion */
    public T getRecursive(int index) {
        if (index == 0) {
            return get(0);
        }
        T x = removeFirst();
        T result = getRecursive(index - 1);
        addFirst(x);
        return result;
    }
}