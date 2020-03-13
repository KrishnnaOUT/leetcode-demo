package com.example.demo.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ningkuizhang
 * @desc
 * @date 2020/3/12 22:09
 */
public class 两数之和 {

    /**
     * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
     * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
     * 示例:
     * 给定 nums = [2, 7, 11, 15], target = 9
     * 因为 nums[0] + nums[1] = 2 + 7 = 9
     * 所以返回 [0, 1]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/two-sum
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {4, 5,2,1,5,3,2};
        int[] result = solution.twoSum(nums, 7);
        System.out.println(result.length);
        System.out.println(result[0] + ":" + result[1]);
    }

    static class Solution {
        public int[] twoSum(int[] nums, int target) {
            // .. 结果数组
            int[] result = new int[2];
            // .. 求解辅助集合对象
            Map<Integer, Integer> helpMap = new HashMap<>();
            for(int i = 0; i < nums.length; i++) {
                int currNum = nums[i];
                // 确认集合helpMap中是否有对应的值，若有则直接结束循环，
                // 输出helpMap目标值得key和当前遍历对象的数组下标
                if (helpMap.containsValue(currNum)) {
                    // 确定包含
                    // 获取包含值对应的key
                    for(Map.Entry<Integer, Integer> searchModel : helpMap.entrySet()) {
                        if (searchModel.getValue().intValue() == currNum) {
                            result[0] = searchModel.getKey();
                            break;
                        }
                    }
                    result[1] = i;
                    return result;
                }
                // 不包含？将对应diffNum放入辅助集合中
                int diffNum = target - currNum;
                helpMap.put(i, diffNum);
            }

            return result;
        }
    }
}
