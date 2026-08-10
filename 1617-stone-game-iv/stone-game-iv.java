class Solution {
    public boolean winnerSquareGame(int n) {
        boolean[] dp = new boolean[n + 1];

        // dp[0] = false
        // No stones -> current player loses

        for (int i = 1; i <= n; i++) {

            for (int j = 1; j * j <= i; j++) {

                // If opponent reaches a losing state,
                // current player wins.
                if (!dp[i - j * j]) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[n];
    }
}