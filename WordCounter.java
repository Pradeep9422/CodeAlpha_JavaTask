import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WordCounter extends JFrame implements ActionListener {
    private JTextArea textArea;
    private JButton countButton;
    private JLabel resultLabel;

    public WordCounter() {
        // Frame settings
        setTitle("Word Counter");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Text Area
        textArea = new JTextArea("Enter your text here...");
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        // Panel for button and result
        JPanel panel = new JPanel();
        countButton = new JButton("Count Words");
        countButton.addActionListener(this);
        resultLabel = new JLabel("Word Count: 0");

        panel.add(countButton);
        panel.add(resultLabel);
        add(panel, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == countButton) {
            String text = textArea.getText().trim();
            String[] words = text.split("\\s+"); // Splitting based on spaces
            int wordCount = text.isEmpty() ? 0 : words.length;
            resultLabel.setText("Word Count: " + wordCount);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            WordCounter frame = new WordCounter();
            frame.setVisible(true);
        });
    }
}
