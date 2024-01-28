package com.bobocode.cs;

import com.bobocode.cs.exception.EmptyStackException;

/**
 * {@link LinkedStack} is a stack implementation that is based on singly linked generic nodes.
 * A node is implemented as inner static class {@link Node<T>}.
 * <p><p>
 * <strong>TODO: to get the most out of your learning, <a href="https://www.bobocode.com/learn">visit our website</a></strong>
 * <p>
 *
 * @param <T> generic type parameter
 * @author Taras Boychuk
 * @author Serhii Hryhus
 */
public class LinkedStack<T> implements Stack<T> {
    private int size;
    private Node<T> head;

    public LinkedStack() {
    }

    public LinkedStack(Node<T> root) {
        this.head = root;
    }

    private static class Node<T> {
        private T element;
        private Node<T> next;

        public Node(T element) {
            this.element = element;
        }
    }

    /**
     * This method creates a stack of provided elements
     *
     * @param elements elements to add
     * @param <T>      generic type
     * @return a new stack of elements that were passed as method parameters
     */
    public static <T> LinkedStack<T> of(T... elements) {
        LinkedStack<T> root = new LinkedStack<>();
        for (int i = 0; i < elements.length; i++) {
            root.push(elements[i]);
        }
        return root;
    }

    /**
     * The method pushes an element onto the top of this stack. This has exactly the same effect as:
     * addElement(item)
     *
     * @param element elements to add
     */
    @Override
    public void push(T element) {
        Node<T> newNode = new Node<>(element);
        if (head == null) {
            head = newNode;
        }
        if (element == null) {
            throw new NullPointerException();
        } else {
            newNode.next = head;
            head = newNode;
        }
        size++;
    }

    /**
     * This method removes the object at the top of this stack
     * and returns that object as the value of this function.
     *
     * @return The object at the top of this stack
     * @throws EmptyStackException - if this stack is empty
     */
    @Override
    public T pop() {
        if (head == null) {
            throw new EmptyStackException();
        } else {
            T removedElement = head.element;
            head = head.next;
            size--;
            return removedElement;
        }
    }

    /**
     * Returns the number of elements in the stack
     *
     * @return number of elements
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Checks if a stack is empty
     *
     * @return {@code true} if a stack is empty, {@code false} otherwise
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

}
