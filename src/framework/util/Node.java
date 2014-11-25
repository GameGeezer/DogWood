package framework.util;

/**
 *
 * @author William Gervasio
 *
 */

import java.util.ArrayList;
import java.util.List;

/**
 * A generic node class
 * @param <T> The type of data being stored as a node
 */
public class Node<T> {

    private List<Node<T>> subnodes;

    public Node() {
        this(new ArrayList<Node<T>>());
    }

    public Node(List<Node<T>> subnodes) {
        this.subnodes = subnodes;
    }

    /**
     * Add node as a child
     * @param node
     */
    public void attach(Node<T> node) {
        subnodes.add(node);
    }

    /**
     * Clear all child nodes
     */
    public void clearNodes() {
        subnodes.clear();
    }

    /**
     * Detach node if it is a direct child
     * @param node
     */
    public void detach(Node<T> node) {
        subnodes.remove(node);
    }

    /**
     * Recursively detach a node
     * @param node
     */
    public void deepDetach(T node) {
        subnodes.remove(node);
        for (Node<T> i : subnodes) {
            i.deepDetach(node);
        }
    }

    /**
     * Checks if the passed node is a direct child
     * @param node
     * @return
     */
    public boolean hasNode(Node<T> node) {
        return subnodes.contains(node);
    }

    public List<Node<T>> getSubnodes() {
        return subnodes;
    }

    public void setSubnodes(List<Node<T>> subnodes) {this.subnodes = subnodes;}
}