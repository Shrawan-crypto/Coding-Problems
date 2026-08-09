class Solution {
    int[][] dp;
    int[] suffix;
    int n;

    public int stoneGameII(int[] piles) {
        n = piles.length;

        suffix = new int[n + 1];

        // suffix[i] = total stones from i to n-1
        for (int i = n - 1; i >= 0; i--) {
            suffix[i] = suffix[i + 1] + piles[i];
        }

        dp = new int[n][n + 1];

        return solve(0, 1);
    }

    private int solve(int i, int M) {
        // All remaining piles can be taken
        if (i + 2 * M >= n) {
            return suffix[i];
        }

        if (dp[i][M] != 0) {
            return dp[i][M];
        }

        int best = 0;

        // Try taking X piles
        for (int X = 1; X <= 2 * M && i + X <= n; X++) {

            int newM = Math.max(M, X);

            // Current player's stones =
            // total remaining - opponent's maximum
            int current = suffix[i] - solve(i + X, newM);

            best = Math.max(best, current);
        }

        return dp[i][M] = best;
    }
}