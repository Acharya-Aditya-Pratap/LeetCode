/**
 * Definition of Interval:
 * public classs Interval {
 *     int start, end;
 *     Interval(int start, int end) {
 *         this.start = start;
 *         this.end = end;
 *     }
 * }
 */

public class Solution {
    /**
     * @param intervals: an array of meeting time intervals
     * @return: if a person could attend all meetings
     */
    public boolean canAttendMeetings(List<Interval> intervals) {
        // Write your code here
        Collections.sort(intervals, (i1, i2) -> i1.start - i2.start);
        for (int i=1;i<intervals.size();i++)
            if (intervals.get(i).start < intervals.get(i - 1).end)
                return false;
        return true;        
    }
}
