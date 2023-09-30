package code;

public class MyPriorityQueue implements MyQueueInterface {
    private MyMinHeap minHeap;

    public MyPriorityQueue() {
        minHeap = new MyMinHeap();
    }

    @Override
    public void enqueue(int d) {
        if (!isFull()) {
            minHeap.insert(d);
        }
    }
    
    @Override
    public int dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return -1;
        }
        return minHeap.remove();
    }
    
    @Override
    public int front() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return -1;
        }
        return minHeap.peek();
    }
    
    @Override
    public boolean isFull() {
        return minHeap.isFull();
    }
    
    @Override
    public boolean isEmpty() {
        return minHeap.isEmpty();
    }
    
    @Override
    public String toString() {
        return minHeap.toString();
    }
}