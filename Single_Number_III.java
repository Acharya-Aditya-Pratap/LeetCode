class Solution {
    public int[] singleNumber(int[] nums) {
        int []ans = new int[2];
        int xor = 0;
        
        for (int ele : nums) xor ^= ele; // O(n)
        
        int mask = 1;
        
        while((mask & xor) == 0) mask = mask<<1;  //O(32)
            
        for (int ele : nums) { //O(N)
            if ((mask & ele) == 0) ans[0] ^= ele;
            else ans[1] ^= ele;
        }
        
        return ans;
    }
}
