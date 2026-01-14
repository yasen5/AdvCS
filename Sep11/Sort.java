import java.util.ArrayList;

public class Sort {
  private static int calls = 0, calls2 = 0, calls3 = 0;

  public static void scramble(ArrayList<String> arr) {
    for (int i = 0; i < arr.size(); i++) {
        int randomIndex = (int) (Math.random() * arr.size());
        String temp = arr.get(i);
        arr.set(i, arr.get(randomIndex));
        arr.set(randomIndex, temp);
    }
  }

  public static void bubbleSort(ArrayList<String> arr) {
    for (int i = 0; i < arr.size(); i ++){
        for (int j = 1;j < arr.size()-i; j ++){
            if (arr.get(j).compareToIgnoreCase(arr.get(j-1)) < 0){
                String save = arr.get(j);
                arr.set(j,arr.get(j-1));
                arr.set(j-1,save);
            }
        }
    }
  }

  // public static void quickSort(ArrayList<String> arr, int minIndex, int maxIndex) {
  //     if (maxIndex == minIndex) {
  //         return;
  //     }
  //     int middleIndex = (minIndex + maxIndex)/2;
  //     String middleValue = arr.get(middleIndex);
  //     int lowerIndex = minIndex;
  //     int higherIndex = maxIndex;
  //     while (lowerIndex <= higherIndex) {
  //         while (arr.get(lowerIndex).compareTo(middleValue) > 0 && lowerIndex < arr.size()) {
  //             lowerIndex++;
  //         }
  //         while (arr.get(higherIndex).compareTo(middleValue) < 0 && higherIndex >= 0) {
  //             higherIndex--;
  //         }
  //         if (lowerIndex > higherIndex) {
  //             break;
  //         }
  //         String temp = arr.get(lowerIndex);
  //         arr.set(lowerIndex, arr.get(higherIndex));
  //         arr.set(higherIndex, temp);
  //     }
  //     quickSort(arr, minIndex, maxIndex);
  // }

  public static void quickSort(ArrayList<String> stuff, int low, int high) {
    if (low < high) { //Base Case
      int spot = partition(stuff, low, high);
      quickSort(stuff, low, spot);
      quickSort(stuff, spot+1, high);
    }
  }  
    
  // public static int partition(ArrayList<String> stuff, int low, int high) {
  //     String pivot = stuff.get(low);  // pivot = first element
  //     int left = low - 1;
  //     int right = high + 1;

  //     while (true) {
  //         do {
  //             left++;
  //         } while (stuff.get(left).compareTo(pivot) < 0);

  //         do {
  //             right--;
  //         } while (stuff.get(right).compareTo(pivot) > 0);

  //         if (left >= right) {
  //             return right;
  //         }

  //         String temp = stuff.get(left);
  //         stuff.set(left, stuff.get(right));
  //         stuff.set(right, temp);
  //     }
  // }
  
  public static int partition(ArrayList<String> stuff, int low, int high) {
    String pivot = stuff.get(low); 
    int bot = low; 
    int top = high;		
    while(bot < top) {
      while (stuff.get(top).compareTo(pivot) > 0) {
        top--; 
      }	
      while (stuff.get(bot).compareTo(pivot) < 0) {
        bot++;
      }
      if(bot >= top) {
        return top;
      }
      String temp = stuff.get(bot);
      stuff.set(bot,stuff.get(top));
      stuff.set(top,temp);
    }
    return 0;
  }
}
