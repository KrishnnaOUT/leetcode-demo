package com.example.demo.leetcode;

import java.time.Clock;

/**
 * @author ningkuizhang
 * @desc
 * @date 2020/3/18 11:02
 */
public class 最长回文子串 {

    /**
     * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
     * 示例 1：
     * 输入: "babad"
     * 输出: "bab"
     * 注意: "aba" 也是一个有效答案。
     *
     * 示例 2：
     * 输入: "cbbd"
     * 输出: "bb"
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/longest-palindromic-substring
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        String s = "";
        Solution solution = new Solution();
        long startMills = Clock.systemDefaultZone().millis();
        String result = solution.longestPalindrome(s);
        long endMills = Clock.systemDefaultZone().millis();
        System.out.println("结果为：" + result);
        System.out.println("耗时：" + (endMills - startMills) + " ms");
    }

    static class Solution {
        /**
         * 思路：寻找到一个字符串汇中的对称点。
         * 然后在此对称点基础上往左右延伸查询当前对称点的最长子串。
         * 若当前遍历的子串比以往的长，则替换。否则继续往下遍历。直至末尾
         */
        public String longestPalindrome(String s) {
            String result = "";
            // 寻找对称的中间值
            char[] chars = s.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                // 两种情况
                // 第一种：以middleChar为中心，两边对称。
                int x = i-1, y = i + 1;
                for (; x >= 0 && y < chars.length; x--, y++) if (chars[x] != chars[y]) break;
                result = s.substring(x + 1, y).length() > result.length() ? s.substring(x + 1, y) : result;
                // 第二种：middleChar和下一个字符对称。两边对称
                x=i;
                y = i + 1;
                for (; x >= 0 && y < chars.length; x--, y++) if (chars[x] != chars[y]) break;
                result = s.substring(x < i ? x + 1 : x, y).length() > result.length() ? s.substring(x < i ? x + 1 : x, y) : result;
            }
            return result;
        }
    }
}
