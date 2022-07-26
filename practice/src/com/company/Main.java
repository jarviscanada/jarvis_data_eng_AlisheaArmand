package com.company;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Map <String, String> m1 = new HashMap<String, String>();
        m1.put("Alberta", "Edmonton");
        m1.put("Manitoba", "Winnipeg");
        m1.put("BC", "Victoria");

        Map <String, String> m2 = new HashMap<String, String>();
        m2.put("Quebec", "Quebec");
        m2.put("Alberta", "Edmonton");
        m2.put("BC", "Victoria");

        Solution.compareMaps(m1, m2);

        Solution.climbStairs(5);

        //Solution.twoSum(new int[]{2, 7, 11, 15}, 9);
        Solution.twoSum(new int[]{11, 7, 15, 2}, 9);

        //Solution.myAtoi("42");
        //Solution.myAtoi("  -42");
        //Solution.myAtoi("4913 with words");
        Solution.myAtoi("-42  ");
        Solution.myAtoi("-4.2");
        Solution.myAtoi(".42");
        Solution.myAtoi("42.");



    }
}
