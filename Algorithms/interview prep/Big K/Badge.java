import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Badge {
    public static List<List<String>> badge(String[][] arr) {
        //Pt.1 We are working on a security system for a badged-access room in our company's building. 
        //Given an ordered list of employees who used their badge to enter or exit the room, write a function that returns two collection
        //All employees who didn't use their badge while exiting the room – they recorded an enter without a matching exix
        //All employees who didn't use their badge while entering the room – they recorded an exit without a matching enter

        //任何exit前面不是entry -> exit without entry;
        //任何entry 后面不是exit -> entry without exit; 
        Map<String, List<String>> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            String name = arr[i][0];
            String val = arr[i][1];
            if (!map.containsKey(name)) {
                List<String> list = new ArrayList<>();
                list.add(val);
                map.put(name, list);
            }
            else {
                map.get(name).add(val);
            }
        }
        List<String> enterWithOutExitList = new ArrayList<>();
        List<String> existWithOutEntryList = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            String name = entry.getKey();
            List<String> list = entry.getValue();
            boolean existWithOutEntry = false;
            boolean entryWithOutExit = false;
            for (int i = 0; i < list.size(); i++) {
                if (i == 0 && list.get(i).equals("exit")) {
                    existWithOutEntry = true;
                }
                if (i > 0 && list.get(i).equals("exit") && !list.get(i-1).equals("enter")) {
                    existWithOutEntry = true;
                }
                if (i == list.size() - 1 && list.get(i).equals("enter")) {
                    entryWithOutExit = true;
                }
                if (i < list.size() - 1 && list.get(i).equals("enter") && !list.get(i+1).equals("exit")) {
                    entryWithOutExit = true;
                }
            }
            if (existWithOutEntry) {
                existWithOutEntryList.add(name);
            }
            if (entryWithOutExit) {
                enterWithOutExitList.add(name);
            }
        }
        List<List<String>> res = new ArrayList<>();
        res.add(enterWithOutExitList);
        res.add(existWithOutEntryList);
        return res;
    } 

    /**
     * Pt.2 We want to find employees who badged into our secured room unusually often. 
     * We have an unordered list of names and access times over a single day. Access times are given as three or four-digit numbers using 24-hour time, such as "800" or "2250" 
     * Write a function that finds anyone who badged into the room 3 or more times in a 1-hour period, 
     * and returns each time that they badged in during that period. (If there are multiple 1-hour periods where this was true, just return the first one.
     * badge_records =
     ["Paul", 1355],
     ["Jennifer", 1910]
     ["John", 830]
     ["Paul", 1315]
     ["John", 835]
     ["Paul", 1405]
     ["Paul", 1630]
     ["John", 855],
    
     ["John", 915]
     ["John", 930]
     ["Jennifer", 1335]
     ["Jennifer", 730]
     ["John", 1630]
     ]
     */

    public static Map<String, Queue<String>>  findUnsuallyAccess(String[][] arr) { 
        Map<String, Queue<String>> res = new HashMap<>();
        Map<String, List<String>> map = new HashMap<>();
        for (int i = 0 ; i < arr.length; i++) {
            String name = arr[i][0];
            String time = arr[i][1];
            if (!map.containsKey(name)) {
                List<String> list = new ArrayList<>();
                list.add(time);
                map.put(name, list);
            }
            else {
                map.get(name).add(time);
            }
        }
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            String name = entry.getKey();
            List<String> list = entry.getValue();
            Collections.sort(list, (a, b) -> Integer.parseInt(a) - Integer.parseInt(b));
            Queue<String> queue = new LinkedList<>();
            for (String s : list) {
                if (queue.isEmpty()) {
                    queue.offer(s);
                }
                else {
                    String first = queue.peek();
                    int a = Integer.parseInt(first);
                    int b = Integer.parseInt(s);
                    if (b - a > 100 && queue.size() >= 3) {
                        //find it;
                        res.put(name, queue);
                        break;
                    }
                    while (!queue.isEmpty() && b - a > 100) {
                        queue.poll();
                    }
                    queue.offer(s);
                }
            }
        }
        return res;
    }
    
    public static void main(String[] args) {  
        String[][] arr = {
            {"Martha",   "exit"},
            {"Paul",     "enter"},
            {"Martha",   "enter"},
            {"Martha",   "exit"},
            {"Jennifer", "enter"},
            {"Paul",     "enter"},
            {"Curtis",   "enter"},
            {"Paul",     "exit"},
            {"Martha",   "enter"},
            {"Martha",   "exit"},
            {"Jennifer", "exit"}
        };
        System.out.println(badge(arr));
        
        
        String[][] badgeRecords = {
        {"Paul", "1355"},
        {"Jennifer", "1910"},
        {"John", "830"},
        {"Paul", "1315"},
        {"John", "835"},
        {"Paul", "1405"},
        {"Paul", "1630"},
        {"John", "855"},
       
        {"John", "915"},
        {"John", "930"},
        
        {"Jennifer", "1335"},
        {"Jennifer", "730"},
        {"John", "1630"}
        };

        System.out.println(findUnsuallyAccess(badgeRecords));
    }
}

