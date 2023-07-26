package com.itheima.datastructure.binarysearchtree;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestBSTTree {

    public BSTTree createTree() {
        /*
                     4
                   /   \
                  2     6
                 / \   / \
                1   3 5   7
         */
        BSTTree.BSTNode n1 = new BSTTree.BSTNode(1, "张无忌");
        BSTTree.BSTNode n3 = new BSTTree.BSTNode(3, "宋青书");
        BSTTree.BSTNode n2 = new BSTTree.BSTNode(2, "周芷若", n1, n3);

        BSTTree.BSTNode n5 = new BSTTree.BSTNode(5, "说不得");
        BSTTree.BSTNode n7 = new BSTTree.BSTNode(7, "殷离");
        BSTTree.BSTNode n6 = new BSTTree.BSTNode(6, "赵敏", n5, n7);
        BSTTree.BSTNode root = new BSTTree.BSTNode(4, "小昭", n2, n6);

        BSTTree tree = new BSTTree();
        tree.root = root;
        return tree;
    }

    @Test
    void get() {
        BSTTree tree = createTree();
        assertEquals("张无忌", tree.get(1));
        assertEquals("周芷若", tree.get(2));
        assertEquals("宋青书", tree.get(3));
        assertEquals("小昭", tree.get(4));
        assertEquals("说不得", tree.get(5));
        assertEquals("赵敏", tree.get(6));
        assertEquals("殷离", tree.get(7));
        assertNull(tree.get(8));

        assertEquals("张无忌", tree.getByRecursive(1));
        assertEquals("周芷若", tree.getByRecursive(2));
        assertEquals("宋青书", tree.getByRecursive(3));
        assertEquals("小昭", tree.getByRecursive(4));
        assertEquals("说不得", tree.getByRecursive(5));
        assertEquals("赵敏", tree.getByRecursive(6));
        assertEquals("殷离", tree.getByRecursive(7));
        assertNull(tree.getByRecursive(8));
    }

    @Test
    public void minMax() {
        BSTTree tree = createTree();
        assertEquals("张无忌", tree.min());
        assertEquals("殷离", tree.max());
        assertEquals("张无忌", tree.minByRecursive());
        assertEquals("殷离", tree.maxByRecursive());
    }

    @Test
    public void put() {
        BSTTree tree = new BSTTree();
        tree.put(4, new Object());
        tree.put(2, new Object());
        tree.put(6, new Object());
        tree.put(1, new Object());
        tree.put(3, new Object());
        tree.put(7, new Object());
        tree.put(5, new Object());
        assertTrue(isSameTree(createTree().root, tree.root));
        tree.put(1, "教主张无忌");
        assertEquals("教主张无忌", tree.get(1));
    }

    @Test
    public void putByRecursive() {
        BSTTree tree = new BSTTree();
        tree.putByRecursive(4, new Object());
        tree.putByRecursive(2, new Object());
        tree.putByRecursive(6, new Object());
        tree.putByRecursive(1, new Object());
        tree.putByRecursive(3, new Object());
        tree.putByRecursive(7, new Object());
        tree.putByRecursive(5, new Object());
        assertTrue(isSameTree(createTree().root, tree.root));
        tree.putByRecursive(1, "教主张无忌");
        assertEquals("教主张无忌", tree.get(1));
    }

    static boolean isSameTree(BSTTree.BSTNode tree1, BSTTree.BSTNode tree2) {
        if (tree1 == null && tree2 == null) {
            return true;
        }
        if (tree1 == null || tree2 == null) {
            return false;
        }
        if (tree1.key != tree2.key) {
            return false;
        }
        return isSameTree(tree1.left, tree2.left) && isSameTree(tree1.right, tree2.right);
    }

    @Test
    public void predecessor() {
        /*
                     4
                   /   \
                  2     7
                 / \   / \
                1   3 6   8
                     /
                    5
         */
        BSTTree.BSTNode n1 = new BSTTree.BSTNode(1, 1);
        BSTTree.BSTNode n3 = new BSTTree.BSTNode(3, 3);
        BSTTree.BSTNode n2 = new BSTTree.BSTNode(2, 2, n1, n3);

        BSTTree.BSTNode n5 = new BSTTree.BSTNode(5, 5);
        BSTTree.BSTNode n6 = new BSTTree.BSTNode(6, 6, n5, null);
        BSTTree.BSTNode n8 = new BSTTree.BSTNode(8, 8);
        BSTTree.BSTNode n7 = new BSTTree.BSTNode(7, 7, n6, n8);
        BSTTree.BSTNode root = new BSTTree.BSTNode(4, 4, n2, n7);

        BSTTree tree = new BSTTree();
        tree.root = root;

        assertNull(tree.predecessor(1));
        assertEquals(1, tree.predecessor(2));
        assertEquals(2, tree.predecessor(3));
        assertEquals(3, tree.predecessor(4));
        assertEquals(4, tree.predecessor(5));
        assertEquals(5, tree.predecessor(6));
        assertEquals(6, tree.predecessor(7));
        assertEquals(7, tree.predecessor(8));
    }

    @Test
    public void successor() {
        /*
                     5
                   /   \
                  2     7
                 / \   / \
                1   3 6   8
                     \
                      4
         */
        BSTTree.BSTNode n1 = new BSTTree.BSTNode(1, 1);
        BSTTree.BSTNode n4 = new BSTTree.BSTNode(4, 4);
        BSTTree.BSTNode n3 = new BSTTree.BSTNode(3, 3, null, n4);
        BSTTree.BSTNode n2 = new BSTTree.BSTNode(2, 2, n1, n3);

        BSTTree.BSTNode n6 = new BSTTree.BSTNode(6, 6);
        BSTTree.BSTNode n8 = new BSTTree.BSTNode(8, 8);
        BSTTree.BSTNode n7 = new BSTTree.BSTNode(7, 7, n6, n8);
        BSTTree.BSTNode root = new BSTTree.BSTNode(5, 5, n2, n7);

        BSTTree tree = new BSTTree();
        tree.root = root;

        assertEquals(2, tree.successor(1));
        assertEquals(3, tree.successor(2));
        assertEquals(4, tree.successor(3));
        assertEquals(5, tree.successor(4));
        assertEquals(6, tree.successor(5));
        assertEquals(7, tree.successor(6));
        assertEquals(8, tree.successor(7));
        assertNull(tree.successor(8));
    }

    @Test
    @DisplayName("删除叶子节点")
    public void deleteByRecursive1() {
        /*
                     4
                   /   \
                  2     7
                 / \   / \
                1   3 6   8
                     /
                    5
         */
        BSTTree.BSTNode n1 = new BSTTree.BSTNode(1, 1);
        BSTTree.BSTNode n3 = new BSTTree.BSTNode(3, 3);
        BSTTree.BSTNode n2 = new BSTTree.BSTNode(2, 2, n1, n3);

        BSTTree.BSTNode n5 = new BSTTree.BSTNode(5, 5);
        BSTTree.BSTNode n6 = new BSTTree.BSTNode(6, 6, n5, null);
        BSTTree.BSTNode n8 = new BSTTree.BSTNode(8, 8);
        BSTTree.BSTNode n7 = new BSTTree.BSTNode(7, 7, n6, n8);
        BSTTree.BSTNode root1 = new BSTTree.BSTNode(4, 4, n2, n7);

        BSTTree tree1 = new BSTTree();
        tree1.root = root1;

        assertEquals(1, tree1.deleteByRecursive(1));
        assertEquals(3, tree1.deleteByRecursive(3));
        assertEquals(5, tree1.deleteByRecursive(5));
        assertEquals(8, tree1.deleteByRecursive(8));


        /*
                     4
                   /   \
                  2     7
                       /
                      6
         */
        BSTTree.BSTNode x2 = new BSTTree.BSTNode(2, 2);
        BSTTree.BSTNode x6 = new BSTTree.BSTNode(6, 6);
        BSTTree.BSTNode x7 = new BSTTree.BSTNode(7, 7, x6, null);
        BSTTree.BSTNode root2 = new BSTTree.BSTNode(4, 4, x2, x7);
        BSTTree tree2 = new BSTTree();
        tree2.root = root2;

        assertTrue(isSameTree(tree1.root, tree2.root));
    }

    @Test
    @DisplayName("删除只有一个孩子节点")
    public void deleteByRecursive2() {
        /*
                     4
                   /   \
                  2     7
                 / \   /
                1   3 6
                     /
                    5
         */
        BSTTree.BSTNode n1 = new BSTTree.BSTNode(1, 1);
        BSTTree.BSTNode n3 = new BSTTree.BSTNode(3, 3);
        BSTTree.BSTNode n2 = new BSTTree.BSTNode(2, 2, n1, n3);

        BSTTree.BSTNode n5 = new BSTTree.BSTNode(5, 5);
        BSTTree.BSTNode n6 = new BSTTree.BSTNode(6, 6, n5, null);
        BSTTree.BSTNode n7 = new BSTTree.BSTNode(7, 7, n6, null);
        BSTTree.BSTNode root1 = new BSTTree.BSTNode(4, 4, n2, n7);

        BSTTree tree1 = new BSTTree();
        tree1.root = root1;

        assertEquals(7, tree1.deleteByRecursive(7));


        /*
                     4
                   /   \
                  2     6
                 / \   /
                1   3 5
         */
        BSTTree.BSTNode x1 = new BSTTree.BSTNode(1, 1);
        BSTTree.BSTNode x3 = new BSTTree.BSTNode(3, 3);
        BSTTree.BSTNode x2 = new BSTTree.BSTNode(2, 2, x1, x3);
        BSTTree.BSTNode x5 = new BSTTree.BSTNode(5, 5);
        BSTTree.BSTNode x6 = new BSTTree.BSTNode(6, 6, x5, null);
        BSTTree.BSTNode root2 = new BSTTree.BSTNode(4, 4, x2, x6);
        BSTTree tree2 = new BSTTree();
        tree2.root = root2;

        assertTrue(isSameTree(tree1.root, tree2.root));
    }

    @Test
    @DisplayName("删除有两个孩子节点, 被删除与后继不邻")
    public void deleteByRecursive3() {
        /*
                      4
                   /     \
                  2      7
                 / \   /   \
                1   3 5     8
                       \
                        6
         */
        BSTTree.BSTNode n1 = new BSTTree.BSTNode(1, 1);
        BSTTree.BSTNode n3 = new BSTTree.BSTNode(3, 3);
        BSTTree.BSTNode n2 = new BSTTree.BSTNode(2, 2, n1, n3);

        BSTTree.BSTNode n6 = new BSTTree.BSTNode(6, 6);
        BSTTree.BSTNode n5 = new BSTTree.BSTNode(5, 5, null, n6);
        BSTTree.BSTNode n8 = new BSTTree.BSTNode(8, 8);
        BSTTree.BSTNode n7 = new BSTTree.BSTNode(7, 7, n5, n8);
        BSTTree.BSTNode root1 = new BSTTree.BSTNode(4, 4, n2, n7);

        BSTTree tree1 = new BSTTree();
        tree1.root = root1;

        assertEquals(4, tree1.deleteByRecursive(4));


        /*
                      5
                   /     \
                  2      7
                 / \   /   \
                1   3 6     8

         */
        BSTTree.BSTNode x1 = new BSTTree.BSTNode(1, 1);
        BSTTree.BSTNode x3 = new BSTTree.BSTNode(3, 3);
        BSTTree.BSTNode x2 = new BSTTree.BSTNode(2, 2, x1, x3);

        BSTTree.BSTNode x6 = new BSTTree.BSTNode(6, 6);
        BSTTree.BSTNode x8 = new BSTTree.BSTNode(8, 8);
        BSTTree.BSTNode x7 = new BSTTree.BSTNode(7, 7, x6, x8);
        BSTTree.BSTNode root2 = new BSTTree.BSTNode(5, 5, x2, x7);
        BSTTree tree2 = new BSTTree();
        tree2.root = root2;

        assertTrue(isSameTree(tree1.root, tree2.root));
    }

    @Test
    @DisplayName("删除有两个孩子节点, 被删除与后继相邻")
    public void deleteByRecursive4() {
        /*
                     4
                   /   \
                  2     5
                 / \     \
                1   3     6

         */
        BSTTree.BSTNode n1 = new BSTTree.BSTNode(1, 1);
        BSTTree.BSTNode n3 = new BSTTree.BSTNode(3, 3);
        BSTTree.BSTNode n2 = new BSTTree.BSTNode(2, 2, n1, n3);

        BSTTree.BSTNode n6 = new BSTTree.BSTNode(6, 6);
        BSTTree.BSTNode n5 = new BSTTree.BSTNode(5, 5, null, n6);
        BSTTree.BSTNode root1 = new BSTTree.BSTNode(4, 4, n2, n5);

        BSTTree tree1 = new BSTTree();
        tree1.root = root1;

        assertEquals(4, tree1.deleteByRecursive(4));


        /*
                     5
                   /  \
                  2    6
                 / \
                1   3

         */
        BSTTree.BSTNode x1 = new BSTTree.BSTNode(1, 1);
        BSTTree.BSTNode x3 = new BSTTree.BSTNode(3, 3);
        BSTTree.BSTNode x2 = new BSTTree.BSTNode(2, 2, x1, x3);

        BSTTree.BSTNode x6 = new BSTTree.BSTNode(6, 6);
        BSTTree.BSTNode root2 = new BSTTree.BSTNode(5, 5, x2, x6);
        BSTTree tree2 = new BSTTree();
        tree2.root = root2;

        assertTrue(isSameTree(tree1.root, tree2.root));
    }

    @Test
    public void less() {
        /*
                 4
               /   \
              2     6
             / \   / \
            1   3 5   7
         */
        BSTTree.BSTNode n1 = new BSTTree.BSTNode(1, 1);
        BSTTree.BSTNode n3 = new BSTTree.BSTNode(3, 3);
        BSTTree.BSTNode n2 = new BSTTree.BSTNode(2, 2, n1, n3);

        BSTTree.BSTNode n5 = new BSTTree.BSTNode(5, 5);
        BSTTree.BSTNode n7 = new BSTTree.BSTNode(7, 7);
        BSTTree.BSTNode n6 = new BSTTree.BSTNode(6, 6, n5, n7);
        BSTTree.BSTNode root = new BSTTree.BSTNode(4, 4, n2, n6);

        BSTTree tree = new BSTTree();
        tree.root = root;

        assertIterableEquals(List.of(1, 2, 3, 4, 5), tree.less(6));
        assertIterableEquals(List.of(7), tree.greater(6));
        assertIterableEquals(List.of(3, 4, 5), tree.between(3, 5));
    }
}