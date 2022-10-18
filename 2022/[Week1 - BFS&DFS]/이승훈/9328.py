import sys
from collections import deque


input = sys.stdin.readline

t = int(input())
h, w = 0, 0
longH, longW = 0, 0
dy = [0, 1, 0, -1]
dx = [1, 0, -1, 0]
board = []
visited = []
key = []


# def findEntry():
#     entry = []
#     for i in range(h):
#         if board[i][0] in ['.', '$']:
#             entry.append([i, 0])
#         if board[i][w-1] in ['.', '$']:
#             entry.append([i, w-1])
#     for j in range(w):
#         if board[0][j] in ['.', '$']:
#             entry.append([0, j])
#         if board[h-1][j] in ['.', '$']:
#             entry.append([h-1, j])
#     return entry


def removeDoor():
    for i in range(longH):
        for j in range(longW):
            if board[i][j].lower() in key:
                board[i][j] = '.'


def bfs(deq):
    keyCnt = 0
    docCnt = 0

    while deq:
        curY, curX = deq.popleft()

        for i in range(4):
            ny, nx = curY+dy[i], curX+dx[i]
            if 0 <= ny < longH and 0 <= nx < longW and not visited[ny][nx]:
                if board[ny][nx] == '$':
                    board[ny][nx] = '.'
                    docCnt += 1
                elif board[ny][nx].islower():
                    key.add(board[ny][nx])
                    board[ny][nx] = '.'
                    keyCnt += 1
                if board[ny][nx] == '.':
                    deq.append([ny, nx])
                    visited[ny][nx] = True
    return docCnt, keyCnt


ansLs = []
for _ in range(t):
    h, w = map(int, (input().split()))
    longH, longW = h+2, w+2
    board = []
    board.append(['.']*(longW))
    for i in range(h):
        board.append(['.'] + list(input().strip()) + ['.'])
    board.append(['.']*(longW))

    key = set(input().strip())
    ans = 0
    while True:
        removeDoor()
        visited = [[False]*(longW) for _ in range(longH)]
        visited[0][0] = True
        docCnt, keyCnt = bfs(deque([[0, 0]]))
        ans += docCnt
        if docCnt+keyCnt == 0:
            ansLs.append(ans)
            break
for a in ansLs:
    print(a)
