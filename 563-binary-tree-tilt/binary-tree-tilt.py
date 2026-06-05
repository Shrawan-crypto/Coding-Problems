# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution(object):
    def findTilt(self, root):
        self.total = 0

        def subtree_sum(node):
            if not node:
                return 0

            left_sum = subtree_sum(node.left)
            right_sum = subtree_sum(node.right)

            self.total += abs(left_sum - right_sum)

            return left_sum + right_sum + node.val

        subtree_sum(root)
        return self.total
        