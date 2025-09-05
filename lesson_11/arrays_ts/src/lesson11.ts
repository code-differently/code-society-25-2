/**
 * Provide the solution to LeetCode 1929 here:
 * https://leetcode.com/problems/concatenation-of-array
 */
export function getConcatenation(nums: number[]): number[] {
  const ans: number[] = [];
    
    for (let i = 0; i < 2; i++) {
	    for (let j = 0; j < nums.length; j++) {
		    ans[i * nums.length + j] = nums[j];
		}
	}
	return ans;
}

/**
 * Provide the solution to LeetCode 2942 here:
 * https://leetcode.com/problems/find-words-containing-character/
 */
export function findWordsContaining(words: string[], x: string): number[] {
  const resultArray: number[] = [];

    for (let i = 0; i < words.length; i++) { 
      for (let j = 0; j < words[i].length; j++) {
         if (words[i].charAt(j) === x) {
            resultArray.push(i);
            break;
         }    
      }
    }    
     return resultArray;
}
