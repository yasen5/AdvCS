import java.util.Set;
import java.util.TreeSet;

public class Runner {
    public static void main(String[] args) {
        String[] letters = "a f d d e v j q w e e a e z f g j".split(" ");
        Set<String> strSet = new TreeSet<String>();
        for (String letter : letters) {
            strSet.add(letter);
        }
        System.out.println(strSet);
        String[] strNumbers = "one two three one two three six seven one two".split(" ");
        Set<String> numSet = new TreeSet<String>();
        for (String strNum : strNumbers) {
            numSet.add(strNum);
        }
        System.out.println(numSet);
        String[] nums = "3 5 4 7 5 17 29 17 4 6 56 72 6".split(" ");
        Integer[] actual_nums = new Integer[nums.length];
        for (int i = 0; i < nums.length; i++) {
            actual_nums[i] = Integer.parseInt(nums[i]);
        }
        Set<Integer> numNumSet = new TreeSet<Integer>();
        for (Integer num : actual_nums) {
            numNumSet.add(num);
        }
        System.out.println(numNumSet);
    }
}