import java.util.stream.*;
public class Solution {
    /**
     * @param strings: a string array
     * @return: return a list of string array
     */
    public static Map<String, List<String>> cache; 
    public List<List<String>> groupStrings(String[] strings) {
        // write your code here
        cache = new HashMap<>();
        for (String ele : strings) {
            String hash = computeHash(ele);
            List<String> list = cache.getOrDefault(hash, new ArrayList<>());
            list.add(ele);
            cache.put(hash, list);
        }

        return cache.values().stream().collect(Collectors.toList());
    }

    private String computeHash(String s) {
        String hash = "0";
        for (int i=1; i<s.length(); i++)
            hash += ((s.charAt(i)-s.charAt(0) + 26)%26) + "";
        return hash;    
    }
}
