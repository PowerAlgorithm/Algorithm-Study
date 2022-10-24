N = int(input())
A = list(map(int, input().split()))


def divine_conquer(start, end):
    cnt = 0
    if start >= end:
        return 0
    m = (start + end) // 2
    l, r = start, m + 1
    cnt += divine_conquer(start, m)
    cnt += divine_conquer(m + 1, end)

    temp = []
    while l <= m and r <= end:
        if A[l] <= A[r]:
            temp.append(A[l])
            l += 1
        else:
            temp.append(A[r])
            r += 1
            cnt += (m + 1 - l)

    if l <= m:
        temp.extend(A[l:m + 1])
    if r <= end:
        temp.extend(A[r:end + 1])

    for i in range(start, end + 1):
        A[i] = temp[i - start]

    return cnt

answer = divine_conquer(0, N - 1)
print(answer)
