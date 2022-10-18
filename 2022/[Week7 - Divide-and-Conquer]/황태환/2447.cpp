#include <bits/stdc++.h>

void func(int a, int b, int n)
{
	if ((a / n) % 3 == 1 && (b / n) % 3 == 1)
		std::cout << ' ';
	else
	{
		if (!(n / 3))
			std::cout << "*";
		else
			func(a, b, n / 3);
	}
}

int main(int argc, char* argv[])
{
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr), std::cout.tie(nullptr);

	int n;
	std::cin >> n;

	for (int i = 0; i < n; i++)
	{
		for (int j = 0; j < n; j++)
			func(i, j, n);
		std::cout << '\n';
	}
	return 0;
}