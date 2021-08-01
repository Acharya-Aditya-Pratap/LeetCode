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
     * @return: the minimum number of conference rooms required
     */
    public int minMeetingRooms(List<Interval> intervals) {
        // Write your code here
        Collections.sort(intervals, (i1, i2) -> i1.start - i2.start);
        if (intervals.isEmpty()) return 0;
        PriorityQueue<Interval> pq = new PriorityQueue<>((i1, i2) -> i1.end - i2.end);
        pq.add(intervals.get(0));
        for (int i=1; i<intervals.size(); i++) 
            if (overlaps(intervals.get(i), pq.peek())) {
               pq.add(intervals.get(i));     
            } else {
               Interval head = pq.poll();
               head.end = intervals.get(i).end;
               pq.add(head);     
            }

        return pq.size();    
    }

    private boolean overlaps(Interval a, Interval b) {
        return (a.start >= b.start && a.start < b.end) ||
            (b.start >= a.start && b.start < a.end);
    }
}
