import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window {
    private JPanel mainPanel;
    private JButton findButton;
    private JTextField nomeTextField;
    private JComboBox datasetComboBox;
    private JComboBox metodoComboBox;
    private JLabel nomeLabel;
    private JLabel datasetLabel;
    private JLabel metodoLabel;

    public Window() {
        datasetComboBox.addItem("Dataset G");
        datasetComboBox.addItem("Dataset P");

        metodoComboBox.addItem("Busca Linear");
        metodoComboBox.addItem("Busca Reverse");
        metodoComboBox.addItem("Busca Linear com Thread");
        metodoComboBox.addItem("Busca Reverse com Thread");

        findButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (metodoComboBox.getSelectedIndex() == 0) {
                    FindersCompare.execute(getDataset(), getNome());
                }
                else if (metodoComboBox.getSelectedIndex() == 1) {
                    FindersCompare.execute(getDataset(), getNome());
                }
                else {
                    FindersCompare.execute(getDataset(), getNome());

                }
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
}
