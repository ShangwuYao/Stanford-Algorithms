/**
 * Created by Administrator on 2017/3/2.
 */
public class jobRatio implements Comparable<jobRatio>{
    private int weight;
    private int length;
    private double ratio;

    public jobRatio(int w,int l){
        weight = w;
        length = l;
        ratio = (double) w / l;
    }
    public int compareTo(jobRatio that){
        if (that.ratio > this.ratio) return -1;
        else if (that.ratio < this.ratio) return 1;
        else return 0;
    }
    public int weightedCTime(int Ctime){
        return Ctime*weight;
    }
    public int CTime(int Time){
        return Time +length;
    }
}