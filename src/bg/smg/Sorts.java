//package bg.smg;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class Sorts {
//
//    private void bubbleSort(List<Element> list) {
//        new Thread(() -> {
//            for (int i = 0; i < elements.size(); i++) {
//        for (int j = 0; j < elements.size() - i - 1; j++) {
//            if (elements.get(j).getWidth() > elements.get(j + 1).getWidth()) {
//                swap(elements, elements.get(j).getWidth(), elements.get(j + 1).getWidth());
//            }
//        }
//    }
//            sortButton.setEnabled(true);
//        }).start();
//    }
//
//    public static void selectionSort(List<Element> arr) {
//        for (int i = 0; i < arr.size() - 1; i++) {
//            int index = i;
//            for (int j = i + 1; j < arr.size(); j++) {
//                if (arr.get(j).getSize() < arr.get(index).getSize()) {
//
//                    index = j;
//                }
//            }
//            Main.swap(index, i);
//        }
//    }
//
//    public static void insertionSort(List<Element> arr) {
//        for (int i = 1; i < arr.size(); i++) {
//            Element current = arr.get(i);
//            int j;
//            for (j = i - 1; j >= 0 && arr.get(j).getSize() > current.getSize(); --j) arr.set(j + 1, arr.get(j));
//            arr.set(j + 1, current);
//        }
//    }
//
//    public static void merge(List<Element> arr1, List<Element> arr2, List<Element> temp) {
//        int curr1 = 0, curr2 = 0, curr3 = 0;
//
//        while (curr1 < arr1.size() && curr2 < arr2.size()) {
//            if (arr1.get(curr1).getSize() <= arr2.get(curr2).getSize()) temp.set(curr3++, arr1.get(curr1));
//            else temp.set(curr3++, arr2.get(curr2));
//        }
//        while (curr1 < arr1.size()) temp.set(curr3++, arr1.get(curr1++));
//        while (curr2 < arr2.size()) temp.set(curr3++, arr2.get(curr2++));
//    }
//
//    public static void mergeSort(List<Element> arr) {
//        if (arr.size() > 1) {
//            List<Element> firstH = new ArrayList<>(arr.subList(0, arr.size() / 2));
//            System.arraycopy(arr, 0, firstH, 0, arr.size());
//            mergeSort(firstH);
//
//            int secondHalfLength = arr.size() - arr.size() / 2;
//            List<Element> secondH = new ArrayList<>(arr.subList(arr.size() / 2, secondHalfLength));
//            System.arraycopy(arr, arr.size() / 2, secondH, 0, secondHalfLength);
//            mergeSort(secondH);
//            merge(firstH, secondH, arr);
//        }
//    }
//
//    public static int partition(List<Element> arr, int first, int last) {
//        Element pivot = arr.get(first);
//        int i = first - 1;
//
//        for (int j = first; j < last; j++) {
//            if (arr.get(j).getSize() <= pivot.getSize()) {
//                i++;
//
//                Element temp = arr.get(i);
//                arr.set(i, arr.get(j));
//                arr.set(j, temp);
//            }
//        }
//        swap(arr.get(i + 1).getSize(), last);
//
//        return i + 1;
//    }
//
//    public static void quickSort(List<Element> arr, int first, int last) {
//        if (last > first) {
//            int pivotIndex = partition(arr, first, last);
//            quickSort(arr, first, pivotIndex - 1);
//            quickSort(arr, pivotIndex + 1, last);
//        }
//    }
//}
//
