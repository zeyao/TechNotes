package mst;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import java.util.Set;

public class Kruskal {
	/**
	 * 
	 * 和prime不同的是，KrusKal在所有的边来找最小距离的边，
	 * 每次加入的边不能让图有环，通过判断要加入的边的两点是不是已经联通（如果已经联通，再去加上这条边，就有环啦）
	 * 把所有有效边就是mst
	 *
	 */
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
	
	public static List<Connection> KrusKalMst(List<Connection> connectionList) {
		Comparator<Connection> compareFactor = new Comparator<Connection>() {
			public int compare(Connection o1, Connection o2) {
				return o1.length - o2.length;                 
			}
		};
		Map<String,Set<String>> unionMap = new HashMap<>();
		List<Connection> mst = new ArrayList<>();
		PriorityQueue<Connection> pq = new PriorityQueue<>(compareFactor);
		//对于边的优先队列
		for (Connection connection : connectionList) {
			pq.offer(connection);
		}
		while (!pq.isEmpty()) {
			Connection minLenconnection = pq.poll();
			String node1 = minLenconnection.node1;
			String node2 = minLenconnection.node2;
			if (isUnioned(node1, node2, unionMap)) {
				continue;
			}
			mst.add(minLenconnection);
			
			//add union for two node of this connection
			Set<String> node1AdjList = unionMap.get(node1);
			Set<String> node2AdjList = unionMap.get(node2);
			node1AdjList.add(node2);
			node2AdjList.add(node1);
			
		}		
		return mst;		
	}
	
	private static boolean isUnioned(String node1, String node2, Map<String,Set<String>> unionMap) {
		Set<String> node1AdjList;
		Set<String> node2AdjList;
		if (!unionMap.containsKey(node1)) {
			node1AdjList = new HashSet<>();
			unionMap.put(node1, node1AdjList);
		}
		else {
			node1AdjList = unionMap.get(node1);
		}
		if (!unionMap.containsKey(node2)) {
			node2AdjList = new HashSet<>();
			unionMap.put(node2, node1AdjList);
		}
		else {
			node2AdjList = unionMap.get(node2);
		}
		
		if (node2AdjList.contains(node1) || node1AdjList.contains(node2)) {
			return true;
		}
		for (String node1Adj : node1AdjList) {
			if (node2AdjList.contains(node1Adj)) {
				return true;
			}
		}
		return false;
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
        List<Connection> result = KrusKalMst(graph);
        for (Connection conn : result) {
            System.out.println(conn.node1 + "-" + conn.node2);
        }
    }	
}
