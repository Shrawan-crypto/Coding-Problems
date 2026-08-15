class Solution {
    public int longestSubsequence(int[] nums) {
        int xor = 0;

        for (int num : nums) {
            xor ^= num;
        }

        boolean hasNonZero = false;

        for (int num : nums) {
            if (num != 0) {
                hasNonZero = true;
                break;
            }
        }

        if (!hasNonZero) {
            return 0;
        }

        if (xor != 0) {
            return nums.length;
        }

        return nums.length - 1; 
    }
}