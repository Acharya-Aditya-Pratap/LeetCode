public class Solution {
    /**
     * @param meeting: the meetings
     * @param value: the value
     * @return: calculate the max value
     */
    static class Interval {
        int start,end,value;
        public Interval(int start, int end, int value) {
            this.start = start;
            this.end = end;
            this.value = value;
        }
    } 
    public int maxValue(int[][] meeting, int[] value) {
        // write your code here
        Map<Integer, List<Interval>> intervalMap = new HashMap<>();
        int []dp = new int[50001];    
        int n = value.length;
        for (int i=0; i<n; i++) {
            Interval interval = new Interval(meeting[i][0], meeting[i][1], value[i]);
            List<Interval> ints = intervalMap.getOrDefault(meeting[i][0], new ArrayList<>());
            ints.add(interval);
            intervalMap.put(meeting[i][0], ints);    
        }

        for (int i=49999; i>=0; i--) {
            dp[i] = Math.max(dp[i], dp[i+1]);
            if (intervalMap.containsKey(i)) {
                for (Interval interval : intervalMap.get(i)) {
                    dp[i] = Math.max(dp[i], interval.value + dp[interval.end]);
                }
            }
        }

        return dp[0];
    }
}
