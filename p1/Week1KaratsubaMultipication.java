/**
 * Created by Administrator on 2017/1/24.
 */
import java.math.BigInteger;
public class Week1KaratsubaMultipication {
    public static void main(String[] args) {
        BigInteger big1 =  new BigInteger("3141592653589793238462643383279502884197169399375105820974944592");
        BigInteger big2 =  new BigInteger("2718281828459045235360287471352662497757247093699959574966967627");
        //BigInteger big1 =  new BigInteger("25872323");
        //BigInteger big2 =  new BigInteger("23232587");

        System.out.println(big1.toString());
        System.out.println(big2.toString());

        System.out.println(calculate(big1,big2));
        //System.out.println(big1.multiply(big2).toString());
    }
    private static BigInteger calculate(BigInteger big1, BigInteger big2){
        int length = big1.toString().length();
        if (length == 1){
            int intbig1 = big1.intValue();
            int intbig2 = big2.intValue();
            int res = intbig1*intbig2;
            return new BigInteger(String.valueOf(res));
        }
        else {
            int length2 = big2.toString().length();
            int len1;
            int len2;
            if ((length % 2) == 1){len1 = length/2 + 1;}
                else {len1 = length/2;}
            len2 = length2 - (length - len1);
            String biga = big1.toString().substring(0, len1);
            String bigb = big1.toString().substring(len1);
            String bigc = big2.toString().substring(0, len2);
            String bigd = big2.toString().substring(len2);

            if (bigc.length() == 0){
                bigc = "0";
            }
            if (bigd.length() == 0){
                bigd = "0";
            }
            BigInteger bigInta = new BigInteger(biga);
            BigInteger bigIntb = new BigInteger(bigb);
            BigInteger bigIntc = new BigInteger(bigc);
            BigInteger bigIntd = new BigInteger(bigd);

            BigInteger proc1 = calculate(bigInta, bigIntc);
            BigInteger proc2 = calculate(bigIntb, bigIntd);
            BigInteger proc3 = calculate(bigInta.add(bigIntb), bigIntc.add(bigIntd));
            BigInteger proc4 = proc3.subtract(proc1.add(proc2));

            String stringac = proc1.toString();
            String stringadbc = proc4.toString();
            StringBuilder sb1 = new StringBuilder();
            for (int i = 0; i < (length - len1)*2; i++) {
                sb1.append("0");
            }
            StringBuilder sb2 = new StringBuilder();
            for (int i = 0; i < length - len1; i++) {
                sb2.append("0");
            }
            BigInteger By10ac = new BigInteger(stringac.concat(sb1.toString()));
            BigInteger By10adbc = new BigInteger(stringadbc.concat(sb2.toString()));
            return By10ac.add(By10adbc).add(proc2);
        }
    }
}
