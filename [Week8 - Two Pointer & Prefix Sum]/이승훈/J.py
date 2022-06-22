import sys

input = sys.stdin.readline
n = int(input())
balls = [list(map(int, input().split())) + [i] for i in range(n)]  # 색, 크기, 위치

balls.sort(key=lambda x: x[1])

ans = [0 for _ in range(n)]
total_size = 0
color_size = [0 for _ in range(200001)]
j = 0
for i in range(n):
    c, s, p = balls[i]
    while balls[j][1] < s:
        total_size += balls[j][1]
        color_size[balls[j][0]] += balls[j][1]
        j += 1
    ans[p] = total_size - color_size[c]

for a in ans:
    print(a)
