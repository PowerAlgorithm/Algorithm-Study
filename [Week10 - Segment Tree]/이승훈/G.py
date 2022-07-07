import sys
import math
input = sys.stdin.readline
# start, end의 범위는 더해지는 범위


def init(node, start, end):
    if start == end:
        tree[node] = 0
        return tree[node]
    mid = (start + end) // 2
    tree[node] = init(node*2, start, mid) + init(node*2+1, mid+1, end)
    return tree[node]


def update(node, start, end, idx, diff):
    # if idx < start or end < idx:
    #     return tree[node]
    # if start == end:
    #     tree[node] = val
    #     return tree[node]

    # mid = (start + end) // 2
    # tree[node] = update(node*2, start, mid, idx, val) + \
    #     update(node*2+1, mid+1, end, idx, val)
    # return tree[node]
    if idx < start or end < idx:
        return

    if start == end:
        tree[node] += diff
        return

    tree[node] += diff
    mid = (start + end) // 2
    update(node*2, start, mid, idx, diff)
    update(node*2+1, mid+1, end, idx, diff)


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
def findNum(val):
    l, r = 0, N-1
    while l <= r:
        mid = (l + r) // 2
        cumVal = query(1, 0, N-1, 0, mid)

        if cumVal < val:
            l = mid + 1
        else:
            r = mid - 1
    return l


N = 1000001
tree = [0]*(1 << math.ceil(math.log2(N))+1)
init(1, 0, N-1)
n = int(input())
for i in range(n):
    cal = list(map(int, input().split()))
    if cal[0] == 1:
        a, b = cal
        idx = findNum(b)
        print(idx)
        update(1, 0, N-1, idx, -1)
    else:
        a, b, c = cal
        update(1, 0, N-1, b, c)
