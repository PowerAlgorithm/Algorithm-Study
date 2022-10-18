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
        
    

N = int(input())
M = int(input())
parent = [i for i in range(N+1)]
graph = [[1000] for _ in range(N+1)]
maxNode = [0 for _ in range(N+1)]

for i in range(N+1):
    graph[i][i] = 0

for _ in range(M):
    a, b = map(int, input().split())
    union(parent, a, b)
    graph[a][b] = 1
    graph[b][a] = 1


for i in range(1, N+1):
    for j in range(1, N+1):
        for k in range(1, N+1):
            if graph[i][j] > graph[i][k] + graph[k][j]:
                graph[i][j] = graph[i][k] + graph[k][j]

for i in range(1, N+1):
    p = find_parent(i)
    maxV = 0
    for j in range(1, N+1):
        if graph[i][j] == 1000:
            continue
        if maxV < graph[i][j]:
            maxV = graph[i][j]
    maxNode[i] = maxV

print(maxNode)
# ans = sorted(set(parent[1:]))
# print(len(ans))
# for i in ans:
#     print(i)

# 9
# 7
# 1 2
# 3 4
# 3 6
# 4 5
# 5 6
# 5 7
# 8 9

# 9
# 8
# 1 2
# 2 3
# 3 4
# 4 5
# 4 6
# 4 7
# 4 8
# 4 9
