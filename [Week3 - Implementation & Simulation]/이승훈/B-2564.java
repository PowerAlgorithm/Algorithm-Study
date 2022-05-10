import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

/***
 * 키워드
 *
 *  조건
 *
 * 풀이
 *
 */

class Pair {
    int x;
    int y;
    int dir;
    public Pair(int x, int y, int dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }
}

class Main {
    public static int[] seq;
    public static int N;

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(br.readLine());

        Pair[] stores = new Pair[n];
        Pair person = null;
        int circumference = (x + y) * 2;
        int ans = 0;

        for(int i=0; i<n+1; i++) {
            st = new StringTokenizer(br.readLine());
            int dir = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());
            int _x;
            int _y;
            if(dir == 1) {
                _x = dist;
                _y = 0;
            }else if(dir == 2) {
                _x = dist;
                _y = y;
            }else if(dir == 3) {
                _x = 0;
                _y = dist;
            }else {
                _x = x;
                _y = dist;
            }
            if (i == n) {
                person = new Pair(_x, _y, dir);
                break;
            }
            stores[i] = new Pair(_x, _y, dir);
        }

        for(int i=0; i<n; i++) {
            ArrayList<Integer> cases = new ArrayList<>();
            Pair store = stores[i];
            if (store.x == person.x && store.x==0) {
                cases.add(Math.abs(store.y - person.y));
                cases.add(circumference - Math.abs(store.y - person.y));
            }
            else if (store.y == person.y && store.y == 0) {
                cases.add(Math.abs(store.x - person.x));
                cases.add(circumference - Math.abs(store.x - person.x));
            }
            else if(store.dir + person.dir == 3 || store.dir + person.dir == 7) {
                int val = Math.abs(store.x + person.x + store.y + person.y);
                cases.add(val);
                cases.add(circumference - val);
            } else {
                int val2 = Math.abs(store.x - person.x) + Math.abs(store.y - person.y);
                cases.add(val2);
                cases.add(circumference - val2);
            }
            Optional<Integer> min = cases.stream().min(Comparator.naturalOrder());
            ans += min.get();
        }
        System.out.println(ans);
        br.close();
    }


}