class Solution(object):
    def checkRecord(self, s):
        absents = 0
        late_streak = 0

        for ch in s:
            if ch == 'A':
                absents += 1
                late_streak = 0
            elif ch == 'L':
                late_streak += 1
            else:
                late_streak = 0

            if absents >= 2 or late_streak >= 3:
                return False

        return True
        