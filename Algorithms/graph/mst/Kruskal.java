package mst;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;


public class Kruskal {
	/**
	 * 
	 * 和prime不同的是，KrusKal在所有的边来找最小距离的边，
	 * 每次加入的边不能让图有环，通过判断要加入的边的两点是不是已经联通（如果已经联通，再去加上这条边，就有环啦）这里用到union find 的思想
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
		     
        Map<String,String> unionMap = new HashMap<>();
        //union map for key with node name and value with the unionId
        for (Connection c : connectionList) {
        	unionMap.put(c.node1, c.node1);
        	unionMap.put(c.node2, c.node2);
        }
        
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
			if (union(node1, node2, unionMap)) {
				continue;
			}
			mst.add(minLenconnection);
		
		}		
		return mst;		
	}
	
	/**
	 * 判断两点是不是联通，用unionMap来记录每个node已经联通的node，如果两个node1 node2任何AdjList有任何一个相同的node说明node1 node2是联通的
	 */
	private static String find(String node, Map<String,String> unionMap) {
        return unionMap.get(node);
    }
    
    private static boolean union(String p, String q, Map<String,String> unionMap) {
        String pId = find(p, unionMap);
        String qId = find(q, unionMap);
        if (pId.equals(qId)) {
            return true;
        }
        
        for (Map.Entry<String,String> entry : unionMap.entrySet()) {
            if (pId.equals(entry.getValue())) {
            	entry.setValue(qId);
            }
        }
        return false;
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
	        List<Connection> graph2 = new ArrayList<>(Arrays.asList(n1, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11, n12, n13, n14, n15, n16));
	        List<Connection> result2 = KrusKalMst(graph2);
	        for (Connection conn : result2) {
	            System.out.println(conn.node1 + "-" + conn.node2);
	        }
    }	
}
