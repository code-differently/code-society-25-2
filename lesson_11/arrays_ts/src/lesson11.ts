
export function getConcatenation(nums: number[]): number[] {
    const n = nums.length;
    const ans: number[] = new Array(2 * n);

    for (let i = 0; i < n; i++) {
        ans[i] = nums[i];
        ans[i + n] = nums[i];
    }
        return ans;  
}

export function findWordsContaining(words: string[], x: string): number[] {
    const indices: number[] = [];

    for (let i = 0; i < words.length; i++){
        const word = words[i];
        if (word.indexOf(x) !== -1) {
            indices.push(i);
     }
    }
    return indices;
  }