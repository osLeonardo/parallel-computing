import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window {
    private JPanel mainPanel;
    private JButton findButton;
    private JTextField nomeTextField;
    private JComboBox datasetComboBox;
    private JLabel nomeLabel;
    private JLabel datasetLabel;
    private JTextArea logTextArea;
    private JScrollPane logScrollPane;

    public Window() {
        datasetComboBox.addItem("Dataset G");
        datasetComboBox.addItem("Dataset P");

        findButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FindersCompare.execute(getDataset(), getNome(), logTextArea);
            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public String getNome() {
        return nomeTextField.getText();
    }

    public String getDataset() {
        if (datasetComboBox.getSelectedIndex() == 0) {
            return "./datasets/dataset_g";
        } else {
            return "./datasets/dataset_p";
        }
    }

    public void appendLog(String message) {
        logTextArea.append(message + "\n");
    }
}
