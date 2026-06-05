class Solution(object):
    def matrixReshape(self, mat, r, c):
        m, n = len(mat), len(mat[0])

        if m * n != r * c:
            return mat

        flat = []
        for row in mat:
            flat.extend(row)

        res = []
        for i in range(r):
            res.append(flat[i * c:(i + 1) * c])

        return res