import org.apache.commons.collections.Buffer;
import sun.java2d.pipe.BufferedBufImgOps;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * Created by Administrator on 2017/3/4.
 */
public class clustering {
    public static void main(String[] args){
        try {
            File file = new File("D:/javaer/abc/StanAl/p3/clustering1.txt");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            int n = Integer.parseInt(br.readLine());
            String str;
            int first,second,third;
            String[] three;

            //use a priority queue for searching
            PriorityQueue<distance> pq = new PriorityQueue<>();

            //use an array of sets to implement cluster
            /**Union find is very important, should memorize it*/
            cluster cluster = new cluster(n);

            while ((str = br.readLine())!=null){
                three = str.split(" ");
                first = Integer.parseInt(three[0]);
                second = Integer.parseInt(three[1]);
                third = Integer.parseInt(three[2]);

                pq.add(new distance(first,second,third));

            }
            int count = n;
            int objective = 4;
            while (cluster.getNumber() > objective){
                // if p and q are not in the same cluster
                distance d1 = pq.remove();
                if (cluster.root(d1.getPoint1()) != cluster.root(d1.getPoint2())) {
                    //merge into one cluster
                    cluster.union(d1.getPoint1(),d1.getPoint2());
                }
            }
            distance d = pq.remove();
            while(cluster.root(d.getPoint1()) == cluster.root(d.getPoint2())){
                d = pq.remove();
            }
            System.out.print(d.getDistance());//106


        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
