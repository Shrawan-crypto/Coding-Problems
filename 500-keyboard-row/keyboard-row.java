class Solution {
    public String[] findWords(String[] words) {
        Set<Character> row1 = new HashSet<>();
        Set<Character> row2 = new HashSet<>();
        Set<Character> row3 = new HashSet<>();

        for (char c : "qwertyuiop".toCharArray()) row1.add(c);
        for (char c : "asdfghjkl".toCharArray()) row2.add(c);
        for (char c : "zxcvbnm".toCharArray()) row3.add(c);

        List<String> res = new ArrayList<>();

        for (String w : words) {
            String lower = w.toLowerCase();
            if (canType(lower, row1) || canType(lower, row2) || canType(lower, row3)) {
                res.add(w);
            }
        }

        return res.toArray(new String[0]);
    }

    private boolean canType(String word, Set<Character> row) {
        for (char c : word.toCharArray()) {
            if (!row.contains(c)) return false;
        }
        return true;
        
    }
}