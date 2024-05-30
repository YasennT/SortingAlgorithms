package bg.smg;

import java.util.ArrayList;
import java.util.List;

public class HeapSort<E extends Comparable<E>> {
        private final ArrayList<E> arr;

        public HeapSort() {
            arr = new ArrayList<>();
        }


        public void add(E newObject) {
            arr.add(newObject);
            int currentIndex = arr.size() - 1;

            while (currentIndex > 0) {
                int parentIndex = (currentIndex - 1) / 2;
                if (arr.get(currentIndex).compareTo(arr.get(parentIndex)) > 0) {
                    E temp = arr.get(currentIndex);
                    arr.set(currentIndex, arr.get(parentIndex));
                    arr.set(parentIndex, temp);
                } else break;

                currentIndex = parentIndex;
            }
        }

        public E remove() {
            if (arr.isEmpty()) return null;

            E removedObject = arr.get(0);
            arr.set(0, arr.get(arr.size() - 1));
            arr.remove(arr.size() - 1);

            int currentIndex = 0;
            while (currentIndex < arr.size()) {
                int leftChildIndex = 2 * currentIndex + 1;
                int rightChildIndex = 2 * currentIndex + 2;

                if (leftChildIndex >= arr.size()) break;
                int maxIndex = leftChildIndex;
                if (rightChildIndex < arr.size()) {
                    if (arr.get(maxIndex).compareTo(arr.get(rightChildIndex)) < 0) {
                        maxIndex = rightChildIndex;
                    }
                }
                if (arr.get(currentIndex).compareTo(arr.get(maxIndex)) < 0) {
                    E temp = arr.get(maxIndex);
                    arr.set(maxIndex, arr.get(currentIndex));
                    arr.set(currentIndex, temp);
                    currentIndex = maxIndex;
                } else break;
            }

            return removedObject;
        }

}
