import sys
input = sys.stdin.readline

n = int(input())
liq = list(map(int, input().split()))
liq.sort()

l, r = 0, n-1
res = []
answer = 2e+9+1


# 투포인터 진행
while l < r:
    tot = liq[l]+ liq[r]
    # 두 용액의 합이 0과 가장 가까운 용액을 정답에 담아주기
    if abs(tot) < answer:
        answer = abs(tot)
        res = [liq[l], liq[r]]
    # 두 용액의합이 0보다 작다면 왼쪽의 값을 늘려 조금 더 0에 가깝게 만들어준다
    if tot < 0:
        l += 1
    # 반대로, 두 용액의 합이 0보다 크다면 오른쪽의 값을 줄여서 조금 더 0에 가깝게 만들어준다
    else:
        r -= 1

print(*res)