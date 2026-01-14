import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
public class Runner {
    public static void main(String[] args){
        ArrayList<String> list = new ArrayList<>();
        try {
            Scanner textSc = new Scanner(new File("names.txt"));
            while (textSc.hasNext()){
                list.add(textSc.next());
            }
        }
        catch (Exception e) {
            System.out.println("Dont care");
        }
        Scanner sc = new Scanner(System.in);
        boolean run = true;
        while (run){
            System.out.println(" what do you want do do\n 1) Display the list \n 2) Scramble the list \n 3) Apply the BubbleSort (O(n^2)) to the list \n 4) QuickSort (O(n log n) to the list) \n 5) Quit");
            int num = sc.nextInt();
            if (num == 1){
                System.out.println(list);
            }
            else if (num == 2){
                // Sort.scramble(list);
            }
            else if (num == 3){
                // Sort.bubbleSort(list);
            }
            else if (num == 4){
                Sort.quickSort(list,0,list.size()-1);
            }
            else{
                run = false;
            }
        }
    }
    
}
