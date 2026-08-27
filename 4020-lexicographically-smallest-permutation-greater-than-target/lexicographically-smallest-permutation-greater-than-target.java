class Solution {

    public String lexGreaterPermutation(String s, String target) {

        int n = s.length();
        int[] freq = new int[26];

        // Count characters of s
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        char[] ans = new char[n];

        // Position up to which we can match target
        int last = -1;

        for (int i = 0; i < n; i++) {

            int t = target.charAt(i) - 'a';

            // Can we make the answer greater at this position?
            for (int c = t + 1; c < 26; c++) {
                if (freq[c] > 0) {
                    last = i;
                    break;
                }
            }

            // Match target character if possible
            if (freq[t] == 0) {
                break;
            }

            ans[i] = target.charAt(i);
            freq[t]--;
        }

        // No position where we can make the string greater
        if (last == -1) {
            return "";
        }

        // Recreate frequency array
        freq = new int[26];

        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        // Keep the prefix equal to target
        for (int i = 0; i < last; i++) {
            freq[ans[i] - 'a']--;
        }

        int targetChar = target.charAt(last) - 'a';

        // Choose the smallest character greater than target[last]
        for (int c = targetChar + 1; c < 26; c++) {

            if (freq[c] > 0) {

                ans[last] = (char) ('a' + c);
                freq[c]--;

                // Fill remaining positions in sorted order
                int pos = last + 1;

                for (int j = 0; j < 26; j++) {
                    while (freq[j] > 0) {
                        ans[pos++] = (char) ('a' + j);
                        freq[j]--;
                    }
                }

                return new String(ans);
            }
        }

        return "";
    }
}