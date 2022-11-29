package org.wes.dancinglinks;

public class NodeIter implements java.util.Iterator<Node> {

    private final Node head;
    private Node current;

    public NodeIter(Node node) {
        this.head = this.current = node;
    }

    @Override
    public boolean hasNext() {
        return current.getR() != head;
    }

    @Override
    public Node next() {
        if (!hasNext()) {
            throw new java.util.NoSuchElementException();
        }
        current = current.getR();
        return current;
    }
}
