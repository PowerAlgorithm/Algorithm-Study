import math
import sys
input = sys.stdin.readline

def checkDistance(edge, x, y, R):
    return True if(((x-edge[0])**2 + (y-edge[1])**2)) <= (R+edge[-1])**2 else False



def find_parent(parent, x):
    if parent[x] != x:
        return find_parent(parent, parent[x])
    return parent[x]


def union(parent, a, b):
    a = find_parent(parent, a)
    b = find_parent(parent, b)
    # 원래 b를 부모로 가지고 있던 놈들도 바꿔줘야함.. 여기서 오래걸림
    for i in range(N):
        if parent[i] == b:
            parent[i] = a

res = []

T = int(input())
for _ in range(T):
    N = int(input())


    parent = [0]*(N)
    for i in range(N):
        parent[i] = i

    tot, cost = 0, 0
    edges = []
    base = []
    for i in range(N):
        edg = list(map(int, input().split()))
        edges.append([edg[0], edg[1], edg[2]])
    
    edges.sort()
    cnt = 1

    for i, edge in enumerate(edges):
        x, y, z = edge
        for j in range(N):
            if checkDistance(edges[j], x, y, z) and parent[i] != parent[j]:
                union(parent, i, j)
    parent.sort()
    tmp = parent[0]
    for i in range(1, N):
        if parent[i] != tmp:
            cnt += 1
            tmp = parent[i]
    
    res.append(cnt)

for i in res:
    print(i)

