import com.google.common.collect.BiMap;
import com.sun.scenario.effect.impl.state.LinearConvolveRenderState;
import org.apache.log4j.Priority;

import java.util.*;

/**
 * Created by Administrator on 2017/3/2.
 */
public class testjob {
    public static void main(String[] args){
        Integer[] a = {3,2,4,1,5,6,8,7};
        Arrays.sort(a, Collections.reverseOrder());
        System.out.println(Arrays.toString(a));
        //System.out.println(1/2);
        HashMap b = new HashMap();

        PriorityQueue pq = new PriorityQueue(Collections.reverseOrder());
        pq.add(3);
        pq.add(2);
        pq.add(4);
        pq.add(1);
        pq.add(5);
        System.out.println(pq.remove());
        System.out.println(pq.remove());
        System.out.println(pq.remove());
        System.out.println(pq.remove());
        System.out.println(pq.remove());
        Queue queue = new LinkedList();
    }
}
