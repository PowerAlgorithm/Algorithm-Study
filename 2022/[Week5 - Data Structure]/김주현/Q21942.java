// 21942번 부품대여장 (G2)
/*
<문제 정보>
 1.

<변수 범위>
 1. 1초 / 512MB

<프로그램 진행>
 1.

<필요 함수>
 1.

 */


import java.io.*;
import java.util.StringTokenizer;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.Map.Entry;

public class Q21942 {
    static int N,L,F;
    static int[] eachMonthDays = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        String period = st.nextToken();
        F = Integer.parseInt(st.nextToken());
        L = getPeriodMinute(period);

        HashMap<String, Info> memberList = new HashMap<>();
        String date, time, parts, member;
        for (int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            date = st.nextToken();
            time = st.nextToken();
            parts = st.nextToken();
            member = st.nextToken();

            int minuteTime = timeToMinute(date, time);
            // 회원 목록에 사람이 없다면 추가
            if (!memberList.containsKey(member)) {
                memberList.put(member, new Info());
            }

            // 빌린 목록에 없다면 추가
            if (!memberList.get(member).partsList.containsKey(parts)) {
                memberList.get(member).partsList.put(parts, minuteTime);
            }

            // 목록에 있다면 반납이므로 반납시간 체크하고 벌금 부과 -> 목록에서 없애기
            else {
                int borrowTime = memberList.get(member).partsList.get(parts);
                if (minuteTime - borrowTime > L) {
                    memberList.get(member).fine += (long) (minuteTime-borrowTime-L)*F;
                }
                memberList.get(member).partsList.remove(parts);
            }
        }

        TreeMap<String, Info> sortedMemberList = new TreeMap<>(memberList);
        int finePeople = 0;
        for (Entry<String, Info> entry : sortedMemberList.entrySet()) {
            if (entry.getValue().fine==0) continue;
            finePeople++;
            sb.append(entry.getKey()).append(" ").
                    append(entry.getValue().fine).append("\n");
        }

        if (finePeople==0) sb.append(-1);


        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static int getPeriodMinute(String period) {
        StringTokenizer st = new StringTokenizer(period,"/:");
        int day = Integer.parseInt(st.nextToken());
        int hour = Integer.parseInt(st.nextToken());
        int min = Integer.parseInt(st.nextToken());
        return (day*1440) + (hour*60) + min;
    }

    public static int timeToMinute(String date, String time) {
        // 2021-01-01 00:00 을 0이라고 할 때의 minute
        StringTokenizer st = new StringTokenizer(date,"-");
        st.nextToken(); // 년도는 항상 같으므로 버림
        int month = Integer.parseInt(st.nextToken());
        int day = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(time,":");
        int hour = Integer.parseInt(st.nextToken());
        int min = Integer.parseInt(st.nextToken());

        int totalDays = dateToDays(month, day);
        return (totalDays*1440) + (hour*60) + min;
    }

    public static int dateToDays(int month, int day) {
        int days=0;
        if (month == 1) days = day-1;
        else {
            for (int i=1;i<month;i++) days+=eachMonthDays[i];
            days += day-1;
        }
        return days;
    }
}

class Info{
    long fine;
    HashMap<String, Integer> partsList;

    public Info() {
        fine = 0;
        partsList = new HashMap<>();
    }
}
