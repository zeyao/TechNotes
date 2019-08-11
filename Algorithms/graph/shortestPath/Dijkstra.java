package shortestPath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;


public class Dijkstra {
	/**
	 * 
	 * Dijkstra 在于从顶点出发，每一次找到距离顶点最近的顶点
	 *
	 */
	
	
	private static int dijkstra(List<Connection> graph, String start, String end) {
		Comparator<Node> compareFactor = new Comparator<Node>() {
			public int compare(Node o1, Node o2) {
                return o1.disToStart - o2.disToStart;                 
			}
		};
		
		PriorityQueue<Node> pq = new PriorityQueue<Node>(compareFactor);
		pq.add(new Node(start, 0));
		while (pq.size() > 0) {
			Node currentNode = pq.poll();		
			if (currentNode.node.equals(end)) {
				return currentNode.disToStart;
			}
			
			for (Connection c: graph) {				
				if (c.node1.equals(currentNode.node)) {					
					pq.add(new Node(c.node2, currentNode.disToStart + c.length));
				}					
				if (c.node2.equals(currentNode.node)) {
					pq.add(new Node(c.node1, currentNode.disToStart + c.length));
				}
				//注意这里是无向图，有向图是不同的,会有不同的策略来进行判断联通性
			}
		}
		return - 1;
	}

	public static void main(String[] args) {
		Connection n1 = new Connection("1", "5", 32);
		Connection n2 = new Connection("5", "4", 35);
		Connection n3 = new Connection("5", "7", 28);
		Connection n4 = new Connection("4", "7", 37);
		Connection n5 = new Connection("1", "7", 19);
		Connection n6 = new Connection("1", "3", 29);
		Connection n7 = new Connection("3", "2", 17);
		Connection n8 = new Connection("2", "7", 34);
		Connection n9 = new Connection("1", "2", 36);
		Connection n10 = new Connection("3", "6", 52);
		Connection n11 = new Connection("2", "6", 40);
		Connection n12 = new Connection("0", "7", 16);
		Connection n13 = new Connection("0", "2", 26);
		Connection n14 = new Connection("0", "4", 38);
		Connection n15 = new Connection("0", "6", 58);
		Connection n16 = new Connection("4", "6", 93);
		List<Connection> graph2 = new ArrayList<>(
				Arrays.asList(n1, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11, n12, n13, n14, n15, n16));
		System.out.println(dijkstra(graph2, "7", "6"));
	}

	public static class Connection {
		String node1;
		String node2;
		int length;

		public Connection(String node1, String node2, int length) {
			this.node1 = node1;
			this.node2 = node2;
			this.length = length;
		}
	}

	static class Node {
		public String node;
		public int disToStart;

		public Node() {
		}

		public Node(String node, int disToStart) {
			this.node = node;
			this.disToStart = disToStart;
		}
	}
}
