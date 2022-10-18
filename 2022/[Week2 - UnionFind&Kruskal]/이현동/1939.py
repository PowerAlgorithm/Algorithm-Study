import sys
input = sys.stdin.readline


def find_parent(parent, x):
    if parent[x] != x:
        return find_parent(parent, parent[x])
    return parent[x]


def union(parent, a, b):
    a = find_parent(parent, a)
    b = find_parent(parent, b)
    if a < b:
        parent[b] = a
    else:
        parent[a] = b

N, M = map(int, input().split())
edges = []
parent = [i for i in range(N+1)]

for _ in range(M):
    a, b, c = map(int, input().split())
    edges.append([a, b, c])


factory = list(map(int, input().split()))
edges.sort(key=lambda x:x[-1], reverse=True)
new_edge = []
ans = sys.maxsize

for edge in edges:
    a, b, c = edge
    if find_parent(parent, a) != find_parent(parent, b):
        union(parent, a, b)
        if find_parent(parent, parent[factory[0]]) == find_parent(parent, parent[factory[-1]]):
            ans = min(ans, c)
            break

print(ans)

# 3 3 
# 1 2 4
# 3 1 2
# 3 2 4
# 1 3
# 5 7 
# 1 2 2
# 1 3 1
# 3 2 7
# 2 4 1
# 3 4 6
# 2 5 5
# 4 5 3
# 1 5  