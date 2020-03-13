package com.example.demo.leetcode;

/**
 * @author ningkuizhang
 * @desc
 * @date 2020/3/12 23:24
 */
public class 两数相加 {

    /**
     * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
     * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
     * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     * 示例：
     * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
     * 输出：7 -> 0 -> 8
     * 原因：342 + 465 = 807
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/add-two-numbers
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode node1 = new ListNode(5);
        ListNode node2 = new ListNode(9);

        ListNode listNode = solution.addTwoNumbers(node2, node1);
        printNode(listNode);
    }

    private static void printNode(ListNode node) {
        while (node != null) {
            System.out.println(node.val);
            node = node.next;
        }
    }


    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    static class Solution {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            int sumVal = l1.val + l2.val;
            int carryFlag = 0;
            if (sumVal >= 10) {
                sumVal = sumVal - 10;
                carryFlag = 1;
            }
            ListNode result = new ListNode(sumVal);
            iterateHelper(result, l1.next, l2.next, carryFlag);
            return result;
        }

        /**
         * 退出条件：当两者为空时，直接退出，不需要进行迭代
         * @param result
         * @param l1
         * @param l2
         * @param carryFlag 进位标志
         */
        private static void iterateHelper(ListNode result, ListNode l1, ListNode l2, int carryFlag) {
            if (l1 == null && l2 == null) {
                if (carryFlag == 1) {
                    ListNode carryNode = new ListNode(carryFlag);
                    result.next = carryNode;
                }
                return;
            }

            // 获取元数据
            int firstVal = 0;
            ListNode l1NextNode = null;
            if (l1 != null) {
                firstVal = l1.val;
                l1NextNode = l1.next;
            }
            int secondVal = 0;
            ListNode l2NextNode = null;
            if (l2 != null) {
                secondVal = l2.val;
                l2NextNode = l2.next;
            }

            // 获取两者之和
            int sumVal = firstVal + secondVal + carryFlag;
            if (sumVal >= 10) {
                sumVal -= 10;
                carryFlag = 1;
            } else {
                carryFlag = 0;
            }

            ListNode currentNode = new ListNode(sumVal);
            result.next = currentNode;
            iterateHelper(currentNode, l1NextNode, l2NextNode, carryFlag);

        }
    }
}
