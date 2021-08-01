/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 */

public class Solution {
    /**
     * @param root: the given tree
     * @return: the number of uni-value subtrees.
     */

    public int unv = 0; 
    public int countUnivalSubtrees(TreeNode root) {
        // write your code here
        unv = 0;
        dfs(root);
        return unv;
    }

    private Set<Integer> dfs(TreeNode root) {
        if (root == null) return Collections.emptySet();
        
        Set<Integer> values = new HashSet<>();
        values.addAll(dfs(root.left));
        values.addAll(dfs(root.right));
        values.add(root.val);

        if (values.size() == 1)
            this.unv++;

        return values;    
    }
}
