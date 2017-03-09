/**
 * Created by Administrator on 2017/3/7.
 */
public class huffmanTree implements Comparable<huffmanTree>{
    private int weight;
    private huffmanTree left;
    private huffmanTree right;
    private huffmanTree parent;
    private int maxlength;
    private int minimumlength;
    huffmanTree(int weight){
        this.weight = weight;
    }
    huffmanTree(huffmanTree left,huffmanTree right){
        this.weight = left.weight + right.weight;
        this.left = left;
        this.right = right;
        // bottom-up, so this is correct
        this.maxlength = Math.max(left.maxlength, right.maxlength) + 1;
        this.minimumlength = Math.min(left.minimumlength, right.minimumlength) + 1;
    }
    public int getMaxlength(){
        return maxlength;
    }
    public int getMinlength(){
        return minimumlength;
    }
    public int compareTo(huffmanTree that){
        if (this.weight > that.weight) return 1;
        else if (this.weight < that.weight) return -1;
        else return 0;
    }
    /**
     * return the parent tree after the union operation
     */
    public static huffmanTree union(huffmanTree tree1, huffmanTree tree2){
        int compare = tree1.compareTo(tree2);
        huffmanTree parentTree;
        if (compare <= 0) {
            parentTree = new huffmanTree(tree1,tree2);
        }
        else {
            parentTree = new huffmanTree(tree2,tree1);
        }
        tree1.parent = parentTree;
        tree2.parent = parentTree;
        return parentTree;
    }
}
