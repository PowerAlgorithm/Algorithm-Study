// 19583번 싸이버개강총회 (S2) [자료구조 - 덱 or 스택]
/*
<문제 정보>
 1. 입장~퇴장 모두 확인된 회원수 출력
    - 개총 시작시간 이하 -> 입장 완료
    - 개총 끝시간 이상 스트리밍 끝시간 이하 -> 퇴장완료

<변수 범위>
 1. 1초 / 1024MB
 2. 시간 범위 00:00 ~ 23:59
 3. 학회원 닉네임 - 알파벳 대소문자, 특수기호 (. _ -)
 4. 닉네임 최대 20글자
 5. 채팅기록은 10만줄을 넘지 않음

<프로그램 진행>
 1.

<필요 함수>
 1.

 */


import java.io.*;
import java.util.StringTokenizer;
import java.util.HashSet;

public class Q19583 {
    static int meetingStartTime, meetingEndTime, streamingEndTime;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        meetingStartTime = convertTimeToMinute(st.nextToken());
        meetingEndTime = convertTimeToMinute(st.nextToken());
        streamingEndTime = convertTimeToMinute(st.nextToken());
        HashSet<String> roll = new HashSet<>();
        HashSet<String> attendee = new HashSet<>();
        String chatRecord;
        int time;
        String member;
        while( (chatRecord=br.readLine()) != null) {
            if (chatRecord.equals("")) break;
            st = new StringTokenizer(chatRecord);
            time = convertTimeToMinute(st.nextToken());
            member = st.nextToken();
            //  받아온 시간이 개총시작 전이라면 HashSet에 등록
            if (time <= meetingStartTime) roll.add(member);
            if (time>=meetingEndTime && time<=streamingEndTime && roll.contains(member)) attendee.add(member);
        }

        sb.append(attendee.size());

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static int convertTimeToMinute(String time) {
        int hour = Integer.parseInt(time.substring(0,2));
        int minute = Integer.parseInt(time.substring(3,5));
        return hour*60 + minute;
    }
}
