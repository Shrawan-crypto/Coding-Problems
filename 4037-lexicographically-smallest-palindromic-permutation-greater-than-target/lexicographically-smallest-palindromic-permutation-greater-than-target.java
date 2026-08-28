class Solution {

    public String lexPalindromicPermutation(String s, String target) {

        int n = s.length();

        // Count characters
        int[] freq = new int[26];

        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }

        // Check if palindrome is possible
        int odd = 0;
        int middleChar = -1;

        for (int i = 0; i < 26; i++) {
            if (freq[i] % 2 == 1) {
                odd++;
                middleChar = i;
            }
        }

        if (odd > 1) {
            return "";
        }

        // Create frequency of first half
        int[] halfFreq = new int[26];

        for (int i = 0; i < 26; i++) {
            halfFreq[i] = freq[i] / 2;
        }

        int halfLength = n / 2;

        String targetHalf = target.substring(0, halfLength);

        // Find smallest half >= targetHalf
        String half = smallestGreaterOrEqual(
                halfFreq,
                targetHalf
        );

        if (half == null) {
            return "";
        }

        // Build palindrome
        String answer = buildPalindrome(
                half,
                middleChar
        );

        // If answer is strictly greater
        if (answer.compareTo(target) > 0) {
            return answer;
        }

        // Otherwise take next permutation
        String nextHalf = nextPermutation(half);

        if (nextHalf == null) {
            return "";
        }

        return buildPalindrome(nextHalf, middleChar);
    }


    private String smallestGreaterOrEqual(
            int[] originalFreq,
            String target
    ) {

        int m = target.length();

        int[][] prefixCount = new int[m + 1][26];

        for (int i = 0; i < m; i++) {

            for (int c = 0; c < 26; c++) {
                prefixCount[i + 1][c] =
                        prefixCount[i][c];
            }

            prefixCount[i + 1][target.charAt(i) - 'a']++;
        }

        int matched = 0;

        int[] remaining = originalFreq.clone();

        while (matched < m) {

            int c = target.charAt(matched) - 'a';

            if (remaining[c] == 0) {
                break;
            }

            remaining[c]--;
            matched++;
        }

        // Target half itself is possible
        if (matched == m) {
            return target;
        }

        // Try changing a position from right to left
        for (int pos = matched; pos >= 0; pos--) {

            int[] available = new int[26];

            for (int c = 0; c < 26; c++) {
                available[c] =
                        originalFreq[c]
                        - prefixCount[pos][c];
            }

            int targetChar =
                    target.charAt(pos) - 'a';

            // Choose smallest character greater
            for (int c = targetChar + 1;
                 c < 26;
                 c++) {

                if (available[c] > 0) {

                    StringBuilder result =
                            new StringBuilder();

                    // Same prefix
                    result.append(
                            target,
                            0,
                            pos
                    );

                    // Bigger character
                    result.append(
                            (char) ('a' + c)
                    );

                    available[c]--;

                    // Fill rest in sorted order
                    for (int x = 0; x < 26; x++) {

                        while (available[x] > 0) {

                            result.append(
                                    (char) ('a' + x)
                            );

                            available[x]--;
                        }
                    }

                    return result.toString();
                }
            }
        }

        return null;
    }


    private String buildPalindrome(
            String half,
            int middleChar
    ) {

        StringBuilder result =
                new StringBuilder();

        // First half
        result.append(half);

        // Middle character
        if (middleChar != -1) {
            result.append(
                    (char) ('a' + middleChar)
            );
        }

        // Reverse half
        for (int i = half.length() - 1;
             i >= 0;
             i--) {

            result.append(half.charAt(i));
        }

        return result.toString();
    }


    private String nextPermutation(String s) {

        char[] arr = s.toCharArray();

        int i = arr.length - 2;

        // Find decreasing position
        while (i >= 0 &&
               arr[i] >= arr[i + 1]) {

            i--;
        }

        // Already largest
        if (i < 0) {
            return null;
        }

        // Find next larger character
        int j = arr.length - 1;

        while (arr[j] <= arr[i]) {
            j--;
        }

        // Swap
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;

        // Reverse suffix
        int left = i + 1;
        int right = arr.length - 1;

        while (left < right) {

            temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;

            left++;
            right--;
        }

        return new String(arr);
    }
}