import sys
import math
from copy import deepcopy
# start, end의 범위는 더해지는 범위


def init(node, start, end):
    if start == end:
        tree[node] = [arr[start], start]
        return tree[node]
    mid = (start + end) // 2
    i0 = init(node*2, start, mid)
    i1 = init(node*2+1, mid+1, end)

    tree[node] = deepcopy(min(i0, i1))
    return tree[node]


def update(node, start, end, idx, val):
    if idx < start or end < idx:
        return tree[node]
    if start == end:
        tree[node] = [val, idx]
        return tree[node]

    mid = (start + end) // 2
    u0 = update(node*2, start, mid, idx, val)
    u1 = update(node*2+1, mid+1, end, idx, val)
    tree[node] = deepcopy(min(u0, u1))
    return tree[node]


def query(node, start, end, left, right):
    if left > end or start > right:
        return [float('inf'), 0]
    if left <= start and end <= right:
        return tree[node]

    mid = (start + end) // 2
    q0 = query(node*2, start, mid, left, right)
    q1 = query(node*2+1, mid+1, end, left, right)
    return min(q0, q1)


# N, M = map(int, sys.stdin.readline().split())
# arr = [int(sys.stdin.readline()) for _ in range(N)]
N = int(input())
arr = list(map(int, input().split()))
M = int(input())
tree = [[0, 0]]*(1 << math.ceil(math.log2(N))+1)

init(1, 0, N-1)
for _ in range(M):
    a, b, c = map(int, sys.stdin.readline().split())
    b -= 1
    if a == 1:
        update(1, 0, N-1, b, c)
    else:
        c -= 1
        _, ans = query(1, 0, N-1, b, c)
        print(ans+1)
