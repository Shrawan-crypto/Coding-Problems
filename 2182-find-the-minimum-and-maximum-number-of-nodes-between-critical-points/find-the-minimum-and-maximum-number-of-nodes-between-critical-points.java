class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {

        int first = -1;      // first critical point
        int prev = -1;       // previous critical point
        int minDistance = Integer.MAX_VALUE;

        ListNode prevNode = head;
        ListNode curr = head.next;

        int index = 1;

        while (curr != null && curr.next != null) {

            int previousValue = prevNode.val;
            int currentValue = curr.val;
            int nextValue = curr.next.val;

            // Check if current node is a critical point
            boolean isCritical =
                    (currentValue > previousValue && currentValue > nextValue) ||
                    (currentValue < previousValue && currentValue < nextValue);

            if (isCritical) {

                // First critical point
                if (first == -1) {
                    first = index;
                }

                // We already have a previous critical point
                if (prev != -1) {
                    minDistance = Math.min(minDistance, index - prev);
                }

                prev = index;
            }

            prevNode = curr;
            curr = curr.next;
            index++;
        }

        // Fewer than two critical points
        if (first == -1 || first == prev) {
            return new int[]{-1, -1};
        }

        // Maximum distance = last critical point - first critical point
        int maxDistance = prev - first;

        return new int[]{minDistance, maxDistance};
    }
}