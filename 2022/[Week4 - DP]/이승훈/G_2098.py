import sys

input = sys.stdin.readline
n = int(input())
graph = [list(map(int, input().split())) for _ in range(n)]
dp = [[float('inf') for _ in range(1 << n)]
      for _ in range(n)]  # 현재 위치부터 시작점까지 거리


def find_path(last, visited):
    if visited == (1 << n) - 1:
        if graph[last][0] == 0:
            return float('inf')
        return graph[last][0]

    if dp[last][visited] != float('inf'):
        return dp[last][visited]

    for i in range(n):
        if graph[last][i] == 0:
            continue
        if visited & (1 << i):
            continue

        dp[last][visited] = min(dp[last][visited],
                                find_path(i, visited | (1 << i)) + graph[last][i])
    return dp[last][visited]


print(find_path(0, 1 << 0))
