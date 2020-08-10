import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class ParentAndChild {
 /**
  * Pt. 1 Suppose we have some input data describing a graph of relationships between parents and children over multiple generations.
    The data is formatted as a list of (parent, child) pairs, where each individual is assigned a unique integer identifier.
    For example, in this diagram, 3 is a child of 1 and 2, and 5 is a child of 4:

    parentChildPairs = [  (1, 3), (2, 3), (3, 6), (5, 6),
                   (5, 7), (4, 5), (4, 8), (8, 10)  ] 

    Write a function that takes this data as input and returns two collections: one containing all individuals with zero known parents, 
    and one containing all individuals with exactly one known parent.

    findNodesWithZeroAndOneParents(parentChildPairs) =>
                                  [ [1, 2, 4],    // Individuals with zero parents
                                  [5, 7, 8, 10] // Individuals with exactly one parent ]

  */
    
    public static List<List<Integer>> findNodesWithZeroAndOneParents(int[][] arr) {
        List<List<Integer>> res = new ArrayList<>();
        Map<Integer, List<Integer>> map = new HashMap<>(); // store child as key , parent as value;
        for (int[] pair : arr) {
            map.put(pair[1], new ArrayList<>());
            map.put(pair[0], new ArrayList<>());
        }
        for (int[] pair : arr) {
            map.get(pair[1]).add(pair[0]);
        }
        List<Integer> one = new ArrayList<>();
        List<Integer> zero = new ArrayList<>();
        map.entrySet().forEach(entry -> {
            if (entry.getValue().size() == 0) {
                zero.add(entry.getKey());
            }
            if (entry.getValue().size() == 1) {
                one.add(entry.getKey());
            }
        });
        res.add(zero);
        res.add(one);

        return res;

    }

    // Pt.2 Write a function that takes the graph, as well as two of the individuals in our dataset, as its inputs and returns true if and only if they share at least one ancestor. 
    //Sample input and output: （input as same as last part）

    public static boolean isShareCommonAncestor(int[][] pairs, int node1, int node2) {
        Set<Integer> set1 = new HashSet<>();
        set1.add(node1);
        Set<Integer> set2 = new HashSet<>();
        set2.add(node2);

        Map<Integer, List<Integer>> map = new HashMap<>(); // store child as key , parent as value;
        for (int[] pair : pairs) {
            map.put(pair[1], new ArrayList<>());
            map.put(pair[0], new ArrayList<>());
        }
        for (int[] pair : pairs) {
            map.get(pair[1]).add(pair[0]);
        }
        find(set1, node1, map);
        find(set2, node2, map);
        for (int a : set1) {
            if (set2.contains(a)) {
                return true;
            }
        }

        return false;
    }

    private static void find(Set<Integer> set, int node, Map<Integer, List<Integer>> map) {
        if (!map.containsKey(node)) {
            return;
        }
        List<Integer> parent = map.get(node);
        set.addAll(parent);
        for (int p : parent) {
            find(set, p, map);
        }
    }
                
    //Pt.3 For example, in this diagram, 3 is a child of 1 and 2, and 5 is a child of 4 Write a function that, for a given individual in our dataset, 
    //returns their earliest known ancestor -- the one at the farthest distance from the input individual.
    // If there is more than one ancestor tied for "earliest", return any one of them. If the input individual has no parents, the function should return null (or -1). Sample input and output:
    //parent_child_pairs = [ (1, 3), (2, 3), (3, 6), (5, 6), (5, 7), (4, 5), (4, 8), (8, 10), (11, 2) ]

    //findEarliestAncestor(parentChildPairs, 8) => 4
    //findEarliestAncestor(parentChildPairs, 7) => 4
    //findEarliestAncestor(parentChildPairs, 6) => 11
    //findEarliestAncestor(parentChildPairs, 1) => null or -1 
    public static int findEarliestAncestor(int[][] pairs, int node) {
        Map<Integer, List<Integer>> map = new HashMap<>(); // store child as key , parent as value;
        for (int[] pair : pairs) {
            map.put(pair[1], new ArrayList<>());
            map.put(pair[0], new ArrayList<>());
        }
        for (int[] pair : pairs) {
            map.get(pair[1]).add(pair[0]);
        }
        int res = -1;
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(node);
        visited.add(node);
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            if (cur != node) {
                res = cur;
            }
            List<Integer> parent = map.get(cur);
            for (int p : parent) {
                if (!visited.contains(p)) {
                    queue.offer(p);
                    visited.add(p);
                }              
            }
        }
        return res;
    }
    public static void main(String[] args) {    
        int[][] arr = new int[][] {
            {1, 3}, {2, 3}, {3, 6}, {5, 6},
            {5, 7}, {4, 5}, {4, 8}, {8, 10}, {11, 2}
        };
        System.out.println(findNodesWithZeroAndOneParents(arr));
        System.out.println(isShareCommonAncestor(arr, 3, 6));
        System.out.println(isShareCommonAncestor(arr, 10, 5));
        System.out.println(isShareCommonAncestor(arr, 2, 3));
        System.out.println(isShareCommonAncestor(arr, 8, 6));
        System.out.println(isShareCommonAncestor(arr, 10, 6));

        System.out.println(isShareCommonAncestor(arr, 5, 3));
        System.out.println(isShareCommonAncestor(arr, 8, 3));

        System.out.println(findEarliestAncestor(arr, 8));
        System.out.println(findEarliestAncestor(arr, 7));
        System.out.println(findEarliestAncestor(arr, 6));
        System.out.println(findEarliestAncestor(arr, 1));
    }
}

