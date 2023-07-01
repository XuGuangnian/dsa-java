package com.itheima.datastructure.linkedlist;

/**
 * 反转链表
 */
public class E01Leetcode206 {

    // 从旧链表拿到每个节点，创建新节点添加至新链表头部
    public ListNode reverseList1(ListNode head) {
        ListNode newHead = null;
        ListNode p = head;
        while (p != null) {
            newHead = new ListNode(p.val, newHead);
            p = p.next;
        }
        return newHead;
    }

    // 移除旧链表头部，加到新链表头部
    public ListNode reverseList2(ListNode head) {
        List list1 = new List(head);
        List list2 = new List(null);
        while (list1.head != null) {
            list2.addFirst(list1.removeFirst());
        }
        return list2.head;
    }

    static class List {
        ListNode head;

        public List(ListNode head) {
            this.head = head;
        }

        public void addFirst(ListNode first) {
            first.next = head;
            head = first;
        }

        public ListNode removeFirst() {
            ListNode first = head;
            if (first != null) {
                head = first.next;
            }
            return first;
        }
    }

    // 递归
    public ListNode reverseList3(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode last = reverseList3(head.next);
        head.next.next = head;
        head.next = null;
        return last;
    }

    /**
     * 将第二个节点移到第一个节点
     * 1 2 3 4 5
     * 2 1 3 4 5
     */
    public ListNode reverseList4(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode n2 = head.next; // 链表第2个节点
        ListNode n1 = head; // n1:新链表头, head:旧链表头
        while (n2 != null) {
            head.next = n2.next;
            n2.next = n1;
            n1 = n2;
            n2 = head.next;
        }
        return n1;
    }

    // 旧链表节点不断指向新链表头部
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = null;
        while (head != null) {
            ListNode o2 = head.next;
            head.next = newHead;
            newHead = head;
            head = o2;
        }
        return newHead;
    }

    public static void main(String[] args) {
        ListNode o5 = new ListNode(5, null);
        ListNode o4 = new ListNode(4, o5);
        ListNode o3 = new ListNode(3, o4);
        ListNode o2 = new ListNode(2, o3);
        ListNode o1 = new ListNode(1, o2);
        System.out.println(o1);
        ListNode n1 = new E01Leetcode206().reverseList(o1);
        System.out.println(n1);
    }

}
