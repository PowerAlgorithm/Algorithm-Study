import sys
input = sys.stdin.readline

"""
오르막 :점선, 피로도 누적 :: 0
내리막 :실선 :: 1
입구 : 0
오르막 k번 피로도 k^2
피로도 계산 -> 최초 조사된 길을 기준
내리막 -> 오르막 고려 x
E = |V-1| -> MST
피로도 차이 계산

"""
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
cnt = 0
ans = []

for _ in range(M+1):
    a, b, f = map(int, input().split())
    edges.append([a, b, f])

edges.sort(key = lambda x:x[-1], reverse=True)

for edge in edges:
    a, b, flag = edge
    if find_parent(parent, a) != find_parent(parent, b):
        if flag == 0:
            cnt += 1
        union(parent, a, b)
ans.append(cnt**2)

cnt = 0
parent = [i for i in range(N+1)]
edges.sort(key = lambda x:x[-1])

for edge in edges:
    a, b, flag = edge
    if find_parent(parent, a) != find_parent(parent, b):
        if flag == 0:
            cnt += 1
        union(parent, a, b)

ans.append(cnt**2)

print(ans[-1]-ans[0])