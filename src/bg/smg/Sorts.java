package bg.smg;

import java.util.ArrayList;

public class Sorts {

    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    Main.swap(arr[j], arr[j + 1]);
                }
            }
        }
    }

    public static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int min = arr[i];
            int index = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (min > arr[j]) {
                    min = arr[j];
                    index = j;
                }
            }
            if (index != i) {
                arr[index] = arr[i];
                arr[i] = min;
            }
        }
    }

    public static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int current = arr[i];
            int j;
            for (j = i - 1; j >= 0 && arr[j] > current; j--) arr[j + 1] = arr[j];
            arr[j + 1] = current;
        }
    }

    public static void merge(int[] arr1, int[] arr2, int[] temp) {
        int curr1 = 0, curr2 = 0, curr3 = 0;

        while (curr1 < arr1.length && curr2 < arr2.length) {
            if (arr1[curr1] < arr2[curr2]) temp[curr3++] = arr1[curr1];
            else temp[curr3++] = arr2[curr2];
        }
        while (curr1 < arr1.length) temp[curr3++] = arr1[curr1++];
        while (curr2 < arr2.length) temp[curr3++] = arr2[curr2++];
    }

    public static void mergeSort(int[] arr) {
        if (arr.length > 1) {
            int[] firstH = new int[arr.length / 2];
            System.arraycopy(arr, 0, firstH, 0, arr.length);
            mergeSort(firstH);

            int secondHalfLength = arr.length - arr.length / 2;
            int[] secondH = new int[secondHalfLength];
            System.arraycopy(arr, arr.length / 2, secondH, 0, secondHalfLength);
            mergeSort(secondH);
            merge(firstH, secondH, arr);
        }
    }

    public static int partition(int[] arr, int first, int last) {
        int pivot = arr[first];
        int low = first + 1, high = last;
        while (high > low) {
            while (low <= high && arr[low] <= pivot) low++;
            while (low <= high && arr[high] > pivot) high--;

            if (high > low) Main.swap(arr[high], arr[low]);
        }
        while (high > first && arr[high] >= pivot) high--;

        if (pivot > arr[high]) {
            arr[first] = arr[high];
            arr[high] = pivot;
            return high;
        } else return first;
    }

    public static void quickSort(int[] arr, int first, int last) {
        if (last > first) {
            int pivotIndex = partition(arr, first, last);
            quickSort(arr, first, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, last);
        }
    }

    public <E extends Comparable<E>> void heapSort(E[] arr) {
        Heap<E> heap = new Heap<>();

        for (int i = 0; i < arr.length; i++) heap.add(arr[i]);

        for (int i = arr.length - 1; i >= 0; i--) arr[i] = heap.remove();
    }

    public class Heap<E extends Comparable<E>> {
        private ArrayList<E> arr;

        public Heap() {
            arr = new ArrayList<>();
        }

        public Heap(E[] objects) {
            this();
            for (E object : objects) add(object);
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
            if (arr.size() == 0) return null;

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

        public int getSize() {
            return arr.size();
        }
    }
}
