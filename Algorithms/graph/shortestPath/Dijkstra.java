package shortestPath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;


public class Dijkstra {
	/**
	 * 
	 * Dijkstra 在于从顶点出发，每一次找到距离顶点最近的顶点
	 *
	 */
	
	
	private static int dijkstra(List<Connection> graph, String start, String end) {
		Map<String, List<Node>> graphMap = buildGraph(graph);
		PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.disToStart - o2.disToStart);
		pq.add(new Node(start, 0));
		Set<String> visited = new HashSet<>();
		while (pq.size() > 0) {
			Node currentNode = pq.poll();		
			if (visited.contains(currentNode.val)) {
				continue;
			}
			visited.add(currentNode.val);
			if (currentNode.val.equals(end)) {
				return currentNode.disToStart;
			}
			
			List<Node> adjList = graphMap.get(currentNode.val);
			if (adjList == null) continue;
			for (Node next: adjList) {				
				pq.offer(new Node(next.val, next.disToStart + currentNode.disToStart));
			}
		}
		return -1;
	}

	static Map<String, List<Node>> buildGraph(List<Connection> graph) {
		Map<String, List<Node>> graphMap = new HashMap<>();
		for (Connection c : graph) {
			String node1 = c.node1;
			String node2 = c.node2;
			if (!graphMap.containsKey(node2)) {
				List<Node> list = new ArrayList<>();
				list.add(new Node(node1, c.length));
				graphMap.put(node2, list);
			}
			else {
				graphMap.get(node2).add(new Node(node1, c.length));
			}
			if (!graphMap.containsKey(node1)) {
				List<Node> list = new ArrayList<>();
				list.add(new Node(node2, c.length));
				graphMap.put(node1, list);
			}
			else {
				graphMap.get(node1).add(new Node(node2, c.length));
			}			
		
		}
		return graphMap;
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

	public static class Node {
		String val;
		int disToStart;

		public Node() {
		}

		public Node(String val, int disToStart) {
			this.val = val;
			this.disToStart = disToStart;
		}
	}
}
