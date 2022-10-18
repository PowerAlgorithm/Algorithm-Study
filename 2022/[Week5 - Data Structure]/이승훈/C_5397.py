l = int(input())
for _ in range(l):
    pw = input().strip()
    left, right = [], []
    for val in pw:
        if left and val == '<':
            right.append(left.pop())
        elif right and val == '>':
            left.append(right.pop())
        elif left and val == '-':
            left.pop()
        elif val.isalnum():
            left.append(val)
    print(''.join(left + list(reversed(right))))
