package com.bobocode.cs;

import com.bobocode.util.ExerciseNotCompletedException;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * {@link RecursiveBinarySearchTree} is an implementation of a {@link BinarySearchTree} that is based on a linked nodes
 * and recursion. A tree node is represented as a nested class {@link Node}. It holds an element (a value) and
 * two references to the left and right child nodes.
 * <p><p>
 * <strong>TODO: to get the most out of your learning, <a href="https://www.bobocode.com/learn">visit our website</a></strong>
 * <p>
 *
 * @param <T> a type of elements that are stored in the tree
 * @author Taras Boychuk
 * @author Maksym Stasiuk
 */
public class RecursiveBinarySearchTree<T extends Comparable<T>> implements BinarySearchTree<T> {
    private Node<T> root;
    private int size;

    private static class Node<T> {
        T element;
        Node<T> rightNode;
        Node<T> leftNode;

        public Node(T element) {
            this.element = element;
        }
    }

    public static <T extends Comparable<T>> RecursiveBinarySearchTree<T> of(T... elements) {
        RecursiveBinarySearchTree<T> tree = new RecursiveBinarySearchTree<>();
        Stream.of(elements)
                .forEach(tree::insert);
        return tree;
    }

    @Override
    public boolean insert(T element) {
        if (root == null) {
            root = new Node<>(element);
            size++;
            return true;
        } else {
            return insert(root, element);
        }
    }

    public boolean insert(Node<T> root, T element) {
        if (element.compareTo(root.element) < 0) {
            if (root.leftNode == null) {
                root.leftNode = new Node<>(element);
                size++;
                return true;
            } else {
                return insert(root.leftNode, element);
            }
        } else if (element.compareTo(root.element) > 0) {
            if (root.rightNode == null) {
                root.rightNode = new Node<>(element);
                size++;
                return true;
            } else {
                return insert(root.rightNode, element);
            }
        } else {
            return false;
        }
    }

    @Override
    public boolean contains(T element) {
        if (element == null) {
            throw new NullPointerException();
        }
        return contains(root, element);
    }

    public boolean contains(Node<T> root,T element) {
        if (root == null) {
            return false;
        } else if (element.compareTo(root.element) < 0) {
            return contains(root.leftNode, element);
        } else if (element.compareTo(root.element) > 0) {
            return contains(root.rightNode, element);
        } else {
            return true;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int depth() {
        return root != null ? depth(root) - 1 : 0;
    }

    public int depth(Node<T> root) {
        if (root == null) {
            return 0;
        } else {
            return 1 + Math.max(depth(root.leftNode), depth(root.rightNode));
        }
    }

    @Override
    public void inOrderTraversal(Consumer<T> consumer) {
        inOrderTraversal(root, consumer);
    }

    public void inOrderTraversal(Node<T> root ,Consumer<T> consumer) {
        if (root != null) {
            inOrderTraversal(root.leftNode, consumer);
            consumer.accept(root.element);
            inOrderTraversal(root.rightNode, consumer);
        }
    }
}
