import java.io.*;
import java.util.*;
import java.util.stream.Collectors;


/***
 * 키워드
 *
 * 조건
 *
 * 풀이
 *
 */

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken())+1;
        int k = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];
        List<String>[] calc = new ArrayList[n];

        for (int i=0; i<n; i++) {
            calc[i] = new ArrayList<>();
        }

        for(int i=1; i<n; i++) {
            int tmp = 0;
            for(int j=1; j<=i; j++) {
                int start = j;
                int end = i - j;
                if (end==0) {
                    if (start <= 3) {
                        calc[i].add(Integer.toString(start));
                        tmp += 1;
                    }
                    else continue;
                } else if (end <= 3){
                    for(String c : calc[start])
                        calc[i].add(c + "+" + end);
                    tmp += (arr[start]);
                }
            }
            arr[i] = tmp;
        }
        List<String> collect = calc[n-1].stream().sorted().collect(Collectors.toList());
//        collect.stream().forEach(System.out::println);
        if(k > collect.size()) {
            System.out.println(-1);
            return;
        }
        System.out.println(collect.get(k-1));
        br.close();

    }


}