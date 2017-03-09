/**
 * Created by Administrator on 2017/3/4.
 */
public class distance implements Comparable<distance>{
    private int point1;
    private int point2;
    private int distance;
    public distance(int p1,int p2,int d){
        point1 = p1;
        point2 = p2;
        distance = d;
    }
    public int getPoint1(){
        return point1;
    }
    public int getPoint2(){
        return point2;
    }
    public int getDistance(){
        return distance;
    }
    public int compareTo(distance that){
        if (this.distance > that.distance) return 1;
        else if (this.distance < that.distance) return -1;
        else return 0;
    }
}
