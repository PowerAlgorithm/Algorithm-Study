import sys
sys.setrecursionlimit(10**6)
input = sys.stdin.readline

def find_parent(parent, x):
    if parent[x] == x:
        return x
    p = find_parent(parent, parent[x])
    parent[x] = p
    return parent[x]

def union(parent, a, b):
    a = find_parent(parent, a)
    b = find_parent(parent, b)
    if a < b:
        parent[b] = a
    else:
        parent[a] = b

G = int(input())
P = int(input())

parent = {i:i for i in range(G+1)}
port = [int(input()) for _ in range(P)]
ans = 0

for p in port:
    a = find_parent(parent, p)

    if a == 0:
        break
    union(parent, a, a-1)
    ans += 1

print(ans)