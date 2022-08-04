package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

public class Q98 {
    public static Deque<TreeNode> stack;
    private static boolean valid;
    private static int rootVal;

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) {
        Q98 solution = new Q98();
        TreeNode root = new TreeNode(2147483644,
                new TreeNode(-2147483648),
                new TreeNode(2147483646, new TreeNode(2147483645), new TreeNode(2147483647)));
        System.out.println(solution.isValidBST(root));
    }

    public boolean isValidBST(TreeNode root) {
        valid = true;
        stack = new ArrayDeque<>();
        stack.push(root);
        rootVal = root.val;
        dfs(Long.MIN_VALUE, Long.MAX_VALUE);
        return valid;
    }

    private void dfs(long min, long max) {
        TreeNode node;
        node = stack.pop();
        if (node.left != null) {
            if (node.left.val >= node.val) {
                valid = false;
            }
            if (rootVal != node.val && (node.left.val >= max || node.left.val <= min)) {
                valid = false;
            }
            stack.push(node.left);
            dfs(min, node.val);
        }
        if (node.right != null) {
            if (node.right.val <= node.val) {
                valid = false;
            }
            if (rootVal != node.val && (node.right.val >= max || node.right.val <= min)) {
                valid = false;
            }
            stack.push(node.right);
            dfs(node.val, max);
        }
    }
}
