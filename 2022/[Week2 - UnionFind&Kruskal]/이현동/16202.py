import sys
from collections import deque
input = sys.stdin.readline
"""
제목부터 MST - > 크루스칼
일반적인 MST 문제이지만, 
턴이라는 것에서 K번 MST 구하는 연산 필요.
그래프에서 간선을 하나씩 제거!
MST 연산 후 간선에서 MST를 구할 때 사용한 최소 비용 간선을
pop시키고 계산하는 것이 관건이다.
하지만, 생각을 해보면 edge를 그리디하게 가져가기 때문에(정렬된 간선)
앞에서 부터 포함을 시켰기 때문에 그냥 pop(0)을 하면 됨.
"""

def find_parent(parent, x):
    if parent[x] != x:
        return find_parent(parent, parent[x])
    return parent[x]

def union(parent, x, y):
    a = find_parent(parent, x)
    b = find_parent(parent, y)

    if a < b:
        for i in range(1, N+1):
            if parent[i] == b:
                parent[i] = a
    else:
        for i in range(1, N+1):
            if parent[i] == a:
                parent[i] = b

def checkMST(parent):
    if len(set(parent[1:])) >= 2:
        return False
    return True

N, M, K = map(int, input().split())
ans = []
edges = []
flag = True # mst가 실패한 뒤로 모든 남은 턴은 0
cost = 0

for i in range(M):
    a, b = map(int, input().split())
    edges.append([a, b, i+1])

tedge = []

for _ in range(K):
    cost = 0
    parent = [i for i in range(N+1)]
    
    for edge in edges:
        a, b, c = edge
        if find_parent(parent, a) != find_parent(parent, b):
            cost += c
            union(parent, a, b)
    
    if checkMST(parent):
        ans.append(cost)
        edges.pop(0)
    else:
        break

l = len(ans)
for _ in range(K-l):
    ans.append(0)

print(*ans)