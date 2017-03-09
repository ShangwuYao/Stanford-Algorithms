import java.util.AbstractMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/2.
 */
public class heap {
    private int[] array;
    private int length;
    private void heap(int n){
        array = new int[n+1];
        length = 0;
    }
    private void insert(int value){
        //using the actual index, not -1
        array[length+1] = value;
        swim(length+1);
        length+=1;
    }
    private void swim(int k){
        while (k>1 && array[k/2] > array[k]){
            exch(k/2,k);
            k=k/2;
        }
    }
    private int Extract_min(){
        exch(1,length);
        int res = array[length];
        array[length--] = 0;
        sink(1);
        return res;
    }
    private void sink(int k){
        while (2*k<length+1){
            int j = 2*k;
            if (j<length && array[j] > array[j+1]) j++;
            if (array[k] < array[j]) break;
            exch(k,j);
            k=j;
        }
    }
    private void exch(int a, int b){
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
    private int pick(){
        return array[1];
    }
}
