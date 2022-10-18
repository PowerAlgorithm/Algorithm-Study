"""
키워드
- 중량제한을 초과하는 양의 물품이 다리를 지나게 되면 다리가 무너지게 된다.


조건
- 경로는 항상 존재하는 데이터만 입력으로 주어짐

풀이
- 최대 중량으로 출발지와 목적지를 이어야된다.
- 최대 신장 트리를 구하면 해결


"""
import sys
from collections import *
sys.setrecursionlimit(10**6)


input = sys.stdin.readline

n, m = map(int, input().split())
# graph = [[] for _ in range(n+1)]
graph = []
for _ in range(m):
    a, b, c = map(int, input().split())
    graph.append([c, a, b])
start, target = map(int, input().split())
parent = [i for i in range(n+1)]


def union(a, b):
    aRoot = find(a)
    bRoot = find(b)

    if aRoot < bRoot:
        parent[bRoot] = aRoot
    else:
        parent[aRoot] = bRoot


def find(a):
    if a == parent[a]:
        return a
    parent[a] = find(parent[a])
    return parent[a]


graph.sort(reverse=True)
for g in graph:
    c, a, b = g
    union(a, b)
    if find(start) == find(target):
        print(c)
        break
# print(parent)
