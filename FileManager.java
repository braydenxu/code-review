/**
 * code sample for interview code review section  -- Java Version
 *
 * This code is a simplified version of two functions of the tree-like file manager
 * modifyNode() is to modify (update, delete) any node (folder or file) metadata to a new value
 * findPath() is to find all the paths from root to leaf (getting all the absolute paths of any specified file)
 *
 *
 */

import java.util.*;
public class FileManager {
    private static class TreeNode {
        public TreeNode left;
        public TreeNode right;
        public int val;
        TreeNode(int val) {
            this.val = val;
        }
    }

    private static TreeNode modifyNode(TreeNode root, int target, int newValue) {
        if (root == null) {
            return null;
        }

        if (root.val == target) {
            root.val = newValue;
            return root;
        }

        TreeNode left = modifyNode(root.left, target, newValue);
        TreeNode right = modifyNode(root.right, target, newValue);

        return left != null ? left : right;
    }

    public static ArrayList<ArrayList<Integer>> findPath(TreeNode root, int target) {
        ArrayList<Integer> path = new ArrayList<Integer>();
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();

        findPath(root, target, path, result);

        return result;
    }

    private static void findPath(TreeNode root, int target, ArrayList<Integer> path, ArrayList<ArrayList<Integer>> result) {
        if (root == null) {
            return;
        }

        if (root.left == null && root.right == null) {
            if (root.val == target) {
                path.add(root.val);
                result.add(new ArrayList<Integer>(path));
                path.remove(path.size()-1);
            }
            return;
        }
        path.add(root.val);
        findPath(root.left, target, path, result);
        findPath(root.right, target, path, result);
        path.remove(path.size()-1);
    }


    public static void main(String[] args) {
        // build tree
        /**
         *         1
         *      2    3
         *    4  5     4
         */
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(4);

        // test case for modifyNode
        TreeNode res = modifyNode(root, 5, 12);
        System.out.println("modifyNode() result:");
        if (res == null) {
            System.out.println("not found");
        } else {
            System.out.println(res.val);
        }

        System.out.println();

        // test case for findPath
        System.out.println("findPath() result:");
        ArrayList<ArrayList<Integer>> result = findPath(root, 4);
        for (int i = 0; i < result.size(); i++) {
            for (int j = 0; j < result.get(i).size(); j++) {
                System.out.print(result.get(i).get(j));
            }
            System.out.println();
        }
    }



}
