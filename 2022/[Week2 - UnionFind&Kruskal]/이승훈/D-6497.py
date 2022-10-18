import sys
"""
핵심 키워드
- 도시에 있는 모든 두 집 쌍에 대해, 불이 켜진 길만으로 서로 왕래.
- 절약할 수 있는 최대 액수 -> 최소 경로

키워드 분석
- 인접 리스트에서 mst를 이용 

조건
- 입력의 끝에서 첫 줄에 0이 2개 주어짐

"""

input = sys.stdin.readline

parent = []
m, n = 0, 0


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


while True:
    edges = []
    m, n = map(int, input().split())
    parent = [i for i in range(m)]
    sumVal = 0
    totalVal = 0

    if m == 0 and n == 0:
        break
    for _ in range(n):
        x, y, z = map(int, input().split())
        edges.append([z, x, y])

    edges.sort()
    for e in edges:
        z, x, y = e
        totalVal += z
        if find(x) != find(y):
            sumVal += z
            union(x, y)
    print(totalVal - sumVal)
