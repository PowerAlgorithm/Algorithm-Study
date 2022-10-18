t = int(input())


def divide(paper):
    if len(paper) == 1:
        return True

    mid = len(paper) // 2
    tmp = list(reversed(paper[mid+1:]))
    i = 0
    success = True
    for t in tmp:
        if t == paper[i]:
            success = False
            break
        i += 1
    if success:
        return divide(tmp)
    return False


for _ in range(t):
    paper = input().strip()
    if divide(paper):
        print('YES')
    else:
        print('NO')
