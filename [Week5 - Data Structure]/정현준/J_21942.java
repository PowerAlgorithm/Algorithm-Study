import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        Map<String, LocalDateTime> infos = new HashMap<>();
        TreeMap<String, Long> fines = new TreeMap<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int infoCount = Integer.parseInt(st.nextToken());
        int rentalMinute = getRentalMinute(st.nextToken());
        int fine = Integer.parseInt(st.nextToken());

        while (infoCount-- > 0) {
            st = new StringTokenizer(br.readLine());
            String[] dates = st.nextToken().split("-");
            String[] times = st.nextToken().split(":");
            String item = st.nextToken();
            String name = st.nextToken();
            String key = item + name;
            LocalDate date = LocalDate.of(Integer.parseInt(dates[0]), Integer.parseInt(dates[1]),
                    Integer.parseInt(dates[2]));
            LocalTime time = LocalTime.of(Integer.parseInt(times[0]), Integer.parseInt(times[1]));
            // infos에 이미 있다면 반납
            if (infos.containsKey(key)) {
                LocalDateTime rentalTime = infos.get(key);
                long diff = ChronoUnit.MINUTES.between(rentalTime, LocalDateTime.of(date, time));
                if (diff > rentalMinute) {
                    long amt = (diff - rentalMinute) * fine;
                    if (fines.containsKey(name)) {
                        fines.put(name, fines.get(name) + amt);
                    } else {
                        fines.put(name, amt);
                    }
                }
                infos.remove(key);
            } else {
                infos.put(key, LocalDateTime.of(date, time));
            }
        }

        if (fines.isEmpty()) {
            bw.append("-1");
        } else {
            for (String key : fines.keySet()) {
                bw.append(key).append(" ").append(String.valueOf(fines.get(key))).append("\n");
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }

    public static int getRentalMinute(String line) {
        int minute = 0;
        String[] timestamp = line.split("/");
        minute += Integer.parseInt(timestamp[0]) * 24 * 60;
        String[] time = timestamp[1].split(":");
        minute += Integer.parseInt(time[0]) * 60;
        minute += Integer.parseInt(time[1]);
        return minute;
    }
}