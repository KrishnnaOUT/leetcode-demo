package com.example.demo.leetcode;

import com.alibaba.fastjson.JSONObject;
import com.sun.xml.internal.ws.policy.sourcemodel.AssertionData;
import jdk.nashorn.internal.AssertsEnabled;
import org.springframework.util.Assert;

import java.time.Clock;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author ningkuizhang
 * @desc
 * @date 2020/3/16 10:15
 */
public class 寻找两个有序数组的中位数 {

    /**
     * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
     * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
     * 你可以假设 nums1 和 nums2 不会同时为空。
     *
     * 示例 1:
     * nums1 = [1, 3]
     * nums2 = [2]
     * 则中位数是 2.0
     *
     * 示例 2:
     * nums1 = [1, 2]
     * nums2 = [3, 4]
     * 则中位数是 (2 + 3)/2 = 2.5
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        int[] nums1 = generateNums(30000);
        int[] nums2 = generateNums(30000);
        System.out.println("nums1: " + JSONObject.toJSONString(nums1));
        System.out.println("nums2: " + JSONObject.toJSONString(nums2));
        Solution solution = new Solution();
        System.out.println("方案一：");
        long startMills = Clock.systemDefaultZone().millis();
        double result = solution.findMedianSortedArrays(nums1, nums2);
        long endMills = Clock.systemDefaultZone().millis();
        System.out.println("执行结果：" + result);
        System.out.println("耗时：" + (endMills - startMills) + " ms");

        System.out.println("方案二：");
        long startMills2 = Clock.systemDefaultZone().millis();
        double result2 = solution.Best_findMedianSortedArrays(nums1, nums2);
        long endMills2 = Clock.systemDefaultZone().millis();
        System.out.println("执行结果：" + result2);
        System.out.println("耗时：" + (endMills2 - startMills2) + " ms");
    }

    /**
     * 生成随机测试数据方法
     * @param length
     * @return
     */
    private static int[] generateNums(int length) {
        int numLimit = length;
        int[] nums = new int[length];
        int initNum = 1;
        Random r = new Random();
        for (int i = 0; i < length; i++) {
            nums[i] = r.nextInt(numLimit) + initNum;
            initNum = nums[i];
        }
        return nums;
    }


    static class Solution {
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            double result = 0.0;
            List<Integer> list1 = nums1 != null ? Arrays.stream(nums1).boxed().collect(Collectors.toList()) : new ArrayList<>();
            List<Integer> list2 = nums2 != null ? Arrays.stream(nums2).boxed().collect(Collectors.toList()) : new ArrayList<>();
            list1.addAll(list2);
            list1 = list1.stream().sorted(Comparator.comparing(Integer::intValue)).collect(Collectors.toList());
            if (list1.size() % 2 == 1) {
                // 奇数个
                result = list1.get(list1.size() / 2);
            } else {
                // 偶数个
                result = ((double)list1.get(list1.size() / 2 - 1) + (double)list1.get(list1.size() / 2)) / 2;
            }
            return result;
        }

        public double Best_findMedianSortedArrays(int[] nums1, int[] nums2) {
            int length = nums1.length + nums2.length;
            int midumn = length / 2 + 1;
            int totalNum = 0, i = 0, j = 0, floorNum = 0, roundNum = 0;
            while (totalNum != midumn ) {
                if (i >= nums1.length) roundNum = nums2[j++];
                else if (j >= nums2.length || nums1[i] < nums2[j]) roundNum = nums1[i++];
                else roundNum = nums2[j++];
                floorNum = (++totalNum == midumn && length % 2 == 0) ? floorNum : roundNum;
            }

            return ((double)floorNum + (double) roundNum) / 2;
        }
    }
}
