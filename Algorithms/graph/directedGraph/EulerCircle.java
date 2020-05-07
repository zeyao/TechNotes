package directedGraph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class EulerCircle {
	//判断无向图是不是欧拉回路
	//欧拉回路指的是可以从顶点A出发一笔画到回到A，并且遍历了所有的边；
	//一个有向图是欧拉图要满足图是一个联通图，并且每个顶点的入度等于出度；
	
	
	//https://sighingnow.github.io/algorithm/euler_path.html
		
	public static void main(String[] args) {
		/**int[][] arr ={{1,2},{2,3},{3,1}};
		System.out.println(isEulerCircle(arr));
		int[][] arr2 ={{1,2},{2,3},{3,1},{4,5}};
		System.out.println(isEulerCircle(arr2)); 
		int[][] arr3 ={{1,2},{2,3},{3,1},{1,3}};
		System.out.println(isEulerCircle(arr3));
		int[][] arr4 ={{1,2},{2,3},{3,1},{1,4},{4,3}};
		System.out.println(isEulerCircle(arr4));**/
		int[][] arr5 ={{1,2},{2,3},{3,4},{4,1},{2,4},{4,2}};
		System.out.println(isEulerCircle(arr5));
	}
	
	private static boolean isEulerCircle(int[][] arr) {
		Set<Integer> nodeSet = new HashSet<>();
		Map<Integer, Integer> inDegree = new HashMap<>();
		Map<Integer, Integer> outDegree = new HashMap<>();		
		Map<Integer,List<Integer>> adjMap = new HashMap<>();
		
		for (int i = 0 ; i < arr.length; i++) {
			
			if (!adjMap.containsKey(arr[i][0])) {
				List<Integer> adjList = new ArrayList<>();
				adjList.add(arr[i][1]);
				adjMap.put(arr[i][0], adjList);
			}
			else {
				List<Integer> adjList = adjMap.get(arr[i][0]);
				adjList.add(arr[i][1]);
			}
			
			if (!inDegree.containsKey(arr[i][1])) {
				inDegree.put(arr[i][1], 1);
			}
			else {
				Integer count = inDegree.get(arr[i][1]);
				count++;
				inDegree.put(arr[i][1], count);
			}
			
			if (!outDegree.containsKey(arr[i][0])) {
				outDegree.put(arr[i][0], 1);
			}
			else {
				Integer count = outDegree.get(arr[i][0]);
				count++;
				outDegree.put(arr[i][0], count);
			}
			for (int j = 0; j < arr[0].length; j++) {
				nodeSet.add(arr[i][j]);
			}
		}
		System.out.println(inDegree);
		System.out.println(outDegree);
		for (Integer node : nodeSet) {
			if (inDegree.get(node) != outDegree.get(node)) {
				return false;
			}
		}
		int nodeSize = nodeSet.size();
		
		// check is all node can be travel in from one start node;
		Set<Integer> visited = new HashSet<>();
		int travelNodeCount = 0;
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(arr[0][0]);
		while (!queue.isEmpty()) {
			Integer node = queue.poll();
			visited.add(node);
			System.out.println(node);
			List<Integer> adjList = adjMap.get(node);
			for (Integer adj : adjList) {
				if (!visited.contains(adj)) {
					queue.offer(adj);
				}
			}
			travelNodeCount++;
		}
		System.out.println(travelNodeCount);
		return travelNodeCount == nodeSize;
	}
}
