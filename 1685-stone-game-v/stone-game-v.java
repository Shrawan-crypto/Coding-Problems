class Solution {

    int[][] memo;
    int[] prefix;

    public int stoneGameV(int[] stoneValue) {

        int n = stoneValue.length;

        memo = new int[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(memo[i], -1);
        }

        prefix = new int[n + 1];

        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + stoneValue[i];
        }

        return solve(0, n - 1);
    }

    private int solve(int left, int right) {

        if (left == right) {
            return 0;
        }

        if (memo[left][right] != -1) {
            return memo[left][right];
        }

        int ans = 0;

        for (int k = left; k < right; k++) {

            int leftSum = prefix[k + 1] - prefix[left];

            int rightSum =
                    prefix[right + 1] - prefix[k + 1];

            if (leftSum < rightSum) {

                ans = Math.max(
                        ans,
                        leftSum + solve(left, k));

            } else if (leftSum > rightSum) {

                ans = Math.max(
                        ans,
                        rightSum + solve(k + 1, right));

            } else {

                ans = Math.max(
                        ans,
                        Math.max(
                                leftSum + solve(left, k),
                                rightSum + solve(k + 1, right)));
            }
        }

        return memo[left][right] = ans;
    }
}