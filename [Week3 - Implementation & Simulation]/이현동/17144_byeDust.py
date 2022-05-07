import sys
from collections import deque
import math 
import copy
input = sys.stdin.readline

direction = [(1, 0), (-1, 0), (0, 1), (0, -1)]

r, c, t = map(int, input().split())

graph = [list(map(int, input().split())) for _ in range(r)]

def amountOfDust(graph):
    tot = 0
    for i in range(r):
        for j in range(c):
            tot += graph[i][j]
    return tot+2

def bfs(graph, dust):
    visited = [[False]*c for _ in range(r)]
    q = deque(dust)
    tmp = copy.deepcopy(graph)
    while q:
        x, y = q.popleft()
        amount, cnt = graph[x][y]//5, 0
    
        for rx, ry in direction:
            dx, dy = x + rx, y + ry
            if 0 <= dx < r and 0 <= dy < c:
                if graph[dx][dy] >= 0:
                    tmp[dx][dy] += amount
                    cnt += 1
        tmp[x][y] -= amount*cnt
    return tmp

def vacuumCleaner(graph):
    loc = []
    
    for j in range(r):
        if graph[j][0] == -1:
            loc.append([j, 1])
    
    x, y = loc.pop(0) # first vacuum clockwise
    # right
    tmp = 0
    for i in range(y, c):
        ttmp = graph[x][i]
        graph[x][i] = tmp
        tmp = ttmp
    # top
    for i in range(x-1, -1, -1):
        ttmp = graph[i][c-1]
        graph[i][c-1] = tmp
        tmp = ttmp
    # left
    for i in range(c-2, -1, -1):
        ttmp = graph[0][i]
        graph[0][i] = tmp
        tmp = ttmp
    # bottom
    for i in range(1, x):
        ttmp = graph[i][0]
        graph[i][0] = tmp
        tmp = ttmp
    
    x, y = loc.pop(0) # first vacuum counter clockwise
    # right
    tmp = 0
    for i in range(y, c):
        ttmp = graph[x][i]
        graph[x][i] = tmp
        tmp = ttmp
    # bottom
    for i in range(x+1, r):
        ttmp = graph[i][c-1]
        graph[i][c-1] = tmp
        tmp = ttmp
    # left
    for i in range(c-2, -1, -1):
        ttmp = graph[r-1][i]
        graph[r-1][i] = tmp
        tmp = ttmp
    # # top
    for i in range(r-2, x, -1):
        ttmp = graph[i][0]
        graph[i][0] = tmp
        tmp = ttmp


while t:
    dust = []
    for i in range(r):
        for j in range(c):
            if 0 < graph[i][j]:
                dust.append([i, j])
    graph = bfs(graph, dust)
    t -= 1
    # print('--------------')
    # for i in range(r):
    #     print(*graph[i])

    vacuumCleaner(graph)
    # print('--------------')
    # for i in range(r):
    #     print(*graph[i])
        


print(amountOfDust(graph))