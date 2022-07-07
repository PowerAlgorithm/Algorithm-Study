import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/***
 * 키워드
 *
 * 조건
 *
 * 풀이
 *
 */
class Num {
    int key;
    int num;
    int pos;

    Num(int key, int num, int pos) {
        this.key = key;
        this.num = num;
        this.pos = pos;
    }
}


class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        HashMap<Integer, Num> hashMap = new HashMap<>();
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            int num = Integer.parseInt(st.nextToken());

            if(hashMap.containsKey(num)) {
                Num num0 = hashMap.get(num);
                num0.num += 1;
            } else {
                hashMap.put(num, new Num(num,1, i));
            }
        }
        List<Num> collect = hashMap.values().stream().collect(Collectors.toList());
        collect.sort(Comparator.comparing(v -> v.pos));
        collect.sort(Comparator.comparing((Num v) -> v.num).reversed());

        for(Num nc : collect) {
            for(int i=0; i<nc.num; i++) {
                System.out.print(nc.key + " ");
            }
        }

        br.close();
    }
}
