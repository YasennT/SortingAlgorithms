package bg.smg;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ElementSorter extends JFrame {

    private JComboBox<String> sortComboBox;
    private JButton sortButton;
    private JPanel elementPanel;
    private List<Element> elements;

    public ElementSorter() {
        setTitle("Element Sorter");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        elements = new ArrayList<>();
        elements.add(new Element("Image1.jpg", 100));
        elements.add(new Element("Image2.jpg", 70));
        elements.add(new Element("Image3.jpg", 120));
        elements.add(new Element("Image4.jpg", 50));
        elements.add(new Element("Image5.jpg", 90));
        elements.add(new Element("Image6.jpg", 110));
        elements.add(new Element("Image7.jpg", 80));

        sortComboBox = new JComboBox<>(new String[]{"Bubble sort", "Selection sort", "Quick sort", "Merge sort", "Heap sort", "Insertion sort"});
        sortButton = new JButton("Sort");
        sortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedSort = (String) sortComboBox.getSelectedItem();
                switch (selectedSort) {
                    case "Bubble sort":
                        Sorts.bubbleSort(elements);
                        break;
                    case "Selection sort":
                        selectionSort(elements);
                        break;
                    case "Insertion sort":
                        inser
                }
            }
        });

        JPanel controlPanel = new JPanel();
        controlPanel.add(sortComboBox);
        controlPanel.add(sortButton);

        elementPanel = new JPanel();
        elementPanel.setLayout(new FlowLayout());

        displayElements();

        add(controlPanel, BorderLayout.NORTH);
        add(elementPanel, BorderLayout.CENTER);
    }

    private void displayElements() {
        elementPanel.removeAll();
        for (Element element : elements) {
            JLabel label = new JLabel(new ImageIcon(element.getImage()));
            label.setPreferredSize(new Dimension(element.getSize(), element.getSize()));
            elementPanel.add(label);
        }
        elementPanel.revalidate();
        elementPanel.repaint();
    }
}