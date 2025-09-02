export class Lesson11 {

  getConcatenation(nums: number[]): number[] {
    const n = nums.length;
    const ans: number[] = new Array(2 * n);

    for (let i = 0; i < n; i++) {
      ans[i] = nums[i];       // first half
      ans[i + n] = nums[i];   // second half
    }

    return ans;
  }

  findWordsContaining(words: string[], x: string): number[] {
    const ans: number[] = [];

    for (let i = 0; i < words.length; i++) {
      if (words[i].indexOf(x) !== -1) { // if word contains x
        ans.push(i); // store the index
      }
    }

    return ans;
  }
}
