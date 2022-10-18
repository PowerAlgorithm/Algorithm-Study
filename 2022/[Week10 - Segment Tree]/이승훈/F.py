import sys
import math
# start, end의 범위는 더해지는 범위


def init(node, start, end):
    if start == end:
        tree[node] = 0
        return tree[node]
    mid = (start + end) // 2
    tree[node] = init(node*2, start, mid) + init(node*2+1, mid+1, end)
    return tree[node]


def update(node, start, end, idx, val):
    if idx < start or end < idx:
        return tree[node]
    if start == end:
        tree[node] = val
        return tree[node]

    mid = (start + end) // 2
    tree[node] = update(node*2, start, mid, idx, val) + \
        update(node*2+1, mid+1, end, idx, val)
    return tree[node]


def query(node, start, end, left, right):
    if left > end or start > right:
        return 0
    if left <= start and end <= right:
        return tree[node]

    mid = (start + end) // 2
    return query(node*2, start, mid, left, right) + \
        query(node*2+1, mid+1, end, left, right)


# N, M = map(int, sys.stdin.readline().split())
# arr = [int(sys.stdin.readline()) for _ in range(N)]
N = int(input())
arr0 = list(map(int, input().split()))
arr1 = list(map(int, input().split()))
tree = [0]*(1 << math.ceil(math.log2(N))+1)
h = [0] * 1000001
init(1, 0, N-1)

for i, val in enumerate(arr0):
    h[val] = i

ans = 0
for i, val in enumerate(arr1):
    update(1, 0, N-1, h[val], 1)
    ans += query(1, 0, N-1, h[val]+1, N-1)
print(ans)
