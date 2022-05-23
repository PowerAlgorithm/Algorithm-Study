import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        String S = st.nextToken(); // 개강총회 시작 시간
        String E = st.nextToken(); // 개강총회 끝낸 시간
        String Q = st.nextToken(); // 개강총회 스트리밍 끝낸 시간

        // 개강총회가 시작한 시간 이전에(개강총회 시간까지) 대화를 한 적이 있는 대상
        // 개강총회를 끝내고 나서 , 스트리밍을 끝낼 때 까지
        Set<String> before = new HashSet<>();
        Set<String> after = new HashSet<>();
        Set<String> names = new HashSet<>();

        String str;
        while ((str = br.readLine()) != null && str.length() > 0) {
            StringTokenizer line = new StringTokenizer(str);
            String time = line.nextToken();
            String name = line.nextToken();
            names.add(name);
            if (S.compareTo(time) >= 0) {
                before.add(name);
            } else if (E.compareTo(time) <= 0 && Q.compareTo(time) >= 0) {
                after.add(name);
            }
        }

        int count = 0;
        for (String name : names) {
            if (before.contains(name) && after.contains(name))
                count++;
        }

        bw.append(String.valueOf(count));
        bw.flush();
        bw.close();
        br.close();
    }
}