package Aug28;

import java.util.ArrayList;
import java.util.List;

public class Warmup {
    public static void main(String[] args) {
        ArrayList<MyItem<String, Double>> itemList = new ArrayList<>(List.of(new MyItem<>("Banana", 1.0), new MyItem<>("Pear", 2.0), new MyItem<>("Stringidk", 3.0)));
        for (@SuppressWarnings("rawtypes") MyItem item : itemList) {
            System.out.println(item);
        }
    }
}

class MyItem<K, V> {
    K key;
    V value;

    public MyItem(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public String toString() {
        return key + " : " + value;
    }
}
