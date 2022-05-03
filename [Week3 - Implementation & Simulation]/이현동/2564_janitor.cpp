#include <bits/stdc++.h>
using namespace std;
using pii = std::pair<int, int>;

/*
 * 1번째 인자, 0 - 북 (i-0), 1 - 남(i-N), 2 - 서(j-0), 3 - 동(j-M)
 * 2번째 인자, 왼쪽 경계로부터 거리
 * 상점, 동근 블록의 위치 꼭지점 될 수 없음.
 */

int N, M;
int store;
int nowD, nowL;
vector<pii> loc;
vector<pii> now; // 동근이의 현  위치

int main()
{

    ios::sync_with_stdio(0);
    cin.tie(0);
    cin >> M >> N;
    cin >> store;
    for (int i = 0; i < store;i++){
        int d, l;
        cin >> d >> l;
        loc.push_back(make_pair(d, l));
    }
    
    cin >> nowD >> nowL;
    int ans = 0;
    for (auto a : loc)
    {
        int storeD = a.first, storeL = a.second;
        int tmp;
        if (nowD == storeD)
            tmp = abs(nowL - storeL);
        else{
            //1 - 북 (i-0), 2 - 남(i-N), 3 - 서(j-0), 4 - 동(j-M)
            if (nowD == 1){ //북
                if (storeD == 2){
                    tmp = min(nowL + storeL + N, M - nowL + M- storeL + N);
                }
                else if (storeD == 3)
                {
                    tmp = nowL + storeL;
                }
                else if (storeD == 4)
                {
                    tmp= M - nowL + storeL;
                }
            }else if(nowD == 2){ // 남
                if (storeD == 1){ // 북
                    tmp= min(nowL + storeL + N, M - nowL + M - storeL + N);
                }
                else if (storeD == 3)
                {
                    tmp= nowL + N - storeL;
                }
                else if (storeD == 4)
                {
                    tmp= M - nowL + N - storeL;
                }
            }else if(nowD == 3){ //서
                if (storeD == 1){
                    tmp= nowL + storeL;
                }
                else if (storeD == 2)
                {
                    tmp = N - nowL + storeL;
                }
                else if (storeD == 4) //동
                {
                    tmp= min(nowL + M + storeL, N - nowL + M + N - storeL);
                }
            }else if(nowD == 4){ // 동
                if (storeD == 1){ //북
                    tmp= nowL + M-storeL;
                }
                else if (storeD == 2) // 남
                {
                    tmp= N - nowL + M - storeL;
                }
                else if (storeD == 3) // 서
                {
                    tmp= min(nowL + M + storeL, N - nowL + M + N - storeL);
                }
            }
        }
        ans += tmp;
    }
    cout << ans;

    return 0;
}