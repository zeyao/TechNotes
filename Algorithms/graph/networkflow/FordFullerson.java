package networkflow;

import java.util.ArrayList;
import java.util.List;


public class FordFullerson {
	
	private int bottleNeck = Integer.MAX_VALUE;
	
	private int findMaxFlow(List<int[]> network, int start, int end) {
		int flow = 0;
		//从0开始DFS
		List<int[]> reversedEdgeToAdd = new ArrayList<>();
		boolean[] marked = new boolean[end+1];
		//避免 添加了 reversed路后回流 1-2-3-2-3
		while (dfs(network, start, end, reversedEdgeToAdd, marked)) {
			System.out.println("Flow : " + bottleNeck);
			flow += bottleNeck;
			network.addAll(reversedEdgeToAdd);
			bottleNeck = Integer.MAX_VALUE;
			reversedEdgeToAdd = new ArrayList<>();
			marked = new boolean[end+1];
		}
		return flow;
	}
	

	
	private boolean dfs(List<int[]> network, int start, int end, List<int[]> reversedEdgeToAdd, boolean[] marked) {
		//根据增广路更新network， 添加reversedEdgeToAdd
		int bactrackBottleNeck = bottleNeck;
		marked[start] = true;
		for (int[] edge : network) {
			if (edge[0] == start && edge[2] > 0 && !marked[edge[1]]) {				
				System.out.println(edge[0] + " - > " + edge[1]);
				
				marked[edge[1]] = true;
				bottleNeck = Math.min(bottleNeck, edge[2]);
				if (edge[1] == end) {
					//find end
					edge[2] -= bottleNeck;
					return true;
				}
				boolean findEnd = dfs(network, edge[1], end, reversedEdgeToAdd, marked);
				//when find end, back track to update 新network， 添加reversedEdgeToAdd
				if (findEnd) {
					edge[2] -= bottleNeck;
					int[] reversedEdge = {edge[1], edge[0], bottleNeck};
					reversedEdgeToAdd.add(reversedEdge);
					return true;
				}
				marked[edge[1]] = false;
				bottleNeck = bactrackBottleNeck;
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		//1 2 1 // 2 3 1
		List<int[]> network = new ArrayList<>();
		int[] edge1 = {0,1,2};
		int[] edge2 = {0,2,3};
		int[] edge3 = {1,3,3};
		int[] edge4 = {1,4,1};
		int[] edge5 = {2,3,1};
		int[] edge6 = {3,5,2};
		int[] edge7 = {4,5,3};
		int[] edge8 = {2,4,1};
		network.add(edge2);
		network.add(edge3);
		network.add(edge7);
		network.add(edge5);
		network.add(edge6);
		network.add(edge1);
		network.add(edge8);
		network.add(edge4);
		FordFullerson f = new FordFullerson();
		System.out.println(f.findMaxFlow(network, 0, 5));
		
		List<int[]> network2 = new ArrayList<>();
		int[] e1 = {0,1,10};
		int[] e2 = {0,2,10};
		int[] e3 = {1,3,25};
		int[] e4 = {2,4,15};
		int[] e5 = {4,1,6};
		int[] e6 = {4,5,10};
		int[] e7 = {3,5,10};
		network2.add(e6);
		network2.add(e1);
		network2.add(e2);
		network2.add(e3);
		network2.add(e4);
		network2.add(e5);
		network2.add(e7);
		FordFullerson f2 = new FordFullerson();
		System.out.println(f2.findMaxFlow(network2, 0, 5));
		
		List<int[]> network3 = new ArrayList<>();
		int[] e11 = {0,1,3};
		int[] e12 = {0,2,2};
		int[] e13 = {1,2,1};
		int[] e14 = {1,3,3};
		int[] e15 = {1,4,4};
		int[] e16 = {2,4,2};
		int[] e17 = {3,5,2};
		int[] e18 = {4,5,3};
		network3.add(e12);
		network3.add(e18);
		network3.add(e16);network3.add(e17);
		network3.add(e13);network3.add(e14);network3.add(e15);
		network3.add(e11);
		FordFullerson f3 = new FordFullerson();
		System.out.println(f3.findMaxFlow(network3, 0, 5));
	}
	
}
