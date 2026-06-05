class Solution(object):
    def findRestaurant(self, list1, list2):
        index_map = {word: i for i, word in enumerate(list1)}
        res = []
        min_sum = float('inf')

        for j, word in enumerate(list2):
            if word in index_map:
                s = j + index_map[word]

                if s < min_sum:
                    min_sum = s
                    res = [word]
                elif s == min_sum:
                    res.append(word)

        return res
        