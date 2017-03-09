import org.apache.log4j.Priority;

import java.io.*;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * Created by Administrator on 2017/3/7.
 */
public class huffman {
    PriorityQueue<huffmanTree> pq;
    public static void main(String[] args){
        huffman solution = new huffman();
        try {
            solution.read();
            solution.calculate();
            int max = solution.pq.peek().getMaxlength();
            int min = solution.pq.peek().getMinlength();
            System.out.println(max);
            System.out.println(min);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    private void calculate(){
        while (pq.size() > 1){
            huffmanTree huffmanTree1 = pq.remove();
            huffmanTree huffmanTree2 = pq.remove();

            huffmanTree parentTree = huffmanTree.union(huffmanTree1,huffmanTree2);
            pq.add(parentTree);
        }
        //return pq.peek().getMaxlength();
    }
    private void read() throws FileNotFoundException, IOException{
        File file = new File("D:/javaer/abc/StanAl/p3/huffman.txt");
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        String str = br.readLine();
        pq = new PriorityQueue<>();

        int numberOfSymbols = Integer.parseInt(str);
        while ((str = br.readLine()) != null){
            int weight = Integer.parseInt(str);

            pq.add(new huffmanTree(weight));
        }
    }
}
