import sys
sys.setrecursionlimit(10**7)
"""
핵심 키워드
- 퍼레이드의 경로는 일정한 지점들과 두 지점을 연결하는 연결 구간으로 이뤄짐
    - 인접 리스트 구조 
- 모든 지점을 지나며 모든 연결 구간(모든 간선)들을 지나고 싶음
    - 기준이 v가 아닌 e임
    - dfs/bfs or 오일러 경로

조건
- 같은 구간 2번 이상 X
- 같은 지점은 2번 이상 O
- 같은 구간이 입력으로 주어지지 않음
- 임의 지점은 하나 이상의 연결이 되어있음
    - 그렇다고 해도 하나의 그룹으로 이뤄졌다는 말은 아님
    - 오일러 경로가 가능한지 따지기 전에 다 같은 그룹인지 확인 필요

풀이
dfs
- 한줄로 모든 v를 지나야한다.
- 시작 지점을 임의로 정한 뒤 dfs로 전체 순회가 가능하면 성공
    - visited는 정점이 아닌 간선을 기준으로 해야됨
- 시작지점 탐색 3000, dfs O(V+E) = O(6000). 즉, O(18000000)


- union find로 그룹을 만든다.
- 한줄이 되려면 가장자리만 넣어야하니 find()로 자신이 말단인지 확인

"""

input = sys.stdin.readline

v, e = map(int, input().split())
# edges = [list(map(int, input().split())) for _ in range(e)]
edges = [[] for _ in range(v+1)]
for _ in range(e):
    a, b = map(int, input().split())
    edges[a].append(b)
    edges[b].append(a)
visited = [False for _ in range(v+1)]
parent = [i for i in range(v+1)]


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


for cur in range(1, v+1):
    for next in edges[cur]:
        union(cur, next)

seperate = False
for vVal in range(1, v+1):
    if find(1) != find(vVal):
        seperate = True

oddCnt = 0
if seperate:
    print('NO')
else:
    for cur in range(1, v+1):
        if len(edges[cur]) % 2 == 1:
            oddCnt += 1
    if oddCnt == 2 or oddCnt == 0:
        print('YES')
    else:
        print('NO')
