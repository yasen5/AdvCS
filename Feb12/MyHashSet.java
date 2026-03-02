
package Feb12;

public class MyHashSet<E> {
    private Object[] hashArray;
    private int size;

    public MyHashSet() {
        hashArray = new Object[128 * 180];
        size = 0;
    }

    public boolean add(E obj) {
        if (contains(obj)) {
            return false;
        } else {
            hashArray[obj.hashCode()] = obj;
            size++;
        }
        return true;
    }

    public void clear() {
        size = 0;
        hashArray = new Object[128];
    }

    public boolean contains(Object obj) {
        if (hashArray[obj.hashCode()] != null && obj.equals(hashArray[obj.hashCode()])) {
            return true;
        }
        return false;
    }

    public E get(int hashcode) {
        return (E) hashArray[hashcode];
    }

    public E get(E elem) {
        return (E) hashArray[elem.hashCode()];
    }

    public boolean remove(Object obj) {
        if (contains(obj)) {
            hashArray[obj.hashCode()] = null;
            size--;
            return true;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public MyDLList<E> toDLList() {
        MyDLList<E> dll = new MyDLList<E>();
        for (int i = 0; i < hashArray.length; i++) {
            if (hashArray[i] != null) {
                dll.add((E) hashArray[i]);
            }
        }
        return dll;
    }
}