"""
키워드
- 최소 스패닝 트리


조건
- O(간선) 

풀이
- 최소 신장 트리


"""
import sys
from collections import *
sys.setrecursionlimit(10**6)


input = sys.stdin.readline

n, m, k = map(int, input().split())
edges = [[i+1] + list(map(int, input().split())) for i in range(m)]
parent = []


def find(a):
    if a == parent[a]:
        return a
    parent[a] = find(parent[a])
    return parent[a]


def union(a, b):
    aRoot = find(a)
    bRoot = find(b)
    if aRoot < bRoot:
        parent[bRoot] = aRoot
    else:
        parent[aRoot] = bRoot


for _ in range(k):
    parent = [i for i in range(0, n+1)]
    sumVal = 0
    cnt = 0

    for e in edges:
        if find(e[1]) != find(e[2]):
            union(e[1], e[2])
            sumVal += e[0]
            cnt += 1
        if cnt == n-1:
            print(sumVal, end=' ')
            edges.pop(0)
            break
    else:
        print(0, end=' ')
