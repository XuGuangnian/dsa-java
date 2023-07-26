package com.itheima.datastructure.binarysearchtree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 二叉搜索树
 */
public class BSTTree {
    static class BSTNode {
        int key;
        Object value;
        BSTNode left;
        BSTNode right;

        public BSTNode(int key) {
            this.key = key;
        }

        public BSTNode(int key, Object value) {
            this.key = key;
            this.value = value;
        }

        public BSTNode(int key, Object value, BSTNode left, BSTNode right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    BSTNode root;

    /**
     * 根据key查询value
     *
     * @param key
     * @return
     */
    public Object get(int key) {
        BSTNode p = root;
        while (p != null) {
            if (key < p.key) {
                p = p.left;
            } else if (key > p.key) {
                p = p.right;
            } else {
                return p.value;
            }
        }
        return null;
    }

    /**
     * 查询（递归实现）
     *
     * @param key
     * @return
     */
    public Object getByRecursive(int key) {
        return doGet(root, key);
    }

    private Object doGet(BSTNode node, int key) {
        if (node == null) {
            return null;
        }
        if (key < node.key) {
            return doGet(node.left, key);
        } else if (key > node.key) {
            return doGet(node.right, key);
        } else {
            return node.value;
        }
    }

    /**
     * 最小key的值
     *
     * @return
     */
    public Object min() {
        return min(root);
    }

    public Object min(BSTNode node) {
        if (root == null) {
            return null;
        }
        while (node.left != null) {
            node = node.left;
        }
        return node.value;
    }

    /**
     * 最小key的值（递归实现）
     *
     * @return
     */
    public Object minByRecursive() {
        return doMin(root);
    }

    private Object doMin(BSTNode node) {
        if (node == null) {
            return null;
        }
        if (node.left == null) {
            return node.value;
        }
        return doMin(node.left);
    }

    /**
     * 最大key的值
     *
     * @return
     */
    public Object max() {
        return max(root);
    }

    public Object max(BSTNode node) {
        if (root == null) {
            return null;
        }
        while (node.right != null) {
            node = node.right;
        }
        return node.value;
    }

    /**
     * 最大key的值（递归实现）
     *
     * @return
     */
    public Object maxByRecursive() {
        return doMax(root);
    }

    private Object doMax(BSTNode node) {
        if (node == null) {
            return null;
        }
        if (node.right == null) {
            return node.value;
        }
        return doMax(node.right);
    }

    /**
     * 新增节点
     *
     * @param key
     * @param value
     */
    public void put(int key, Object value) {
        BSTNode node = root;
        BSTNode parent = null;
        while (node != null) {
            parent = node;
            if (key < node.key) {
                node = node.left;
            } else if (key > node.key) {
                node = node.right;
            } else {
                // key存在则更新
                node.value = value;
                return;
            }
        }
        // key不存在则新增
        BSTNode newNode = new BSTNode(key, value);
        if (parent == null) {
            root = newNode;
        } else if (key < parent.key) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
    }

    /**
     * 新增节点（递归实现）
     *
     * @param key
     * @param value
     */
    public void putByRecursive(int key, Object value) {
        root = doPut(root, key, value);
    }

    private BSTNode doPut(BSTNode node, int key, Object value) {
        if (node == null) {
            return new BSTNode(key, value);
        }

        if (key < node.key) {
            node.left = doPut(node.left, key, value);
        } else if (key > node.key) {
            node.right = doPut(node.right, key, value);
        } else {
            node.value = value;
        }
        return node;
    }

    /**
     * 查找关键字的前任值
     *
     * @param key
     * @return
     */
    public Object predecessor(int key) {
        // 先找key
        BSTNode node = root;
        BSTNode ancestorFromLeft = null;
        while (node != null) {
            if (key < node.key) {
                node = node.left;
            } else if (key > node.key) {
                ancestorFromLeft = node;
                node = node.right;
            } else {
                break;
            }
        }
        if (node == null) {
            return null;
        } else if (node.left != null) {
            return max(node.left);
        } else {
            return ancestorFromLeft != null ? ancestorFromLeft.value : null;
        }
    }

    /**
     * 查找关键字的后任值
     *
     * @param key
     * @return
     */
    public Object successor(int key) {
        // 先找key
        BSTNode node = root;
        BSTNode ancestorFromRight = null;
        while (node != null) {
            if (key < node.key) {
                ancestorFromRight = node;
                node = node.left;
            } else if (key > node.key) {
                node = node.right;
            } else {
                break;
            }
        }
        if (node == null) {
            return null;
        } else if (node.right != null) {
            return min(node.right);
        } else {
            return ancestorFromRight != null ? ancestorFromRight.value : null;
        }
    }

    /**
     * <h3>根据关键字删除</h3>
     * 要删除某节点（称为 D），必须先找到被删除节点的父节点，这里称为 Parent
     * 1. 删除节点没有左孩子，将右孩子托孤给 Parent
     * 2. 删除节点没有右孩子，将左孩子托孤给 Parent
     * 3. 删除节点左右孩子都没有，已经被涵盖在情况1、情况2 当中，把 null 托孤给 Parent
     * 4. 删除节点左右孩子都有，可以将它的后继节点（称为 S）托孤给 Parent，设 S 的父亲为 SP，又分两种情况
     * 1. SP 就是被删除节点，此时 D 与 S 紧邻，只需将 S 托孤给 Parent
     * 2. SP 不是被删除节点，此时 D 与 S 不相邻，此时需要将 S 的后代托孤给 SP，再将 S 托孤给 Parent
     *
     * @param key
     * @return
     */
    public Object delete(int key) {
        BSTNode node = root;
        BSTNode parent = null;
        while (node != null) {
            if (key < node.key) {
                parent = node;
                node = node.left;
            } else if (key > node.key) {
                parent = node;
                node = node.right;
            } else {
                break;
            }
        }
        if (node == null) {
            return null;
        }
        if (node.left == null) {
            // 没有左孩子（或者没有孩子）
            shift(parent, node, node.right);
        } else if (node.right == null) {
            // 没有右孩子
            shift(parent, node, node.left);
        } else {
            // 左右孩子都有
            // 找后继结点s
            BSTNode s = node.right;
            BSTNode sParent = node;
            while (s.left != null) {
                sParent = s;
                s = s.left;
            }
            if (node.right != s) {
                shift(sParent, s, s.right);
                s.right = node.right;
            }
            shift(parent, node, s);
            s.left = node.left;
        }
        return node.value;
    }

    public Object deleteByRecursive(int key) {
        ArrayList<Object> result = new ArrayList<>();
        root = doDelete(root, key, result);
        return result.isEmpty() ? null : result.get(0);
    }

    public BSTNode doDelete(BSTNode node, int key, ArrayList<Object> result) {
        if (root == null) {
            return null;
        }
        if (key < node.key) {
            node.left = doDelete(node.left, key, result);
            return node;
        }
        if (key > node.key) {
            node.right = doDelete(node.right, key, result);
            return node;
        }
        result.add(node.value);
        if (node.left == null) {
            return node.right;
        }
        if (node.right == null) {
            return node.left;
        }
        BSTNode s = node.right;
        while (s.left != null) {
            s = s.left;
        }
        s.right = doDelete(node.right, s.key, new ArrayList<>());
        s.left = node.left;
        return s;
    }

    /**
     * 托孤方法
     *
     * @param parent  被删除节点的父亲
     * @param deleted 被删除节点
     * @param child   被顶上去的节点
     */
    private void shift(BSTNode parent, BSTNode deleted, BSTNode child) {
        if (parent == null) {
            root = child;
        } else if (deleted == parent.left) {
            parent.left = child;
        } else {
            parent.right = child;
        }
    }

    /*
                 4
               /   \
              2     6
             / \   / \
            1   3 5   7
     */

    // 找 < key 的所有 value
    public List<Object> less(int key) { // key=6
        ArrayList<Object> result = new ArrayList<>();
        BSTNode node = root;
        LinkedList<BSTNode> stack = new LinkedList<>();
        while (node != null || !stack.isEmpty()) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                BSTNode pop = stack.pop();
                if (pop.key < key) {
                    result.add(pop.value);
                } else {
                    break;
                }
                node = pop.right;
            }
        }
        return result;
    }

    // 找 > key 的所有 value
    public List<Object> greater(int key) {
        /*ArrayList<Object> result = new ArrayList<>();
        BSTNode node = root;
        LinkedList<BSTNode> stack = new LinkedList<>();
        while (node != null || !stack.isEmpty()) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                BSTNode pop = stack.pop();
                if (pop.key > key) {
                    result.add(pop.value);
                }
                node = pop.right;
            }
        }
        return result;*/
        ArrayList<Object> result = new ArrayList<>();
        BSTNode node = root;
        LinkedList<BSTNode> stack = new LinkedList<>();
        while (node != null || !stack.isEmpty()) {
            if (node != null) {
                stack.push(node);
                node = node.right;
            } else {
                BSTNode pop = stack.pop();
                if (pop.key > key) {
                    result.add(pop.value);
                } else {
                    break;
                }
                node = pop.left;
            }
        }
        return result;
    }

    // 找 >= key1 且 <= key2 的所有值
    public List<Object> between(int key1, int key2) {
        ArrayList<Object> result = new ArrayList<>();
        BSTNode node = root;
        LinkedList<BSTNode> stack = new LinkedList<>();
        while (node != null || !stack.isEmpty()) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                BSTNode pop = stack.pop();
                if (pop.key > key2) {
                    break;
                } else if (pop.key >= key1) {
                    result.add(pop.value);
                }
                // if (pop.key >= key1 && pop.key <= key2) {
                //     result.add(pop.value);
                // } else if (pop.key > key2) {
                //     break;
                // }
                node = pop.right;
            }
        }
        return result;
    }
}
