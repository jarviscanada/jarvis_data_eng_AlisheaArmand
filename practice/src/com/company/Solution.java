package com.company;

import java.lang.reflect.Array;
import java.util.*;

public class Solution {
    /**
     * How to compare two maps in Java? Two maps are equal when they have the same keys and values
     * @param m1
     * @param m2
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K,V> boolean compareMaps(Map<K, V> m1, Map<K, V> m2){
        boolean isEqual;
       if (m1.equals(m2)) {
           System.out.println(true);
           return true;
       } else {
           System.out.println(false);
           return false;
       }
    }

    /**
     * You are climbing a staircase. It takes n steps to reach the top. Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
     * https://leetcode.com/problems/climbing-stairs/
     * @param n
     * @return climbStairs[n]
     */
    public static int climbStairs(int n) {
        if (n == 1) {
            return 1;
    } else {
            int[] climbStairs = new int[n + 1];
            climbStairs[1] = 1;
            climbStairs[2] = 2;

            for (int i = 3; i <= n; i++) {
                climbStairs[i] = climbStairs[i-1] + climbStairs [i-2];

            }
            System.out.println(climbStairs[n]);
            return climbStairs[n];

        }
}

    /**
     * Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target
     * https://leetcode.com/problems/two-sum/
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {

            if(!map.containsKey(nums[i])) {
                map.put(target - nums[i], i);
                System.out.println(map);

            }else {
                result[0] = i;
                result[1] = map.get(nums[i]);
                break;
            }
        }
        System.out.println(Arrays.toString(result));
        return result;
    }

    /**
     * Implement the myAtoi(string s) function, which converts a string to a 32-bit signed integer (similar to C/C++'s atoi function).
     *
     * The algorithm for myAtoi(string s) is as follows:
     *
     * Read in and ignore any leading whitespace.
     * Check if the next character (if not already at the end of the string) is '-' or '+'. Read this character in if it is either. This determines if the final result is negative or positive respectively. Assume the result is positive if neither is present.
     * Read in next the characters until the next non-digit character or the end of the input is reached. The rest of the string is ignored.
     * Convert these digits into an integer (i.e. "123" -> 123, "0032" -> 32). If no digits were read, then the integer is 0. Change the sign as necessary (from step 2).
     * If the integer is out of the 32-bit signed integer range [-231, 231 - 1], then clamp the integer so that it remains in the range. Specifically, integers less than -231 should be clamped to -231, and integers greater than 231 - 1 should be clamped to 231 - 1.
     * Return the integer as the final result.
     * https://leetcode.com/problems/string-to-integer-atoi/
     * @param s
     * @return int
     */
    public static int myAtoi(String s) {
        int sign = 1;
        int result = 0;
        int i =0;

        while (s.charAt(i) == ' ' || s.charAt(i) == '.') {
            i++;
        }

        for (; i < s.length(); i++){

            if (s.charAt(i) == '-') {
                sign = -1;
                i++;
            }
            while (i < s.length() && s.charAt(i) <= '9' && s.charAt(i) >= '0') {
                result = 10 * result + (s.charAt(i++) - '0');
            }
        }

        System.out.println(sign * result);
          return sign * result;

    }

}
