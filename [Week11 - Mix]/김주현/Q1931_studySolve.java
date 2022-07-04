// 1931번 회의실 배정 (S1) [정렬]
/*
<문제 정보>
 1. 회의 시작시간, 끝시간이 주어질 때 사용할 수 있는 회의의 최대 개수 출력

<변수 범위>
 1. 2초 / 128MB
 2. 1<=N<=100,000
 3. 시간 값 int 범위 안의 음아닌 정수

<프로그램 진행>
 1.

<필요 함수>
 1.

 */

/*
 희의실을 가장 많이 사용하려면 ?
    - 같은 시작시간이라면 끝나는 시간이 빠른 회의를 선택해야 한다. (O)
    - 다음 회의를 선택할 때는 끝나는 시간과 가장 가까운 시작시간을 가지는 회의를 선택한다.
    => 시작시간과 끝나는 시간을 이분탐색으로 찾자! (X)

    - 만약에 0~6, 1~2 가 있다면 1~2를 선택해야함.
    - (수정) 다음 회의를 선택할 때는 이전 회의가 끝나는 시간보다 크거나 같으면서 진행될 회의의 끝나는 시간이 가장 빠른 회의를 선택
    => 함수 find(int endTime) ->  endTime 이상의 시작시간을 가지면서 끝나는 시간이 가장 빠른 회의를 탐색

    데이터의 개수가 100,000 이기 때문에 선형 탐색 가능 -> endTime 기준으로 정렬 후 탐색
 */


import java.io.*;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Q1931_studySolve {
    static int N;
    static Meeting[] meetings;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        // ****** 입력 ******
        N = Integer.parseInt(br.readLine());
        meetings = new Meeting[N];
        StringTokenizer st;
        int startTime, endTime;
        for (int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            startTime = Integer.parseInt(st.nextToken());
            endTime = Integer.parseInt(st.nextToken());
            meetings[i] = new Meeting(startTime, endTime);
        }

        // ***** endTime 기준 오름차순 정렬 (시작시간은 상관없음) ******
        Arrays.sort(meetings);

        // ***** 선형 탐색
        int lastEndTime = -1;
        int lastStartTime = -1;
        int possibleMeeting = 0;
        for (int i=0;i<N;i++) {
            // 회의 시작 시간이 이전 회의 끝나는 시간보다 작다면 continue
            System.out.printf("i : %d, lastEndTime : %d\n",i,lastEndTime);
            if (meetings[i].startTime < lastEndTime) continue;

            // (start,end) -> (1,1), (1,1) 2개가 있다면 하나는 배제해야함.
            if (meetings[i].endTime == lastEndTime && meetings[i].startTime == lastStartTime) continue;

            // 시작이 (3,7)->(7,7) vs (7,7)->(3,7) !!!!  후자의 경우 (3,7)을 세주지 못함!
            // 즉 endTime이 같다면 startTime이 작은 순으로 정렬 해주어야 함!!

            // 시작시간이 조건에 맞다면 가능한 회의 수를 1 늘려주고 lastEndTime을 갱신한다.
            possibleMeeting++;
            lastEndTime = meetings[i].endTime;
            lastStartTime = meetings[i].startTime;
            System.out.printf("possible Meeting : %d\n",possibleMeeting);
        }

        sb.append(possibleMeeting);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}

class Meeting implements Comparable<Meeting>{
    int startTime, endTime;

    public Meeting (int startTime, int endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public int compareTo(Meeting o) {
        return this.endTime == o.endTime ? this.startTime-o.startTime : this.endTime-o.endTime;
    }
}



















