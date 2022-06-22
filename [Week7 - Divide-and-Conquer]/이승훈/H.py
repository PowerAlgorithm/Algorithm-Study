n, b = map(int, input().split())
board = [list(map(int, input().split())) for _ in range(n)]


def squared(m):
    return multifly(m, m)


def multifly(a, b):
    tmp = [[0 for _ in range(n)] for _ in range(n)]
    for i in range(n):
        for j in range(n):
            val = 0
            for k in range(n):
                val += a[i][k] * b[k][j]
            tmp[i][j] = val % 1000
    return tmp


def divide(cnt):
    if cnt == 1:
        return board
    if cnt % 2 == 0:
        val = divide(cnt//2)
        return squared(val)
    else:
        val = divide(cnt//2)
        return multifly(squared(val), board)


for val in divide(b):
    for v in val:
        print(v % 1000, end=' ')
    print()
