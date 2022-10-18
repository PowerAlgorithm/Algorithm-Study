import sys
import math
# start, end의 범위는 더해지는 범위


def init(node, start, end):  # node : 노드의 위치 (tree list에서의 idx)
    if start == end:
        tree[node] = arr[start]
        return tree[node]

    mid = (start + end) // 2
    tree[node] = init(node*2, start, mid) + init(node*2+1, mid+1, end)
    return tree[node]


def update(node, start, end, idx, diff):  # idx : 리프 노드 위치 (arr idx)
    if idx < start or end < idx:
        return

    tree[node] += diff

    if start == end:
        return

    mid = (start+end)//2
    update(node*2, start, mid, idx, diff)
    update(node*2+1, mid+1, end, idx, diff)


# left, right가 원하는 범위 == 고정된 값
def query(node, start, end, left, right):
    if left > end or start > right:
        return 0

    if left <= start and end <= right:
        return tree[node]

    mid = (start+end)//2
    return query(node*2, start, mid, left, right) + \
        query(node*2+1, mid+1, end, left, right)


N, M, K = map(int, sys.stdin.readline().split())
arr = [int(sys.stdin.readline()) for _ in range(N)]
tree = [0]*(1 << math.ceil(math.log2(N))+1)

init(1, 0, N-1)
for _ in range(M+K):
    a, b, c = map(int, sys.stdin.readline().split())
    b -= 1
    if a == 1:
        diff = c - arr[b]
        arr[b] = c
        update(1, 0, N-1, b, diff)
    else:
        c -= 1
        print(query(1, 0, N-1, b, c))
