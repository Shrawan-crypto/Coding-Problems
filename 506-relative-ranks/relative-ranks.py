class Solution(object):
    def findRelativeRanks(self, score):
        sorted_idx = sorted(range(len(score)), key=lambda i: score[i], reverse=True)

        res = [""] * len(score)
        medals = ["Gold Medal", "Silver Medal", "Bronze Medal"]

        for rank, i in enumerate(sorted_idx):
            if rank < 3:
                res[i] = medals[rank]
            else:
                res[i] = str(rank + 1)

        return res
        