class Solution {

    class Node {
        int len;
        int pLen;
        int sLen;
        int longest;

        char leftChar;
        char rightChar;

        Node() {
        }
    }

    Node[] tree;
    char[] str;

    private Node merge(Node a, Node b) {

        if (a == null)
            return b;

        if (b == null)
            return a;

        Node res = new Node();

        res.len = a.len + b.len;

        res.leftChar = a.leftChar;
        res.rightChar = b.rightChar;

        res.pLen = a.pLen;

        if (a.pLen == a.len && a.rightChar == b.leftChar) {
            res.pLen = a.len + b.pLen;
        }

        res.sLen = b.sLen;

        if (b.sLen == b.len && a.rightChar == b.leftChar) {
            res.sLen = b.len + a.sLen;
        }

        int cross = 0;

        if (a.rightChar == b.leftChar) {
            cross = a.sLen + b.pLen;
        }

        res.longest = Math.max(
                Math.max(a.longest, b.longest),
                cross);

        return res;
    }

    private void build(int idx, int left, int right) {

        if (left == right) {

            Node node = new Node();

            node.len = 1;
            node.pLen = 1;
            node.sLen = 1;
            node.longest = 1;

            node.leftChar = str[left];
            node.rightChar = str[left];

            tree[idx] = node;

            return;
        }

        int mid = (left + right) / 2;

        build(2 * idx, left, mid);
        build(2 * idx + 1, mid + 1, right);

        tree[idx] = merge(
                tree[2 * idx],
                tree[2 * idx + 1]);
    }

    private void update(
            int idx,
            int left,
            int right,
            int pos,
            char ch) {

        if (left == right) {

            str[pos] = ch;

            Node node = new Node();

            node.len = 1;
            node.pLen = 1;
            node.sLen = 1;
            node.longest = 1;

            node.leftChar = ch;
            node.rightChar = ch;

            tree[idx] = node;

            return;
        }

        int mid = (left + right) / 2;

        if (pos <= mid) {
            update(2 * idx, left, mid, pos, ch);
        } else {
            update(2 * idx + 1, mid + 1, right, pos, ch);
        }

        tree[idx] = merge(
                tree[2 * idx],
                tree[2 * idx + 1]);
    }

    public int[] longestRepeating(
            String s,
            String queryCharacters,
            int[] queryIndices) {

        int n = s.length();

        str = s.toCharArray();

        tree = new Node[4 * n];

        build(1, 0, n - 1);

        int k = queryIndices.length;

        int[] ans = new int[k];

        for (int i = 0; i < k; i++) {

            update(
                    1,
                    0,
                    n - 1,
                    queryIndices[i],
                    queryCharacters.charAt(i));

            ans[i] = tree[1].longest;
        }

        return ans;
    }
}