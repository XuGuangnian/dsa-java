package com.itheima.datastructure.binarytree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TreeTraversal2 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(
                new TreeNode(new TreeNode(4), 2, null),
                1,
                new TreeNode(new TreeNode(5), 3, new TreeNode(6))
        );
        // preAndInorderTraversal(root);
        // postorderTraversal(root);
        traversal(root);
    }

    /*
            1
           / \
          2   3
         /   / \
        4   5   6
     */
    public static List<Integer> traversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        List<Integer> result2 = new ArrayList<>();
        List<Integer> result3 = new ArrayList<>();

        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode curr = root;
        TreeNode pop = null;

        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                stack.push(curr);
                // 待处理左子树
                result.add(curr.val);
                curr = curr.left;
            } else {
                TreeNode peek = stack.peek();
                if (peek.right == null) {
                    // 没有右子树
                    result2.add(peek.val);
                    pop = stack.pop();
                    result3.add(pop.val);
                } else if (peek.right == pop) {
                    // 右子树处理完成
                    pop = stack.pop();
                    result3.add(pop.val);
                } else {
                    // 待处理右子树
                    result2.add(peek.val);
                    curr = peek.right;
                }
            }
        }

        System.out.println(result);
        System.out.println(result2);
        System.out.println(result3);
        return result;
    }

    public static List<Integer> preAndInorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        List<Integer> result2 = new ArrayList<>();

        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode curr = root;

        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                stack.push(curr);
                result.add(curr.val);
                curr = curr.left;
            } else {
                TreeNode pop = stack.pop();
                result2.add(pop.val);
                curr = pop.right;
            }
        }

        System.out.println(result);
        System.out.println(result2);
        return result;
    }

    public static List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode curr = root;
        TreeNode pop = null;

        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                stack.push(curr);
                curr = curr.left;
            } else {
                TreeNode peek = stack.peek();
                if (peek.right == null || peek.right == pop) {
                    pop = stack.pop();
                    result.add(pop.val);
                } else {
                    curr = peek.right;
                }
            }
        }

        System.out.println(result);
        return result;
    }
}
