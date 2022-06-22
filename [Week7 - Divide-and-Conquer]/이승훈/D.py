n = int(input())


board = [['*' for _ in range(n)] for _ in range(n)]


def divide(sx, sy, ex, ey):
    if (ex - sx) <= 1:
        return

    len_tmp = (ex - sx + 1)//3

    mx0 = sx + len_tmp  # 좌측 mx
    my0 = sy + len_tmp

    mx1 = mx0 + len_tmp  # 우측 mx
    my1 = my0 + len_tmp

    divide(sx, sy, mx0-1, my0-1)
    divide(mx0, sy, mx1-1, my0-1)
    divide(mx1, sy, ex, my0-1)

    divide(sx, my0, mx0-1, my1-1)
    divide(mx0, my0, mx1-1, my1-1)
    divide(mx1, my0, ex, my0-1)

    divide(sx, my1, mx0-1, ey)
    divide(mx0, my1, mx1-1, ey)
    divide(mx1, my1, ex, ey)

    for i in range(my0, my1):
        for j in range(mx0, mx1):
            board[i][j] = ' '


divide(0, 0, n-1, n-1)

for b in board:
    for val in b:
        print(val, end='')
    print()
