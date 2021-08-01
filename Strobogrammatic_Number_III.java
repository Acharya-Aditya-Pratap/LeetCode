public class Solution {
    /**
     * @param low: a string
     * @param high: a string
     * @return: the total strobogrammatic numbers that exist in the range of low <= num <= high
     */
 
    public int strobogrammaticInRange(String low, String high) {
        // Write your code here
        int countLEQLow = 0, countLEQHigh = 0;
        int cumulativeCount = 0;
        for (int len = 1; len <= high.length(); len++) {
            List<String> stbs = findStrobogrammatic(len);
            cache.put(len, stbs);
            cumulativeCount += stbs.size();
            if (len == low.length())
                countLEQLow = cumulativeCount;
            if (len == high.length())
                countLEQHigh = cumulativeCount;    
        }
        
        int count = countLEQHigh + countLEQLow;
        
        Long countGEQHigh = cache.get(high.length())
                .stream()
                .filter(stb -> stb.compareTo(high) > 0)
                .count();

        Long countGEQLow = cache.get(low.length())
                .stream()
                .filter(stb -> stb.compareTo(low) >= 0)
                .count();

        return countLEQHigh - countLEQLow + countGEQLow.intValue() - countGEQHigh.intValue();                
    }

    public static Map<Integer, List<String>> cache = new HashMap<>();

    public List<String> findStrobogrammatic(int n) {
        // write your code here
        if (n <= 0) return Arrays.asList("");
        if (n == 1) return Arrays.asList("0", "1", "8");
        if (n == 2) return Arrays.asList("11","69","88","96");

        if (cache.containsKey(n)) return cache.get(n);

        List<String> ans = new ArrayList<>();
        List<String> stbN2 = findStrobogrammatic1(n-2);
        
        for (String c : Arrays.asList("1", "6", "8", "9")) {
            for (String stb : stbN2) {
                ans.add(c + stb + stb(c));
            }
        }

        cache.put(n, ans);
        return ans; 
    }

    public List<String> findStrobogrammatic1(int n) {
        // write your code here
        if (n <= 0) return Arrays.asList("");
        if (n == 1) return Arrays.asList("0", "1", "8");

        List<String> ans = new ArrayList<>();
        List<String> stbN2 = findStrobogrammatic1(n-2);
        
        for (String c : Arrays.asList("0", "1", "6", "8", "9")) {
            for (String stb : stbN2) {
                ans.add(c + stb + stb(c));
            }
        }

        return ans; 
    }

    private String stb(String c) {
        switch(c) {
            case "9": return "6";
            case "6": return "9";
            case "0": return "0";
            case "8": return "8";
            case "1": return "1";
            default : return "-";
        } 
    }
}
