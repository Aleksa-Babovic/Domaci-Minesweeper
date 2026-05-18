package Milestone2;

public class MyLinkedList {
    private NodeMove head;
    private int size;

    public MyLinkedList() {
        this.head = null;
        this.size = 0;
    }

    public void insert(Move move) {
        NodeMove newNode = new NodeMove(move);
        if (head == null) {
            head = newNode;
        } else {
            NodeMove current = head;
            while (current.next != null)
                current = current.next;
            current.next = newNode;
        }
        size++;
    }

    public NodeMove getHead() { 
    	return head; 
    }
    public int size() { 
    	return size; 
    }
}