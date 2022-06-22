n, h = map(int, input().split())
ls = []
up = [0 for _ in range(h)]
down = [0 for _ in range(h)]

for i in range(n):
    val = int(input())
    if i % 2 == 0:
        down[val] += 1
    else:
        up[val] += 1
for i in range(h):
    up[i] += up[i-1]
    down[i] += down[i-1]

up_num = up[-1]
down_num = down[-1]
ans = float('inf')
ans_num = 0
for i in range(h):
    a = down_num - down[i]
    b = up_num - up[h - 1 - i]
    val = a + b
    if val < ans:
        ans = val
        ans_num = 1
    elif val == ans:
        ans_num += 1

print(ans, ans_num)
