import java.util.*;

class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {

        Map<Integer, Set<Integer>> map = new HashMap<>();

        for (int[] seat : reservedSeats) {

            int row = seat[0];
            int col = seat[1];

            map.putIfAbsent(row, new HashSet<>());
            map.get(row).add(col);
        }

        long answer = (long) n * 2;

        for (int row : map.keySet()) {

            Set<Integer> seats = map.get(row);

            boolean left =
                    !seats.contains(2) &&
                    !seats.contains(3) &&
                    !seats.contains(4) &&
                    !seats.contains(5);

            boolean middle =
                    !seats.contains(4) &&
                    !seats.contains(5) &&
                    !seats.contains(6) &&
                    !seats.contains(7);

            boolean right =
                    !seats.contains(6) &&
                    !seats.contains(7) &&
                    !seats.contains(8) &&
                    !seats.contains(9);

            int families = 0;

            if (left && right) {
                families = 2;
            } else if (left || middle || right) {
                families = 1;
            }

            answer -= 2;
            answer += families;
        }

        return (int) answer;
    }
}