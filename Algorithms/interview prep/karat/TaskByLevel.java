import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class TaskByLevel {
    /**input = {
        {"cook", "eat"},   // do "cook" before "eat"
        {"study", "eat"},
        {"sleep", "study"}}
        
        output (steps of a workflow):
        {{"sleep", "cook"},.
        {"study"},
        {"eat"}}
        */
    
    private static List<List<String>> findTaskByLevel(String[][] arr) {
        Map<String, Integer> indegree = new HashMap<>();
        Map<String, List<String>> adj = new HashMap<>();
        for (String[] pair : arr) {
            indegree.put(pair[0], 0);
            indegree.put(pair[1], 0);
            adj.put(pair[0], new ArrayList<>());
            adj.put(pair[1], new ArrayList<>());
        }
        for (String[] pair : arr) {
            indegree.put(pair[1], indegree.get(pair[1])+1);
            adj.get(pair[0]).add(pair[1]);
        }

        //topologic sort
        Queue<String> queue = new LinkedList<>();
        indegree.entrySet().forEach(e -> {
            if (e.getValue() == 0) {
                queue.offer(e.getKey());
            }
        });
        List<List<String>> res = new ArrayList<>();
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<String> list = new ArrayList<>();
            while (size > 0) {
                String cur = queue.poll();
                list.add(cur);
                List<String> next = adj.get(cur);
                for (String n : next) {
                    int val = indegree.get(n);
                    val--;
                    indegree.put(n, val);
                    if (val == 0) {
                        queue.offer(n);
                    }
                }
                size--;
            }
            res.add(list);
        }
        return res;
    }
    public static void main(String[] args) {    
        String[][] arr = new String[][] { 
            {"cook", "eat"},   // do "cook" before "eat"
            {"study", "eat"},
            {"sleep", "study"}
        };
        System.out.println(findTaskByLevel(arr));
    }
}

