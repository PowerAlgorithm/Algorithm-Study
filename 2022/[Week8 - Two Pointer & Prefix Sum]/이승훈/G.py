n = int(input())
ls = list(map(int, input().split()))

sum_ls = [0 for _ in range(n+1)]
for i in range(1, n+1):
    sum_ls[i] = sum_ls[i-1] + ls[i-1]

ans0 = 0
first_val = sum_ls[n]-sum_ls[1]
for i in range(1, n):
    ans0 = max(ans0, first_val - ls[i] + sum_ls[n] - sum_ls[i+1])


ls.reverse()
sum_ls = [0 for _ in range(n+1)]
for i in range(1, n+1):
    sum_ls[i] = sum_ls[i-1] + ls[i-1]

ans1 = 0
first_val = sum_ls[n]-sum_ls[1]
for i in range(1, n):
    ans1 = max(ans1, first_val - ls[i] + sum_ls[n] - sum_ls[i+1])

ans2 = 0
for i in range(2, n):
    ans2 = max(ans2, sum_ls[i]-sum_ls[1] + sum_ls[n-1] - sum_ls[i-1])
print(max(ans0, ans1, ans2))
