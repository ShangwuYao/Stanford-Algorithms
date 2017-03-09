import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by Administrator on 2017/3/2.
 */
public class jobSequence {

    public static void main(String[] args){
        /**bug-free*/
        /**
         * good job, using another class for job and implement the comparable interface
         * actually saved a lot of trouble. easily sorted.
         */
        try {
            File file = new File("D:/javaer/abc/StanAl/p3/jobs.txt");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            int n = Integer.parseInt(br.readLine());

            job[] jobs = new job[n];
            //brute force finding the smallest

            String str;
            String[] two;
            int first,second;
            int i =0;
            while ((str =br.readLine())!= null){
                two = str.split(" ");
                first = Integer.parseInt(two[0]);
                second = Integer.parseInt(two[1]);

                jobs[i] = new job(first,second);
                i++;
            }
            Arrays.sort(jobs);
            int Ctime = 0;
            long sum = 0;
            for (i=i-1;i>=0;i--){
                Ctime = jobs[i].CTime(Ctime);
                sum += jobs[i].weightedCTime(Ctime);
            }
            System.out.println(sum);
            //solution 1: 69119377652
            //solution 2: 67311454237  be careful for overflow
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
}

