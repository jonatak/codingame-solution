import sys
import math

# Auto-generated code below aims at helping you parse
# the standard input according to the problem statement.

tree = {}
explored = set()

n = int(raw_input()) # the number of adjacency relations
for i in xrange(n):
    xi, yi = [int(j) for j in raw_input().split()]
    tree.setdefault(xi, set()).add(yi)
    tree.setdefault(yi, set()).add(xi)

treeGen = (k for k, v in tree.items() if len(v) == 1)

def get_next_node(leaf):
    res = set()
    for i in leaf:
        for e in tree[i]:
            if e not in explored:
                res.add(e)
                explored.add(e)
    return res

first = set([treeGen.next()])
leafs = get_next_node(first)
acc = 0

while (len(leafs)):
    acc += 1
    leafs = get_next_node(leafs)

final = acc / 2 + acc % 2
print final
