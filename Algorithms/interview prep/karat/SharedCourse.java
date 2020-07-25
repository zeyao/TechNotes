import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 You are a developer for a university. Your current project is to develop a system for students to find courses they share with friends. 
 The university has a system for querying courses students are enrolled in, returned as a list of (ID, course) pairs.
Write a function that takes in a list of (student ID number, course name) pairs and returns, 
for every pair of students, a list of all courses they share.

student_course_pairs_1 = [
["58", "Linear Algebra"],
["94", "Art History"],
["94", "Operating Systems"],
["17", "Software Design"],
["58", "Mechanics"],
["58", "Economics"],
["17", "Linear Algebra"],
["17", "Political Science"],
["94", "Economics"],
["25", "Economics"],
["58", "Software Design"],
]

Sample Output (pseudocode, in any order):

find_pairs(student_course_pairs_1) =>
{
[58, 17]: ["Software Design", "Linear Algebra"]
[58, 94]: ["Economics"]
[58, 25]: ["Economics"]
[94, 25]: ["Economics"]
[17, 94]: []
[17, 25]: []
}
 * 
 */
public class SharedCourse {

    public static Map<List<String>, List<String>> getPossiblePairs(String[][] input){
        Map<List<String>, List<String>> res = new HashMap<>();
        Map<String, Set<String>> map = new HashMap<>();
        for (String[] arr : input) {
            String id = arr[0];
            String course = arr[1];
            if (!map.containsKey(id)) {
                Set<String> set = new HashSet<>();
                set.add(course);
                map.put(id, set);
            }
            else {
                map.get(id).add(course);
            }
        }
        List<String> idList = new ArrayList<>(map.keySet());
        for (int i = 0; i < idList.size(); i++) {
            for (int j = i + 1; j < idList.size(); j++) {
                String id1 = idList.get(i);
                String id2 = idList.get(j);
                List<String> pair = new ArrayList<>(Arrays.asList(new String[]{id1, id2}));
                List<String> common = new ArrayList<>();
                for (String c : map.get(id1)) {
                    Set<String> courseSet = map.get(id2);
                    if (courseSet.contains(c)) {
                        common.add(c);
                    }
                }
                res.put(pair, common);
            }
        }
        return res;
    }
    
    public static void main(String[] args) {    
        String[][] mat = new String[][]{
            {"42", "Software Design"},
            {"0", "TEST"},
            {"9", "ART"}

        };
        System.out.println(getPossiblePairs(mat));
        
        String[][] mat2 = new String[][] {
            {"58", "Linear Algebra"},
            {"94", "Art History"},
            {"94", "Operating Systems"},
            {"17", "Software Design"},
            {"58", "Mechanics"},
            {"58", "Economics"},
            {"17", "Linear Algebra"},
            {"17", "Political Science"},
            {"94", "Economics"},
            {"25", "Economics"},
            {"58", "Software Design"},
        };
        System.out.println(getPossiblePairs(mat2));
    }
}

