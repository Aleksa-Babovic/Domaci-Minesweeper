package Milestone1;

public class CoordinateQueue {

    private static class CoordinateNode {
        int row;
        int col;
        CoordinateNode next;

        CoordinateNode(int row, int col) {
            this.row = row;
            this.col = col;
            this.next = null;
        }
    }

    private CoordinateNode head;
    private CoordinateNode tail;
    private int size;

    public CoordinateQueue() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public void enqueue(int row, int col) {
        CoordinateNode newNode = new CoordinateNode(row, col);
        if (tail != null) tail.next = newNode;
        tail = newNode;
        if (head == null) head = newNode;
        size++;
    }

    public int[] dequeue() {
        if (isEmpty()) throw new RuntimeException("Queue is empty");
        int[] coords = {head.row, head.col};
        head = head.next;
        if (head == null) tail = null;
        size--;
        return coords;
    }

    public boolean isEmpty() { 
    	return head == null; 
    }
    
    public int size() { 
    	return size; 
    }
}