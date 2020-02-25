package linkedlist;

import javax.naming.OperationNotSupportedException;
import java.util.Iterator;

public class Queue<T> extends LinkedList<T> {
    public Queue() {}

    @Override
    public void add(T value) {
        /* YOUR CODE HERE */
        this.pushFront(value);

    }

    @Override
    public void add(int index, T value) {
        throw new RuntimeException();
    }

    @Override
    public T remove(int index){
        throw new RuntimeException();
    }

    @Override
    public T peekFront() {
        throw new RuntimeException();
    }

    @Override
    public T peekBack() {
        throw new RuntimeException();
    }

    @Override
    public T popFront() {
        throw new RuntimeException();
    }

    @Override
    public T popBack() {
        throw new RuntimeException();
    }

    @Override
    public Iterator<T> iterator() {
        throw new RuntimeException();
    }

    @Override
    public Iterator<T> reverseIterator() {
        throw new RuntimeException();
    }

    @Override
    public T remove() {
        return super.popBack();
    }



    public T peek() {
        return super.peekBack();
    }


}
