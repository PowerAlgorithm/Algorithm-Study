import sys
import copy
input = sys.stdin.readline

n, m, t = map(int, input().split())
arr = [list(map(int, input().split())) for _ in range(n)]
test = [list(map(int, input().split())) for _ in range(t)]


def rotate(arr, i, d, k, m):
    if k % m == 0:
        return arr[i]
    if d == 0:
        return arr[i][m-k:] + arr[i][:m-k]
    else:
        return arr[i][k:] + arr[i][:k]

def findAdj(arr, n, m):
    tmp = copy.deepcopy(arr)
    flag = False
    # 행 검사
    for i in range(n):
        for j in range(1, m):
            if m-1 == j and tmp[i][j] == tmp[i][0]:
                arr[i][j] = arr[i][0] = 0
                if tmp[i][j] != 0 and tmp[i][0]!=0:
                    flag = True
            if tmp[i][j-1] == tmp[i][j]:
                arr[i][j] = arr[i][j-1] = 0
                if tmp[i][j] != 0 and tmp[i][j-1] != 0:
                    flag = True
                
    
    # 열 검사
    for j in range(m):
        for i in range(1, n):    
            if tmp[i-1][j] == tmp[i][j]:
                arr[i-1][j] = arr[i][j] = 0
                if tmp[i-1][j] != 0 and tmp[i][j] != 0:
                    flag = True
    
    return flag

def noAdj(arr, n, m):
    tot, cnt = 0, 0
    for i in range(n):
        for j in range(m):
            if arr[i][j] != 0:
                cnt += 1
                tot += arr[i][j]
    if cnt == 0:
        return True
    avg = tot / cnt
    # print(avg, tot, cnt)
    for i in range(n):
        for j in range(m):
            if arr[i][j] != 0:
                if arr[i][j] > avg:
                    arr[i][j] -= 1
                elif arr[i][j] < avg:
                    arr[i][j] += 1
    return False

for x, d, k in test:
    for i in range(n):
        if (i+1) % x == 0:
            arr[i] = rotate(arr, i, d, k, m)

    res = findAdj(arr, n, m)
    # print("res :", res)
    if not res:
        if noAdj(arr, n, m):
            break
    
    # for i in range(n):
    #     print(*arr[i])
    # print('---------------')

ans = 0
for i in range(n):
    ans += sum(arr[i])
print(ans)