package heap;

public class MinHeapTest {
	
	public static void main(String[] args) {	
		MinHeap pq = new MinHeap();
		pq.offer(1);
		System.out.println(pq.peek());
		pq.poll();
		System.out.println(pq.peek());
		pq.offer(5);
		pq.offer(1);
		pq.offer(9);
		pq.offer(91);
		System.out.println(pq.poll());
		System.out.println(pq.poll());
		pq.offer(3);
		System.out.println(pq.poll());
		System.out.println(pq.poll());
	}
}
