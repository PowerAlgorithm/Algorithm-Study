// 11000번 강의실 배정 (G5) [우선순위큐][정렬]
/*
<문제 정보>
 1.

<변수 범위>
 1. 1초 / 256MB
 2.

<프로그램 진행>
 1.

<필요 함수>
 1.

 */

/*
 - 수업 시간중 일부가 겹친다면 다른 강의실을 써야함
 - 한 강의실의 시간표를 잡을 때 최대한 빈 공간이 없도록 시간표를 짜야 한다.
    => 이전 수업의 endTime과 다음 수업의 startTime이 최대한 붙어 있도록 한 강의실의 시간표를 짜야함

   ex) (1,3) (2,4) (2,5) (1, 4) (3, 4) (5, 7) (4, 5)
   ** N^2 풀이는 불가

   Final
   끝 시간과 그다음 시작시간이 최대한 붙어 있어야 된다고 생각했지만 그러지 않아도 됨
   시작 시작을 기준으로 오름차순 정렬을 한다면 있는 강의실 중 끝나는 시간이 가장 이른 한 곳에 넣어주면 됨 -> PriorityQueue
        - PriorityQueue에 들어가는 것은 해당 강의실 EndTime
        - pq에서 나온 강의실에 추가한 강의로 끝나는 시간을 변경한 뒤 다시 pq에 넣어줌
        - 마지막 답은 pq의 size
 */



import java.io.*;
import java.util.StringTokenizer;
import java.util.PriorityQueue;
import java.util.Arrays;

public class Q11000 {
    static int N;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // ******************** 입력 & 초기화 ********************
        N = Integer.parseInt(br.readLine());
        LectureRoom[] lectureRooms = new LectureRoom[N];

        int startTime, endTime;
        for (int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            startTime = Integer.parseInt(st.nextToken());
            endTime = Integer.parseInt(st.nextToken());

            lectureRooms[i] = new LectureRoom(startTime, endTime);
        }


        // ******************** 메인 로직 ********************

        // 1. 정렬
        Arrays.sort(lectureRooms);

        // 2. PriorityQueue를 이용해 필요한 강의실 개수 세기
        PriorityQueue<Integer> endTimeOfLectureRooms = new PriorityQueue<>();

        // 처음값 pq에 추가
        endTimeOfLectureRooms.add(lectureRooms[0].endTime);

        // 2번째 강의부터 정렬한 순서대로 pq에 넣어서 처리
        for (int i=1;i<N;i++) {
            // 현재 강의 시작시간이 pq에 있는 강의실 끝나는 시간 이후에 시작할 수 있다면
            // 끝나는 시간이 가장 빠른 강의실을 빼고,
            if (endTimeOfLectureRooms.peek() <= lectureRooms[i].startTime) {
                endTimeOfLectureRooms.poll();
            }

            // 현재 강의의 끝나는 시간을 pq에 추가 (현재 강의가 들어갈 수 없어 새로운 강의실을 만들어야 할때도 동일)
            endTimeOfLectureRooms.add(lectureRooms[i].endTime);
        }

        // ******************** 정답 출력 ********************
        // 마지막 정답은 endTimeOfLectureRooms 의 size를 구한다.
        sb.append(endTimeOfLectureRooms.size());

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}

class LectureRoom implements Comparable<LectureRoom>{
    int startTime, endTime;

    public LectureRoom (int startTime, int endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    // 시작시간을 기준으로 정렬, 같다면 종료시간이 빠른 순으로
    @Override
    public int compareTo(LectureRoom o) {
        return this.startTime == o.startTime ? this.endTime - o.endTime : this.startTime - o.startTime;
    }
}














