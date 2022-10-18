import sys
import math
# start, end의 범위는 더해지는 범위


def init(node, start, end):
    if start == end:
        tree[node] = arr[start]
        return tree[node]
    mid = (start + end) // 2
    tree[node] = min(init(node*2, start, mid), init(node*2+1, mid+1, end))
    return tree[node]


def update(node, start, end, idx, val):
    if idx < start or end < idx:
        return float('inf')
    if start == end:
        tree[node] = val
        return tree[node]

    mid = (start + end) // 2
    tree[node] = min(update(node*2, start, mid, idx, val),
                     update(node*2+1, mid+1, end, idx, val))
    return tree[node]


def query(node, start, end, left, right):
    if left > end or start > right:
        return float('inf')
    if left <= start and end <= right:
        return tree[node]

    mid = (start + end) // 2
    return min(query(node*2, start, mid, left, right),
               query(node*2+1, mid+1, end, left, right))


N, M = map(int, sys.stdin.readline().split())
arr = [int(sys.stdin.readline()) for _ in range(N)]
tree = [0]*(1 << math.ceil(math.log2(N))+1)

init(1, 0, N-1)
for _ in range(M):
    a, b = map(int, sys.stdin.readline().split())
    a -= 1
    b -= 1
    print(query(1, 0, N-1, a, b))
