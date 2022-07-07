import sys
import math
input = sys.stdin.readline
# start, end의 범위는 더해지는 범위


strVal = input().strip() + '<'
seq = ''
for s in strVal:
    if s == '<':
        if seq and seq[-1] == '>':
            print(seq, end='')
        else:
            print(''.join(list(reversed(seq))), end='')
        seq = '<'
        continue
    if s == '>':
        print(seq + '>', end='')
        seq = ''
        continue
    if seq and seq[0] not in ['<', '>'] and s == ' ':
        print(''.join(list(reversed(seq))) + ' ', end='')
        seq = ''
        continue
    seq += s
