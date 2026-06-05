"""
# Definition for a Node.
class Node(object):
    def __init__(self, val=None, children=None):
        self.val = val
        self.children = children
"""

class Solution(object):
    def preorder(self, root):
        if not root:
            return []

        stack = [root]
        res = []

        while stack:
            node = stack.pop()
            res.append(node.val)

            # reverse because stack is LIFO
            for child in reversed(node.children):
                stack.append(child)

        return res
        