import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class MeetingRooms {
    //p1 whether the new time can be schedule as new meeting
    public static boolean meetingRooms(int[][] meeting, int start, int end) {
        for (int[] m : meeting) {
            if (m[0] >= end || m[1] <= start) {
                continue;
            }
            return false;
        }
        return true;
    }
  

    //Pt.2 Similar to Merge Intervals(LeetCode 56), 
    //but the output is different, now you are required to output idle time after time intervals merged, 
    //notice also output 0 - first start time.
    //Leetcode 795 employee free time
    //[[1,2],[5,6]],[1,3],[4,10]] -> [3,4]
    public static List<List<Integer>> mergeInterval2(int[][] intervals){
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        List<List<Integer>> res = new ArrayList<>();
        int[] first = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            int[] cur = intervals[i];
            if (first[1] >= cur[0]) { // overlapped - merge
                int start = Math.min(first[0], cur[0]);
                int end = Math.max(first[1], cur[1]);
                int[] merged = {start, end};
                first = merged;
            }
            else {
                List<Integer> list = new ArrayList<>();
                int idelStart = first[1];
                int idelEnd = cur[0];
                list.add(idelStart);
                list.add(idelEnd);
                res.add(list);
                first = cur;
            }
        }
        return res;
    }

    //Merge Interval;
    public static List<List<Integer>> mergeInterval(int[][] intervals){
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        List<List<Integer>> res = new ArrayList<>();
        int[] first = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            int[] cur = intervals[i];
            if (cur[0] > first[1]) { //no overlapped - not merge, add the first interval to list
                List<Integer> list = new ArrayList<>();
                list.add(first[0]);
                list.add(first[1]);
                res.add(list);
                first = cur;
            }
            else {
                int start = Math.min(cur[0], first[0]);
                int end = Math.max(cur[1], first[1]);
                first[0] = start;
                first[1] = end;
            }
        }
        List<Integer> list = new ArrayList<>();
        list.add(first[0]);
        list.add(first[1]);
        res.add(list);
        return res;
    }

    public static void main(String[] args) {    
        int[][] arr = {
            {1300, 1500},
            {930, 1200},
            {830, 845},
        };
        System.out.println(meetingRooms(arr, 820, 830));
        System.out.println(meetingRooms(arr, 1400, 1450));

        int[][] arr2 = {
            {1,2},{5,6},{1,3},{4,10}
        };
        System.out.println(mergeInterval2(arr2));
        System.out.println(mergeInterval(arr2));
    }
}

