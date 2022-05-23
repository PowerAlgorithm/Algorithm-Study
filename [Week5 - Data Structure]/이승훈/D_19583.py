import sys
input = sys.stdin.readline


def time(t):
    t, m = t.split(':')
    return int(t) * 60 + int(m)


start, end0, end1 = input().split()
start, end0, end1 = time(start), time(end0), time(end1)

members = set()
ans = 0
while True:
    try:
        t, n = input().split()
        if time(t) <= start:
            members.add(n)
        elif end0 <= time(t) <= end1 and n in members:
            members.remove(n)
            ans += 1
    except:
        break
print(ans)
