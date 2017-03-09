import java.io.*;
import java.util.Arrays;

/**
 * Created by Administrator on 2017/2/27.
 */
public class MedianMaintainence {
    /** using 2 heaps, shouldn't be very hard.*/
    private int n = 10000;//TODO: THIS SHOULD CHANGE

    public static void main(String[] args){
        try {
            File file = new File("D:/javaer/abc/StanAl/p2/Median.txt");
            FileInputStream fis = new FileInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(inputStreamReader);

            MedianMaintainence medianM = new MedianMaintainence();
            medianM.Lowheap();
            medianM.Highheap();
            int n = 10000;//TODO: THIS SHOULD CHANGE
            int[] medians = new int[n];

            String str;
            int value;
            int i = 0;
            while((str = br.readLine())!= null){
                value = Integer.parseInt(str);
                medianM.insert(value);
                medians[i] = medianM.pickMedian();
                i++;
                //System.out.print(Arrays.toString(medianM.lowarray));
                //System.out.println(Arrays.toString(medianM.higharray));
            }
            int sum = 0;
            for (int j = 0; j<medians.length;j++){
                sum += medians[j];
                sum = sum%10000;
            }
            //System.out.println(Arrays.toString(medianM.lowarray));
            //System.out.println(Arrays.toString(medianM.higharray));
            System.out.println(Arrays.toString(medians));
            System.out.println(sum);
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }
    private int pickMedian(){
        if (highlength > lowlength) return highpick();
        else return lowpick();
    }
    private void insert(int k){
        if (k > highpick())  highinsert(k);
        else  lowinsert(k);

        if (lowlength > highlength + 1){
            int value = Extract_max();
            highinsert(value);
        }
        else if (highlength > lowlength + 1){
            int value = Extract_min();
            lowinsert(value);
        }
    }
    /**implement with an array*/
    private int[] lowarray;
    private int lowlength;

        private void Lowheap(){
            lowarray = new int[n+1];
            lowlength = 0;
        }
        private void lowinsert(int value){
            //using the actual index, not -1
            lowarray[lowlength+1] = value;
            lowswim(lowlength+1);
            lowlength+=1;
        }
        private void lowswim(int k){
            while (k>1 && lowarray[k/2] < lowarray[k]){
                lowexch(k/2,k);
                k=k/2;
            }
        }
        private void lowexch(int a, int b){
            int temp = lowarray[a];
            lowarray[a] = lowarray[b];
            lowarray[b] = temp;
        }

        private int Extract_max(){
            lowexch(1,lowlength);
            int res = lowarray[lowlength];
            lowarray[lowlength--] = 0;
            lowsink(1);
            return res;
        }
        private void lowsink(int k){
            while (2*k<highlength+1){
                int j = 2*k;
                if (j<lowlength && lowarray[j] < lowarray[j+1]) j++;
                if (lowarray[k] > lowarray[j]) break;
                lowexch(k,j);
                k=j;
            }
        }
        private int lowpick(){
            return lowarray[1];
        }

    private int[] higharray;
    private int highlength;
    private void Highheap(){
        higharray = new int[n+1];
        highlength = 0;
    }
    private void highinsert(int value){
        //using the actual index, not -1
        higharray[highlength+1] = value;
        highswim(highlength+1);
        highlength+=1;
    }
    private void highswim(int k){
        while (k>1 && higharray[k/2] > higharray[k]){
            highexch(k/2,k);
            k=k/2;
        }
    }
    private int Extract_min(){
        highexch(1,highlength);
        int res = higharray[highlength];
        higharray[highlength--] = 0;
        highsink(1);
        return res;
    }
    private void highsink(int k){
        while (2*k<highlength+1){
            int j = 2*k;
            if (j<highlength && higharray[j] > higharray[j+1]) j++;
            if (higharray[k] < higharray[j]) break;
            highexch(k,j);
            k=j;
        }
    }
    private void highexch(int a, int b){
        int temp = higharray[a];
        higharray[a] = higharray[b];
        higharray[b] = temp;
    }
    private int highpick(){
        return higharray[1];
    }
}
