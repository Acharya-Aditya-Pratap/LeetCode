public class Solution {
    /**
     * @param intervals: the intervals
     * @param rooms: the sum of rooms
     * @param ask: the ask
     * @return: true or false of each meeting
     */

    static class Interval implements Comparable<Interval>{
        int start,end;
        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int compareTo(Interval i) {
            return this.start - i.start;
        }
    } 

    public boolean[] meetingRoomIII(int[][] intervals, int rooms, int[][] ask) {
        // Write your code here.
        boolean []ans = new boolean[ask.length];
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        
        int []dp = new int[50001];
        
        for (int[] i : intervals) {
            dp[i[0]] += 1;
            dp[i[1]] -= 1;
        }

        for (int t=1; t < dp.length; t++)
            dp[t] += dp[t - 1];

        List<Interval> intervalsWithMaxRooms = new ArrayList<>();
        for (int i=0;i<dp.length;i++) {
            if (dp[i] == rooms) {
                int temp = i;
                while(dp[temp] == rooms) temp++;
                intervalsWithMaxRooms.add(new Interval(i, temp - 1));
                i = temp;
            }
        }   

        for (int k=0; k<ask.length; k++) {
            int []a = ask[k];
            Interval interval = new Interval(a[0], a[1]-1);
            int ip = Collections.binarySearch(intervalsWithMaxRooms, interval);
            if (ip >= 0) {
                ans[k] = false;
            } else {
                ip = -ip - 1;
                ans[k] = (!(ip >= 1 && overlaps(intervalsWithMaxRooms.get(ip - 1), interval))
                    && !(ip < intervalsWithMaxRooms.size() && overlaps(intervalsWithMaxRooms.get(ip), interval)
                ));
            }
        }

        return ans;
    }

    private boolean overlaps(Interval a, Interval b) {
        return (a.start >= b.start && a.start <= b.end) ||
            (b.start >= a.start && b.start <= a.end);
    }
}
