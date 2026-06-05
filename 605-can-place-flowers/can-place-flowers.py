class Solution(object):
    def canPlaceFlowers(self, flowerbed, n):
        i = 0
        count = 0
        m = len(flowerbed)

        while i < m:
            if flowerbed[i] == 0:
                left = (i == 0 or flowerbed[i - 1] == 0)
                right = (i == m - 1 or flowerbed[i + 1] == 0)

                if left and right:
                    flowerbed[i] = 1
                    count += 1
                    if count >= n:
                        return True
                    i += 1
            i += 1

        return count >= n
        