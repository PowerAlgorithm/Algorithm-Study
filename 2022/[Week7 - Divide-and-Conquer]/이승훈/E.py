n, r, c = map(int, input().split())
ans = 0


def divide(sx, sy, ex, ey):
    global ans

    mx = (sx + ex) // 2
    my = (sy + ey) // 2

    val = (ex - mx) ** 2

    if sx == ex:
        return

    if sx <= c <= mx and sy <= r <= my:
        divide(sx, sy, mx, my)

    if mx+1 <= c <= ex and sy <= r <= my:
        divide(mx+1, sy, ex, my)
        ans += (val)

    if sx <= c <= mx and my+1 <= r <= ey:
        divide(sx, my+1, mx, ey)
        ans += (val * 2)

    if mx+1 <= c <= ex and my+1 <= r <= ey:
        divide(mx+1, my+1, ex, ey)
        ans += (val * 3)


divide(0, 0, 2**n - 1, 2**n - 1)
print(ans)
