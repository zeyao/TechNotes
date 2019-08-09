package mst;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

import java.util.Set;

public class Prime {
	
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
	
	public static List<Connection> primMst(List<Connection> connectionList) {
		Comparator<Connection> compareFactor = new Comparator<Connection>() {
			public int compare(Connection o1, Connection o2) {
                return o1.length - o2.length;                 
			}
		};
		Set<String> markedNode = new HashSet<>();
		List<Connection> mst = new ArrayList<>();
		PriorityQueue<Connection> pq = new PriorityQueue<>(compareFactor);
		//对于边的优先队列
		String firstPickedNode = connectionList.get(0).node1;
		visit(connectionList, firstPickedNode, pq, markedNode);
		
		while (!pq.isEmpty()) {
			Connection minLenconnection = pq.poll();
			String node1 = minLenconnection.node1;
			String node2 = minLenconnection.node2;
			if (markedNode.contains(node1) && markedNode.contains(node2)) {
				continue;
				//失效的边
			}
			mst.add(minLenconnection);
			if (!markedNode.contains(node1)) {
				visit(connectionList, node1, pq, markedNode);
			}
			if (!markedNode.contains(node2)) {
				visit(connectionList, node2, pq, markedNode);
			}
		}
		
		return mst;
		
	}
	
	private static void visit(List<Connection> connectionList, String node, PriorityQueue<Connection> pq, Set<String> markedNode) {
		//标记顶点node并且将所有链接node未被标记的顶点都放入PQ
		//这里可以用一个Map<String,List<Connection>> 一个字典查找来优化，找到所有包含node 的边
		markedNode.add(node);
		for (Connection connection : connectionList) {
			if (node.equals(connection.node1)) {
				pq.add(connection);
			}
			else if (node.equals(connection.node2)) {
				pq.add(connection);
			}
		}
		
		
	}
	
	public static void main(String[] args) {
        Connection c1 = new Connection("A", "D", 1);
        Connection c2 = new Connection("A", "B", 3);
        Connection c3 = new Connection("D", "B", 3);
        Connection c4 = new Connection("B", "C", 1);
        Connection c5 = new Connection("C", "D", 1);
        Connection c6 = new Connection("E", "D", 6);
        Connection c7 = new Connection("C", "E", 5);
        Connection c8 = new Connection("C", "F", 4);
        Connection c9 = new Connection("E", "F", 2);
        List<Connection> graph = new ArrayList<>(Arrays.asList(c1, c2, c3, c4, c5, c6, c7, c8, c9));
        List<Connection> result = primMst(graph);
        for (Connection conn : result) {
            System.out.println(conn.node1 + "-" + conn.node2);
        }
        System.out.println("分割线 ------------------- ");
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
        List<Connection> graph2 = new ArrayList<>(Arrays.asList(n1, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11, n12, n13, n14, n15, n16));
        List<Connection> result2 = primMst(graph2);
        for (Connection conn : result2) {
            System.out.println(conn.node1 + "-" + conn.node2);
        }
    }
	
}
