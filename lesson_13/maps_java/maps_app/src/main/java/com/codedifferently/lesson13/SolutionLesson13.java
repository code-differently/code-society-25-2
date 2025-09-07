import java.util.HashMap;
import java.util.Map;

public class SolutionLesson13 {
  /**
   * Returns the sum over all characters of |index_in_s - index_in_t|. Guarded so that if a char in
   * t isn't present in s, we just skip it. (Adjust if your assignment expects a different policy.)
   */
  public static int permutationDifference(String s, String t) {
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
        // GUARD: char in t not found in s → skip (or treat as 0 effect)
        // If your rubric says otherwise, swap this branch to handle differently.
        continue;
      }
      sum += Math.abs(i - j);
    }
    return sum;
  }
}
