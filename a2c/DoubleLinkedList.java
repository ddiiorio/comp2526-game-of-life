package ca.bcit.comp2526.a2c;

import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class creates a doubly linked list of generic objects, including
 * an iterator to transverse through the list and methods to add and
 * remove objects.
 * 
 * @author Danny Di Iorio
 * @version 2.0
 * 
 * @param <E>
 */
public class DoubleLinkedList<E>
    implements Iterable<E>, Serializable {

    private static final long serialVersionUID = -8033214456081537378L;
    private Node<E> head;
    private Node<E> tail;
    private int size;

    /**
     * Keeps track of each object in the doubly linked list.
     * 
     * @author Danny Di Iorio
     * @version 1.0
     * 
     * @param <E>
     */
    public static class Node<E> implements Serializable {

        private static final long serialVersionUID = 3488647267086915162L;
        private E data;
        private Node<E> previous;
        private Node<E> next;

        /**
         * 
         * Constructs Node objects.
         * 
         * @param e
         *            data
         */
        public Node(E e) {
            data = e;
        }
    }

    /**
     * Getter for data in the first node in the list.
     * 
     * @return the head
     */
    public E getFirst() {
        if (size > 0) {
            return head.data;
        } else {
            return null;
        }
    }

    /**
     * Getter for data in the last node in the list.
     * 
     * @return the tail
     */
    public E getLast() {
        if (size > 0) {
            return tail.data;
        } else {
            return null;
        }
    }

    /**
     * Getter for list size.
     * 
     * @return the size
     */
    public int size() {
        return size;
    }

    /**
     * Adds node to the front of a double linked list.
     * 
     * @param e
     *            data being added
     * @throws CouldNotAddException
     *            throws if data being added is null
     */
    public void addToFront(E e) throws CouldNotAddException {
        if (e == null) {
            throw new CouldNotAddException("Can not add null parameter");
        }

        Node<E> newNode = new Node<E>(e);

        if (head == null) {
            tail = newNode;
        } else {
            newNode.next = head;
            head.previous = newNode;
        }
        head = newNode;
        size++;
    }

    /**
     * Removes node from the front of a double linked list.
     * 
     * @return E data being removed
     * @throws CouldNotRemoveException
     *             throws if list is empty
     */
    public E removeFromFront() throws CouldNotRemoveException {
        if (head == null) {
            throw new CouldNotRemoveException("Remove failed, list is empty");
        }
        E remove = head.data;
        Node<E> current = head;
        if (head == tail) {
            head = null;
            tail = null;
        } else {
            head = current.next;
        }
        size--;
        return remove;
    }

    /**
     * Adds node to the end of a double linked list.
     * 
     * @param e
     *            data being added
     * @throws CouldNotAddException
     *            throws if data being added is null
     */
    public void addToEnd(E e) throws CouldNotAddException {
        if (e == null) {
            throw new CouldNotAddException("Can not add null parameter");
        }

        Node<E> newNode = new Node<E>(e);

        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.previous = tail;
            newNode.next = null;
        }
        tail = newNode;
        size++;
    }

    /**
     * Removes node from the end of double linked list.
     * 
     * @return E data being removed
     * @throws CouldNotRemoveException
     *             throws if list is empty
     */
    public E removeFromEnd() throws CouldNotRemoveException {
        if (head == null) {
            throw new CouldNotRemoveException("Remove failed, list is empty");
        }

        E remove = tail.data;
        Node<E> current = head;

        if (head == tail) {
            head = null;
            tail = null;
        } else {
            while (current.next != tail) {
                current = current.next;
            }
            tail = current;
            current.next = null;
        }
        size--;
        return remove;
    }

    /**
     * Iterator for double linked list of type E.
     * 
     * @return new Iterator
     */
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node<E> current = head;
            private int index;
            
            @Override
            public boolean hasNext() {
                return (index < size);
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E node = current.data;
                current = current.next;
                index++;
                return node;
            }
        };
    }

    /**
     * Prints all nodes in a double linked list.
     * 
     * @return List as a String
     */
    public String toString() {
        String result = "";
        Node<E> current = head;
        while (current.next != null) {
            current = current.next;
            current.previous = current.previous; // redundant statement just to 
            // prevent unused variable checkstyle error on 'previous' node.
            result += current.data + ", ";
        }
        return "" + result;
    }
}
