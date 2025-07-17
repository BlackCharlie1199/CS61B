public class LinkedListDeque<T> {
    private int size;
    private node sentinel;

    public class node {
        /** Create the node of list s.t. we don't need to
         *  understand how linked list is worked when we call LinkedListDeque
         *  : D
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

    /* Add a node at first position */
    public void addFirst (T item) {
        sentinel.nextNode = new node(item, sentinel, sentinel.nextNode);
        size += 1;
    }

    /* Add a node at last position */
    public void addLast (T item) {
        sentinel.previousNode = new node(item, sentinel.previousNode, sentinel);
        size += 1;
    }

    public T removeFirst() {
        node firstNode = sentinel.nextNode;
        T x = firstNode.item;
        sentinel.nextNode = firstNode.nextNode;
        sentinel.nextNode.previousNode = sentinel;
        firstNode = null;
        size -= 1;
        return x;
    }

    public T removeLast() {
        node lastNode = sentinel.previousNode;
        T x = lastNode.item;
        sentinel.previousNode = lastNode.previousNode;
        sentinel.previousNode.nextNode = sentinel;
        lastNode = null;
        size -= 1;
        return x;
    }
    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        node p = sentinel.nextNode;
        while (p!=sentinel) {
            System.out.print(p.item);
            System.out.print(" ");
            p = p.nextNode;
        }
    }

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
        return sentinel.nextNode.findRecursive(index);
    }
}