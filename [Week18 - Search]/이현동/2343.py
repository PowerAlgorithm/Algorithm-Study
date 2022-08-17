import sys
input = sys.stdin.readline


N, M = map(int, input().split())
arr = list(map(int, input().split()))


l = max(arr)
r = 1000000001
mid = 0

while l <= r:
    mid = (l+r)//2
    cnt, tot = 0, 0
    for i in range(N):
        if(arr[i] + tot > mid):
            tot = 0
            cnt+=1
        tot += arr[i]
    
    if tot != 0:
        cnt+=1
    
    if cnt > M:
        l = mid + 1
    else:
        r = mid - 1
        
print(l)