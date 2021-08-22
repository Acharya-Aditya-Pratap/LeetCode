import java.util.stream.*;

public class Solution {
    /**
     * @param target: a target string 
     * @param dictionary: a set of strings in a dictionary
     * @return: an abbreviation of the target string with the smallest possible length
     */
    public String minAbbreviation(String target, String[] dictionary) {
        // Write your code here
        Set<Integer> diffs = Arrays.stream(dictionary)
            .filter(s -> s.length() == target.length())
            .map(s -> computeDiff(target, s))
            .collect(Collectors.toSet());

        int maxConsecutiveZeros = 0, winner=0;

        for (int i=0; i< 1<<target.length(); i++) {
            boolean isValid = true;
            
            for (int diff : diffs) {
                if ((diff & i) == 0) {
                    isValid = false;
                    break;        
                }
            }

            if (isValid) {
                int conecutiveZeros=0;

                for (int j=0; j<target.length()-1; j++) 
                    if (((i>>j) & 3) == 0) conecutiveZeros++;

                if (conecutiveZeros > maxConsecutiveZeros) {
                    maxConsecutiveZeros = conecutiveZeros;
                    winner = i;
                }        
            }
        }

        StringBuilder sb = new StringBuilder();
        int count=0;

        for (int i=0; i<target.length(); i++) {
            if ((winner & 1<<i) != 0) {
                if (count != 0) {
                    sb.append(count);
                    count = 0;    
                }
                sb.append(target.charAt(i));
            } else count++;
        }   

        if (count != 0) sb.append(count);

        return sb.toString();    
    }

    private int computeDiff(String target, String s) {
        int diff = 0;
        for (int i=0; i<s.length(); i++) 
            if (s.charAt(i) != target.charAt(i))
                diff |= 1<<i;
        return diff;        
    }
}
