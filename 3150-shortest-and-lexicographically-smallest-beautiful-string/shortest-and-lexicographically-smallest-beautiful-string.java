class Solution {
    public String shortestBeautifulSubstring(String s, int k) {

        int n = s.length();
        int[] pos = new int[n];
        int count = 0;

        // Store positions of all 1's
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') {
                pos[count++] = i;
            }
        }

        // Not enough 1's
        if (count < k) {
            return "";
        }

        String ans = "";

        // Check every group of k consecutive 1's
        for (int i = 0; i + k - 1 < count; i++) {

            int start = pos[i];
            int end = pos[i + k - 1];

            String current = s.substring(start, end + 1);

            // First substring or shorter substring
            if (ans.equals("") || current.length() < ans.length()) {
                ans = current;
            }
            // Same length -> lexicographically smaller
            else if (current.length() == ans.length()
                    && current.compareTo(ans) < 0) {
                ans = current;
            }
        }

        return ans;
    }
}