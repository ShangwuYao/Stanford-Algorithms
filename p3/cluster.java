import java.util.HashSet;

/**
 * Created by Administrator on 2017/3/4.
 */
public class cluster{
    int[] intfield;
    int[] size;
    int number;
    public cluster(int N){
        intfield = new int[N + 1];
        size = new int[N + 1];
        number = N;
        for (int i = 1; i<N + 1;i++){
            intfield[i] = i;
            size[i] = 1;
        }
    }
    public int root(int i){
        int current = i;
        while (intfield[current] != current){
            intfield[current] = intfield[intfield[current]];//path compression
            current = intfield[current];
        }
        return current;
    }
    public void union(int p, int q){
        if (root(p) != root(q)){
            if (size[p] > size[q]){
                intfield[root(q)] = intfield[root(p)];
                size[root(p)] += size[root(q)];
            }else {
                intfield[root(p)] = intfield[root(q)];
                size[root(q)] += size[root(p)];
            }
            number--;
        }
    }
    public int getNumber(){
        return number;
    }
}
