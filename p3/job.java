/**
 * Created by Administrator on 2017/3/2.
 */
public class job implements Comparable<job>{
    private int weight;
    private int length;
    private int difference;

    public job(int w,int l){
        weight = w;
        length = l;
        difference = w - l;
    }
    public int compareTo(job that){
        if (that.difference > this.difference) return -1;
        else if (that.difference < this.difference) return 1;
        else if (that.weight > this.weight) return -1;
        else if (that.weight < this.weight) return 1;
        else return 0;
    }
    public int weightedCTime(int Ctime){
        return Ctime*weight;
    }
    public int CTime(int Time){
        return Time +length;
    }
}