# 공유기 설치
n, c = map(int, input().split())

data = [int(input()) for _ in range(n)]
data.sort()

start = 1
end = data[n-1] - data[0]
result = 0

while start <= end:
    mid = (start + end) // 2
    value = data[0]
    count = 1
    
    for i in range(1, n):
        if data[i] >= value + mid:
            count += 1
            value = data[i]
    if count >= c:
        start = mid + 1
        result = mid
    else:
        end = mid - 1
print(result)