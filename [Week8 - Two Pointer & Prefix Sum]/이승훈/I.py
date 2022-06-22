n = int(input())

ls = [int(input()) for _ in range(n)]


divisor = [1 for _ in range(1000001)]
divisor[0] = 0
for i in range(2, 1000001):
    j = 1
    while i * j < 1000001:
        divisor[i * j] += i
        j += 1

for i in range(1, len(divisor)):
    divisor[i] += divisor[i-1]

for val in ls:
    print(divisor[val])
