import sys
input = sys.stdin.readline



def find_parent(parent, x):
    if parent[x] != x:
        return find_parent(parent, parent[x])
    return parent[x]

def union_find(parent, a, b):
    a = find_parent(parent, a)
    b = find_parent(parent, b)
    if a < b:
        parent[b] = a
    else:
        parent[a] = b

ans = []



while True:    
    m, n = map(int, input().split())
    if m == 0 and n == 0:
        break
    parent = [0]*(m+1)
    for i in range(m+1):
        parent[i] = i
    
    tot, cost = 0, 0
    edges = []
    
    for i in range(n):
        edg = list(map(int, input().split()))
        tot += edg[2]
        edges.append([edg[0], edg[1], edg[2]])

    edges.sort(key = lambda x : x[2])
    cnt = 0

    for edge in edges:
        x, y, z = edge
        if find_parent(parent, x) != find_parent(parent, y):
            cost += z
            union_find(parent, x, y)            
    ans.append(tot-cost)

for i in ans:
    print(i)
