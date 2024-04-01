package bg.smg;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class SortingVisualizer extends JFrame {

    private final List<Element> elements;
    private final JButton sortButton;

    public SortingVisualizer() {
        setTitle("Sorting Visualizer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1200, 400));

        elements = new ArrayList<>();
        elements.add(new Element("resources/hd.jpg", 100, 100));
        elements.add(new Element("resources/hd.jpg", 70, 70));
        elements.add(new Element("resources/hd.jpg", 120, 120));
        elements.add(new Element("resources/hd.jpg", 50, 50));
        elements.add(new Element("resources/hd.jpg", 25, 25));
        elements.add(new Element("resources/hd.jpg", 140, 140));
        elements.add(new Element("resources/hd.jpg", 80, 80));

        JComboBox<String> algorithmComboBox;
        algorithmComboBox = new JComboBox<>(new String[]{"Bubble Sort", "Selection Sort", "Insertion Sort", "Merge Sort", "Quick Sort", "Heap Sort"});
        sortButton = new JButton("Sort");
        JButton resetButton = new JButton("Reset");

        JPanel drawingPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                int x = 50;
                for (Element element : elements) {
                    g.drawImage(element.getImage(), x, 50, element.getHeight(), element.getHeight(), null);
                    g.setColor(Color.BLACK);
                    x += 150;
                }
            }
        };

        setLayout(new BorderLayout());
        JPanel controlPanel = new JPanel();
        controlPanel.add(new JLabel("Select Algorithm:"));
        controlPanel.add(algorithmComboBox);
        controlPanel.add(sortButton);
        controlPanel.add(resetButton);
        add(controlPanel, BorderLayout.NORTH);
        add(drawingPanel, BorderLayout.CENTER);

        sortButton.addActionListener(e -> {
            sortButton.setEnabled(false);
            String selectedAlgorithm = (String) algorithmComboBox.getSelectedItem();
            switch (selectedAlgorithm) {
                case "Bubble Sort":
                    bubbleSort(elements);
                    break;
                case "Selection Sort":
                    selectionSort(elements);
                    break;
                case "Insertion Sort":
                    insertionSort(elements);
                    break;
                case "Merge Sort":
                    mergeSort(elements);
                    break;
                case "Quick Sort":
                    quickSort(elements, 0, elements.size()-1);
                    break;
                case "Heap Sort":
                    heapSort(elements);
                    break;
            }
        });
        resetButton.addActionListener(e -> resetElements());

        pack();
    }

    // BUBBLE SORT
    private void bubbleSort(List<Element> list) {
        new Thread(() -> {
            int n = list.size();
            for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j < n - i - 1; j++) {
                    if (list.get(j).getWidth() > list.get(j + 1).getWidth()) {
                        swap(list, j, j + 1);
                        repaint();
                        sleep();
                    }
                }
            }

            sortButton.setEnabled(true);
        }).start();
    }

    // SELECTION SORT
    private void selectionSort(List<Element> list) {
        new Thread(() -> {
            for (int i = 0; i < list.size() - 1; i++) {
                int minIndex = i;
                for (int j = i + 1; j < list.size(); j++) {
                    if (list.get(j).getWidth() < list.get(minIndex).getWidth()) {
                        minIndex = j;
                    }
                }
                swap(list, i, minIndex);
                repaint();
                sleep();
            }
            sortButton.setEnabled(true);
        }).start();
    }

    // INSERTION SORT
    private void insertionSort(List<Element> list) {
        new Thread(() -> {
            for (int i = 1; i < list.size(); ++i) {
                Element curr = list.get(i);
                int j;
                for (j = i - 1; j >= 0 && list.get(j).getWidth() > curr.getWidth(); --j) list.set(j + 1, list.get(j));
                list.set(j + 1, curr);
                list.set(j + 1, curr);
                repaint();
                sleep();
            }
            sortButton.setEnabled(true);
        }).start();
    }

    // MERGE SORT
    private void mergeSort(List<Element> list) {
        new Thread(() -> {
            if (list.size() > 1) {
                int mid = list.size() / 2;
                List<Element> firstHalf = new ArrayList<>(list.subList(0, mid));
                List<Element> secondHalf = new ArrayList<>(list.subList(mid, list.size()));

                mergeSort(firstHalf);
                mergeSort(secondHalf);
                merge(firstHalf, secondHalf, list);
                try {
                    Thread.sleep(150); // Adjust the delay time as needed (in milliseconds)
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                repaint();
            }
            sortButton.setEnabled(true);
        }).start();
    }

    public void merge(List<Element> arr1, List<Element> arr2, List<Element> temp) {
        int i = 0, j = 0, k = 0;
        List<Element> sortedList = new ArrayList<>(arr1.size() + arr2.size());

        while (i < arr1.size() && j < arr2.size()) {
            if (arr1.get(i).getWidth() <= arr2.get(j).getWidth()) sortedList.add(arr1.get(i++));
            else sortedList.add(arr2.get(j++));
        }

        while (i < arr1.size()) sortedList.add(arr1.get(i++));
        while (j < arr2.size()) sortedList.add(arr2.get(j++));
        for (Element element : sortedList) temp.set(k++, element);

        repaint();
        try {
            Thread.sleep(150);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // QUICK SORT
    private void quickSort(List<Element> list, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(list, low, high);

            quickSort(list, low, pivotIndex - 1);
            quickSort(list, pivotIndex + 1, high);
            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            repaint();
        } else sortButton.setEnabled(true);
    }
    private int partition(List<Element> list, int low, int high) {
        int pivot = list.get(high).getWidth();
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (list.get(j).getWidth() < pivot) {
                i++;
                swap(list, i, j);
                try {
                    Thread.sleep(150);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                repaint();
            }
        }
        swap(list, i + 1, high);
        return i + 1;
    }

    // HEAP SORT
    private void heapSort(List<Element> list) {
        new Thread(() -> {
            HeapSort.Heap<Element> heap = new HeapSort.Heap<>();
            for (Element element : list) {
                heap.add(element);
                repaint();
                try {
                    Thread.sleep(500); // Adjust delay as needed
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            for (int i = list.size() - 1; i >= 0; i--) {
                list.set(i, heap.remove());
                repaint();
                try {
                    Thread.sleep(500); // Adjust delay as needed
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            sortButton.setEnabled(true);
        }).start();
    }
    private void resetElements() {
        elements.clear();
        elements.add(new Element("resources/hd.jpg", 100, 100));
        elements.add(new Element("resources/hd.jpg", 70, 70));
        elements.add(new Element("resources/hd.jpg", 120, 120));
        elements.add(new Element("resources/hd.jpg", 50, 50));
        elements.add(new Element("resources/hd.jpg", 25, 25));
        elements.add(new Element("resources/hd.jpg", 140, 140));
        elements.add(new Element("resources/hd.jpg", 80, 80));
        repaint();
    }
    private void swap(List<Element> list, int i, int j) {
        Element temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

    private void sleep() {
        try {
            int delay = 150;
            Thread.sleep(delay);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    static class Element implements Comparable<Element> {
        private Image image;
        private final int width;
        private final int height;
        public Element(String imagePath, int width, int height) {
            try {
              this.image = ImageIO.read(new File(imagePath));
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.width = width;
            this.height = height;
        }
        public Image getImage() {
            return image;
        }
        public int getWidth() {
            return width;
        }
        public int getHeight() {
                return height;
            }
        @Override
        public int compareTo(Element o) {
            return Integer.compare(this.width, o.width);
        }
    }

    public static void main(String[] args) {
        new SortingVisualizer().setVisible(true);
    }
}
