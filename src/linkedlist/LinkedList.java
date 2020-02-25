package linkedlist;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<T> implements Iterable<T> {
    private class ListNode<T> {
        private T payload;
        private ListNode<T> next;
        private ListNode<T> prev;

        public ListNode(T v) {
            this.payload = v;
        }
    }

    protected ListNode<T> frontNode;
    protected ListNode<T> backNode;
    protected int size;

    public LinkedList() {
        /* YOUR CODE HERE */
    }

    public LinkedList(Iterable<? extends T> c) {
        /* YOUR CODE HERE */
        for (T t : c) pushBack(t);
    }

    public T peekFront() {
        /* YOUR CODE HERE */
        if (frontNode == null) {
            return null;
        } else {
            return frontNode.payload;
        }
    }

    public T peekBack() {
        /* YOUR CODE HERE */
        if (backNode == null) {
            return null;
        } else {
            return backNode.payload;
        }
    }

    public T popFront() {
        /* YOUR CODE HERE */
        ListNode<T> current = frontNode;
        if (current == null) {
            throw new NoSuchElementException();
        }
        size--;
        frontNode = frontNode.next;
        current.next = null;
        if (frontNode != null) {
            frontNode.prev = null;
        } else {
            backNode = null;
        }
        return current.payload;
    }

    public T popBack() {
        /* YOUR CODE HERE */
        ListNode<T> current = backNode;
        if (current == null) {
            throw new NoSuchElementException();
        }
        size--;
        backNode = backNode.prev;
        current.prev = null;
        if (backNode != null) {
            backNode.next = null;
        } else {
            frontNode = null;
        }
        return current.payload;
    }

    public void pushBack(T value) {
        /* YOUR CODE HERE */
        ListNode<T> current = new ListNode<>(value);
        size++;
        if (backNode == null) {
            backNode = current;
            frontNode = current;
            return;
        }
        backNode.next = current;
        current.prev = backNode;
        backNode = current;

    }

    public void pushFront(T value) {
        /* YOUR CODE HERE */
        ListNode<T> current = new ListNode<>(value);
        size++;
        if (frontNode == null) {
            frontNode = current;
            backNode = current;
            return;
        }
        frontNode.prev = current;
        current.next = frontNode;
        frontNode = current;
    }

    public void add(T value) {
        /* YOUR CODE HERE */
        pushFront(value);
    }

    public void add(int index, T value) {
        /* YOUR CODE HERE */
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        ListNode<T> addNode = new ListNode<>(value);
        int count = 0;
        ListNode<T> current = frontNode;
        ListNode<T> previous = null;
        while (count < index) {
            previous = current;
            current = current.next;
            count++;
        }
        addNode.next = current;
        addNode.prev = previous;
        if (previous != null) {
            previous.next = addNode;
        }
        if (current != null) {
            current.prev = addNode;
        }
        if (index == 0) {
            frontNode = addNode;
        }
        if (index == size) {
            backNode = addNode;
        }
        size++;
    }

    public T remove() {
        /* YOUR CODE HERE */
        return popFront();
    }

    public T remove(int index) {
        /* YOUR CODE HERE */
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        int count = 0;
        ListNode<T> current = frontNode;
        ListNode<T> previous = null;
        while (count < index) {
            previous = current;
            current = current.next;
            count++;
        }
        if (previous != null && current != null) {
            previous.next = current.next;
        }
        if (current != null && current.next != null) { //Actually current will not be null due to check index >= size first
            current.next.prev = previous;
        }
        if (index == 0) {
            frontNode = frontNode.next;
        }
        if (index == size - 1) {
            backNode = backNode.prev;
        }
        current.next = null; //Actually current will not be null due to check index >= size first
        current.prev = null; //Actually current will not be null due to check index >= size first
        size--;
        return current.payload;

    }

    private void remove(ListNode<T> node) {
        /* YOUR CODE HERE */
    }

    public int size() {
        return this.size;
    }

    @Override
    public Iterator<T> iterator() {
        /* YOUR CODE HERE */
        return new LinkedListIterator();
    }

    public class LinkedListIterator implements Iterator<T> {
        /* YOUR CODE HERE */
        ListNode<T> current = frontNode;
        ListNode<T> previous = current;

        @Override
        public boolean hasNext() {
            /* YOUR CODE HERE */
            return current != null;
        }

        @Override
        public T next() {
            /* YOUR CODE HERE */
            if (current == null) {
                throw new NoSuchElementException();
            }
            previous = current;
            current = current.next;
            return previous.payload;
        }

        @Override
        public void remove() {
            /* YOUR CODE HERE */
            if (current == previous) {
                throw new IllegalStateException();
            }

            if (previous.prev != null) {
                previous.prev.next = previous.next;
            } else {
                frontNode = previous.next;
            }
            if (previous.next != null) {
                previous.next.prev = previous.prev;
            } else {
                backNode = previous.prev;
            }
            previous.next = null;
            previous.prev = null;
            size--;
        }
    }

    public Iterator<T> reverseIterator() {
        /* YOUR CODE HERE */
        return new LinkedListReverseIterator();
    }

    public class LinkedListReverseIterator implements Iterator<T> {
        /* YOUR CODE HERE */
        ListNode<T> current = backNode;
        ListNode<T> next = current;

        @Override
        public boolean hasNext() {
            /* YOUR CODE HERE */
            return current != null;
        }

        @Override
        public T next() {
            /* YOUR CODE HERE */
            if (current == null) {
                throw new NoSuchElementException();
            }
            next = current;
            current = current.prev;
            return next.payload;
        }

        @Override
        public void remove() {
            /* YOUR CODE HERE */
            if (current == next) {
                throw new IllegalStateException();
            }
            if (next.next != null) {
                next.next.prev = next.prev;
            } else {
                backNode = next.prev;
            }
            if (next.prev != null) {
                next.prev.next = next.next;
            } else {
                frontNode = next.next;
            }
            next.next = null;
            next.prev = null;
            size--;
        }
    }


    // toString requires Iterator to be partially implemented to function
    // as it uses the for-each loop construct
    @Override
    public String toString() {
        String result = "[";

        for (T value : this) {
            result += String.format("%s, ", value.toString());
        }

        if (result.length() > 1) {
            result = result.substring(0, result.length() - 2);
        }

        result += "]";
        return result;
    }
}
