package hashTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LC49GroupAnagrams {
	/**Given an array of strings, group anagrams together.

	Example:

	Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
	Output:
	[
  		["ate","eat","tea"],
  		["nat","tan"],
  		["bat"]
	]
	**/
	
	public List<List<String>> groupAnagrams(String[] strs) {
        Map<String,List<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] arr = s.toCharArray();
            Arrays.sort(arr);
            String key = String.valueOf(arr);
            if (map.containsKey(key)) {
                List<String> list = map.get(key);
                list.add(s);
            } else {
                List<String> list = new ArrayList<>();
                list.add(s);
                map.put(key,list);
            }
        }
        
        List<List<String>> returnList = new ArrayList<>();
        for (Map.Entry<String,List<String>> entry : map.entrySet()) {
            returnList.add(entry.getValue());
        }
        return returnList;
    }
}
