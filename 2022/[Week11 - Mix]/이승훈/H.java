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
class Pair implements Comparable<Pair>{
    int num;
    int pos;

    Pair(int num, int pos) {
        this.num = num;
        this.pos = pos;
    }

    @Override
    public int compareTo(Pair o) {
        return this.num - o.num;
    }
}
class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] nums = new int[n];
        Deque<Integer> zeroCnt = new LinkedList<>();
        ArrayList<Integer>[] arr = new ArrayList[n];
        for(int i=0; i<n; i++) {
            arr[i] = new ArrayList<>();
        }
        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken())-1;
            int b = Integer.parseInt(st.nextToken())-1;
            arr[a].add(b);
            nums[b] += 1;
        }
        for(int i=0; i<n; i++) {
            if(nums[i] == 0) {
                zeroCnt.add(i);
            }
        }

        while(!zeroCnt.isEmpty()) {
            int val = zeroCnt.poll();
            System.out.println(val+1);
            for(int child : arr[val]) {
                nums[child] -= 1;
                if(nums[child] == 0)
                    zeroCnt.add(child);
            }
        }

        br.close();
    }
}
