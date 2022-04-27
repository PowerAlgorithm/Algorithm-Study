import sys
sys.setrecursionlimit(10**6)
input = sys.stdin.readline

"""
:일정한 지점 : vertex
:두 지점 연결하는 연결구간: edges
같은 연결 구간을 두번 이상 지날 경우 민원

지점은 두 번 이상 지나도 됨 + 연결 구간 한 번만 지나라 

하나의 집합 가능하면 yes else no
"""

ans = "YES"

def dfs(v, path, depth):
    cnt = 0
    if E == depth:
        print("YES")
        exit()
    for i in graph[v]:
        if (i, v) in path or (v, i) in path:
            cnt += 1
            if cnt == len(graph[v]):
                global ans
                ans = "NO"
            continue
        path.add((v, i))
        path.add((i, v))
        dfs(i, path, depth+1)

V, E = map(int, input().split())
graph = [[] for _ in range(V+1)]

for _ in range(E):
    a, b = map(int, input().split())
    graph[a].append(b)
    graph[b].append(a)

for i in range(1, V+1):
    dfs(i, set(), 0)

print(ans)