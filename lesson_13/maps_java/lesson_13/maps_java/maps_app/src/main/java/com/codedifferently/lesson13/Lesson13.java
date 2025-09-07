package com.codedifferently.lesson13;

import java.util.HashMap;
import java.util.Map;

/**
 * Lesson 13 solution for "Permutation Difference between Two Strings".
 */
public class Lesson13 {

    /**
     * Sum over characters of |index_in_s - index_in_t|.
     * Guard: if a char in t is not present in s, skip it.
     */
    public int findPermutationDifference(String s, String t) {
        if (s == null || t == null) return 0;

        Map<Character, Integer> pos = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            pos.put(s.charAt(i), i);
        }

        int sum = 0;
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            Integer j = pos.get(c);
            if (j == null) {
                continue; // skip missing chars
            }
            sum += Math.abs(i - j);
        }
        return sum;
    }
}
