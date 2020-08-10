import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**

Sample Input:
employees = [
  "1, Bill, Engineer",
  "2, Joe, HR",
  "3, Sally, Engineer",
  "4, Richard, Business",
  "6, Tom, Engineer"
]

friendships = [
  "1, 2",
  "1, 3",
  "3, 4"
]
 * 
 */
public class FriendCycle {
    //Pt 1.Given employees and friendships, find all adjacencies that denote the friendship, 
    //A friendship is bi-directional/mutual so if 1 is friends with 2, 2 is also friends with 1.
/** 
    Sample Output:
    Output:
    1: 2, 3
    2: 1
    3: 1, 4
    4: 3
    6: None
*/
    private static List<String> findFriend(String[] employees, String[] friendships) {
        List<String> res = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (String e : employees) {
            String[] s = e.split(",");
            map.put(s[0], new ArrayList<>());
        }
        for (String str : friendships) {
            String[] s = str.split(",");
            map.get(s[0]).add(s[1]);
            map.get(s[1]).add(s[0]);
        }
        map.entrySet().forEach(entry -> {
            StringBuilder sb = new StringBuilder();
            sb.append(entry.getKey()).append(":");
            List<String> friendList = entry.getValue();
            if (friendList.isEmpty()) {
                sb.append("None");
            }
            else {
                for (String f: friendList) {
                    sb.append(f).append(",");
                }
                sb.deleteCharAt(sb.length()-1);
            }
            res.add(sb.toString());
        });
        return res;

    }

    //Pt 2.Now for each department count the number of employees that have a friend in another department
    /**
     * Sample Output:
    Output:
    "Engineer: 2 of 3"
    "HR: 1 of 1"
    "Business: 1 of 1"
    */  

    
    private static List<String> findFriend2(String[] employees, String[] friendships) {
        //just need to use department as key, and use the res of first question, can find a man's frend, check is there any friend from other department, if no count -1;
        List<String> res = new ArrayList<>();
        Map<String, List<String>> departmentMap = new HashMap<>();
        for (String e : employees) {
            String[] arr = e.split(",");
            String department = arr[2];
            if (!departmentMap.containsKey(department)) {
                List<String> list = new ArrayList<>();
                list.add(arr[0]);
                departmentMap.put(department, list);
            }
            else {
                departmentMap.get(department).add(arr[0]);
            }
        }

        Map<String, List<String>> friendMap = new HashMap<>();
        for (String e : employees) {
            String[] s = e.split(",");
            friendMap.put(s[0], new ArrayList<>());
        }
        for (String str : friendships) {
            String[] s = str.split(",");
            friendMap.get(s[0]).add(s[1]);
            friendMap.get(s[1]).add(s[0]);
        }
        
        departmentMap.entrySet().forEach(e -> {
            String depart = e.getKey();
            List<String> employeeList = e.getValue();
            int size = employeeList.size();
            int nofriend = 0;
            for (String employee : employeeList) {
                List<String> friend = friendMap.get(employee);
                if (friend.isEmpty()) {
                    nofriend++;
                    continue;
                }
                boolean inOtherDepart = false;
                for (String f : friend) {
                    if (!employeeList.contains(f)) {
                        inOtherDepart = true;
                        break;
                    }
                }
                if (!inOtherDepart) {
                    nofriend++;
                }
            }
            //"Engineer: 2 of 3"
            String s = depart + " : " + (size - nofriend) + "of " + size;
            res.add(s);
        });
        return res;
    }

    //Pt 3.Output if all the employees are in a same friend cycle.
    //use UF
    public static boolean isAllInOneFriendCycle(String[] employees, String[] friendships) {
        Map<String, String> unionMap = new HashMap<>();
        for (String e : employees) {
            String[] arr = e.split(",");
            unionMap.put(arr[0], arr[0]);
        }

        System.out.println(unionMap);

        for (String str : friendships) {
            String[] pair = str.split(",");
            union(unionMap, pair[0], pair[1]);
        }
        String targertValue = null;
        for (Map.Entry<String, String> entry : unionMap.entrySet()) {
            String val = entry.getValue();
            if (targertValue == null) {
                targertValue = val;
            }
            else {
                if (!val.equals(targertValue)) {
                    return false;
                }
            }
        }
        return true;

    }

    private static String find(Map<String, String> unionMap, String p) {
        return unionMap.get(p);
    }

    private static void union(Map<String, String> unionMap, String p, String q) {
        String pId = find(unionMap, p);
        String qId = find(unionMap, q);
        if (pId.equals(qId)) {
            return;
        }
        for (Map.Entry<String, String> entry : unionMap.entrySet()) {
            if (entry.getValue().equals(pId)) {
                entry.setValue(qId);
            }
        }
    }

    public static void main(String[] args) {    
        String[] employee = new String[]{"1,Bill,Engineer","2,Joe,HR","3,Sally,Engineer","4,Richard,Business","6,Tom,Engineer"};
        String[] friendships = new String[]{"1,2","1,3","3,4"};
        System.out.println(findFriend(employee, friendships));
        System.out.println(findFriend2(employee, friendships));
        System.out.println(isAllInOneFriendCycle(employee, friendships));
        String[] friendships1 = new String[]{"1,2","1,3","3,4","1,6"};
        System.out.println(isAllInOneFriendCycle(employee, friendships1));
    }
}

