package com.itheima.datastructure.linkedlist;

/**
 * 根据值删除节点
 */
public class E02Leetcode203 {
    /**
     * 方法1
     *
     * @param head 链表头
     * @param val  目标值
     * @return 删除后的链表头
     */
    public ListNode removeElements1(ListNode head, int val) {
        ListNode s = new ListNode(-1, head);
        ListNode p1 = s;
        ListNode p2 = s.next;
        while (p2 != null) {
            if (p2.val == val) {
                p1.next = p2.next;
            } else {
                p1 = p1.next;
            }
            p2 = p1.next;
        }
        return s.next;
    }

    /**
     * 方法2
     *
     * @param p   链表头
     * @param val 目标值
     * @return 删除后的链表头
     */
    public ListNode removeElements(ListNode p, int val) {
        if (p == null) {
            return null;
        }
        if (p.val == val) {
            return removeElements(p.next, val);
        } else {
            p.next = removeElements(p.next, val);
            return p;
        }
    }

    public static void main(String[] args) {
        ListNode head = ListNode.of(1, 2, 6, 3, 6);
        // ListNode head = ListNode.of(6, 6, 6, 6);
        System.out.println(head);
        System.out.println(new E02Leetcode203()
                .removeElements1(head, 6));
    }
}
