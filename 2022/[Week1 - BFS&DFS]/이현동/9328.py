import sys
from collections import deque
input = sys.stdin.readline


def checkRange(rx, ry):
    if 0 <= rx < H+2 and 0 <= ry < W+2:
        return True
    return False


def bfs():
    q = deque([])  # location
    q.append([0, 0])
    visited = [[False] * (W+2) for _ in range(H+2)]  # 방문 표시, 패딩 사용
    doorloc = [deque([]) for _ in range(26)]  # 문마다 좌표 위치
    visited[0][0] = True
    ans = 0

    while q:
        x, y = q.popleft()
        for i in range(4):
            rx, ry = x + dx[i], y + dy[i]
            if checkRange(rx, ry):
                if graph[rx][ry] == '*':
                    continue
                if not visited[rx][ry]:
                    visited[rx][ry] = True
                    if graph[rx][ry] == '$':
                        ans += 1
                    elif graph[rx][ry].isalpha() and graph[rx][ry].isupper():
                        tmp = ord(graph[rx][ry].lower()) - ord('a')
                        if not alkey[tmp]:
                            doorloc[tmp].append([rx, ry])
                            continue
                    elif graph[rx][ry].isalpha() and graph[rx][ry].islower(): # 열쇠 획득 시
                        tmp = ord(graph[rx][ry]) - ord('a')
                        alkey[tmp] = True
                        while doorloc[tmp]:
                            kx, ky = doorloc[tmp].popleft()
                            q.append([kx, ky])
                    q.append([rx, ry])
    return ans


T = int(input())
dx = [0, 0, -1, 1]
dy = [-1, 1, 0, 0]

res = []
for _ in range(T):
    H, W = map(int, input().split())  # 높이, 너비
    graph = [list('.'*(W+2))]
    for _ in range(H):
        graph.append(list('.' + input().rstrip() + '.'))  # 그래프
    graph.append(list('.'*(W+2)))
    keys = input().strip()  # 키 값들
    alkey = [False]*26
    if keys == '0':
        keys = ''
    for k in keys:
        alkey[ord(k)-ord('a')] = True

    res.append(bfs())

for i in res:
    print(i)
# 1
# 5 11
# ...........
# .....ABCDEF
# .....******
# *****aB$C..
# ***********
# c

# 1
# 5 11
# .....******
# *****$Z$DEF
# ***********
# *****ad$C..
# *****fe****
# c

# 15 15
# **$*.**********
# ****.*******$**
# ****B.$****b.**
# $*****c*****.**
# *C$.*****fD..**
# *$*xd******.**$
# $.C********A.**
# **h********.AA.
# ***************
# ***.i**********
# ***.***.K$** ***
# *k.$$I.$*******
# ******.$..j***$
# *******D*******
# ****$**F*******
# za
