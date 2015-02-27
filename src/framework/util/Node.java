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
 *
 * @param <T> The type of data being stored as a node
 */
public class Node<T> {

    private Node parent;
    private final List<Node<T>> subnodes = new ArrayList<Node<T>>();

    /**
     * Add node as a child
     *
     * @param node The node being attached
     */
    public final void attach(final Node<T> node) {

        subnodes.add(node);
        node.parent = this;
    }

    /**
     * Clear all child nodes
     */
    public final void clearNodes() {

        subnodes.clear();
    }

    /**
     * Detach node if it is a direct child
     *
     * @param node The node being detached
     */
    public final void detach(final Node<T> node) {

        subnodes.remove(node);
    }

    /**
     * Recursively detach a node
     *
     * @param node The node being detached
     */
    public final void deepDetach(final T node) {

        subnodes.remove(node);

        for (Node<T> i : subnodes) {
            i.deepDetach(node);
        }
    }

    /**
     * Checks if the passed node is a direct child
     *
     * @param child The node being searched for
     * @return
     */
    public boolean hasChild(final Node<T> child) {

        return subnodes.contains(child);
    }

    public boolean hasChildRecursive(final Node<T> child) {

        if (hasChild(child)) {

            return true;
        } else {

            for (Node<T> node : subnodes) {

                node.hasChild(child);
            }
        }

        return false;
    }

    public List<Node<T>> getSubnodes() {

        return subnodes;
    }

    public Node getParent() {

        return parent;
    }
}