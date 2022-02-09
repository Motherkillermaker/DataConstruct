package LeetCode;

/**
 * @author CJYong
 * @create 2022-02-09 12:11
 */
public class RandomNode {

    public int val;

    public RandomNode next;

    public RandomNode random;

    public RandomNode() {
    }

    public RandomNode(int val) {
        this.val = val;
    }

    public RandomNode(int val, RandomNode next, RandomNode random) {
        this.val = val;
        this.next = next;
        this.random = random;
    }

    @Override
    public String toString() {
        return "LeetCode.RandomNode{" +
                "val=" + val +
                ", next=" + next +
                ", random=" + random +
                '}';
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
