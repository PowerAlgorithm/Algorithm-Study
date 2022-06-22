n = int(input())
board = [list(map(int, input().split())) for _ in range(n)]


def divide(sx, sy, ex, ey):
    x_mid = (sx + ex) // 2
    y_mid = (sy + ey) // 2

    if sx == ex:
        return board[sy][sx]

    lu = divide(sx, sy, x_mid, y_mid)
    ld = divide(x_mid+1, sy, ex, y_mid)
    ru = divide(sx, y_mid+1, x_mid, ey)
    rd = divide(x_mid+1, y_mid+1, ex, ey)

    tmp = [lu, ld, ru, rd]
    tmp.sort()
    return tmp[-2]


print(divide(0, 0, n-1, n-1))
