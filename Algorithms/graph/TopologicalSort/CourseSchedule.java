package TopologicalSort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class CourseSchedule {
    //[0,1] 要先学0， 需要先学1；
	public boolean canFinish(int numCourses, int[][] prerequisites) {
		if (numCourses <= 1 || prerequisites == null || prerequisites.length == 0) {
			return true;
		}
		//用一个Map来储存所有节点的下一个节点adj
		Map<Integer,List<Integer>> map = new HashMap<>();
		//统计每一个节点的入度 (有几个pre-request)；
		int[] inDegree = new int[numCourses];
		
		for (int i = 0 ; i < prerequisites.length; i++) {
			inDegree[prerequisites[i][0]] += 1;
			if (!map.containsKey(prerequisites[i][1])) {
				List<Integer> adjList = new ArrayList<>();
				adjList.add(prerequisites[i][0]);
				map.put(prerequisites[i][1],adjList);
			}
			else {
				List<Integer> adjList = map.get(prerequisites[i][1]);
				adjList.add(prerequisites[i][0]);
			}
		}
		
		Queue<Integer> queue = new LinkedList<>();
		for (int i = 0 ; i < inDegree.length; i++) {
			if (inDegree[i] == 0) {
				queue.offer(i);
				//从没有任何pre-request的节点开始bfs			
			}				
		}
		
		while (!queue.isEmpty()) {
			  int current = queue.poll(); 
			  List<Integer> adjList = map.get(current);
			  if (adjList != null && !adjList.isEmpty()) {
				  for (Integer adj : adjList) {
					  inDegree[adj] = inDegree[adj] - 1;
					  if (inDegree[adj] == 0) {
						  queue.offer(adj);
					  }
				  }
			  }
		}
		for (int i = 0; i < numCourses; i++) {
			if (inDegree[i] != 0) {
				return false;
				//Topo sort 结束，如果还有入度大于0；说明依旧有pre-request,有环；
			}
		}
		return true;
	}
	
}
