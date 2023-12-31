import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.FileChooserUI;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.security.Key;
import java.util.Scanner;

public class MenuBar {
    JMenuBar menuBar;
    MenuBar(JTextArea textArea) {
        menuBar = new JMenuBar();

        JMenu file = new JMenu();
        file.setText("File");
        file.setMnemonic(KeyEvent.VK_F);

        JMenuItem open = new JMenuItem();
        open.setText("Open");
        open.setMnemonic(KeyEvent.VK_O);
        open.addActionListener(e -> openFunc(textArea));

        JMenuItem save = new JMenuItem();
        save.setText("Save");
        save.setMnemonic(KeyEvent.VK_S);
        save.addActionListener(e -> saveFunc(textArea));

        JMenuItem exit = new JMenuItem();
        exit.setText("Exit");
        exit.setMnemonic(KeyEvent.VK_E);
        exit.addActionListener(e -> exitFunc(textArea));

        file.add(open);
        file.add(save);
        file.add(exit);



        JMenu edit = new JMenu();
        edit.setText("Edit");
        edit.setMnemonic(KeyEvent.VK_E);

        JMenuItem font = new JMenuItem();
        font.setText("Font");
        font.setMnemonic(KeyEvent.VK_F);
        font.addActionListener(e -> fontFunc(textArea));

        JMenuItem color = new JMenuItem();
        color.setText("Color");
        color.setMnemonic(KeyEvent.VK_C);
        color.addActionListener(e -> colorFunc(textArea));

        edit.add(font);
        edit.add(color);

        menuBar.add(file);
        menuBar.add(edit);
    }

    public void openFunc(JTextArea textArea) {

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("C:\\"));
        FileNameExtensionFilter fileNameExtensionFilter= new FileNameExtensionFilter("Text Files", "txt");
        fileChooser.setFileFilter(fileNameExtensionFilter);
        int response = fileChooser.showOpenDialog(null);
        if(response == JFileChooser.APPROVE_OPTION) {
            textArea.setText("");
            File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
            Scanner fileIn = null;
            try {
                fileIn = new Scanner(file);
                if(file.isFile()) {
                    while (fileIn.hasNextLine()) {
                        textArea.append(fileIn.nextLine() + "\n");
                    }
                }
            }
            catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            finally {
                fileIn.close();
            }
        }
    }

    public void saveFunc(JTextArea textArea) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("C:\\"));
        int response = fileChooser.showSaveDialog(null);
        if(response == JFileChooser.APPROVE_OPTION) {
            File file;
            PrintWriter fileOut = null;
            file = new File(fileChooser.getSelectedFile().getAbsolutePath());
            try {
                fileOut = new PrintWriter(file);
                fileOut.println(textArea.getText());
            }
            catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            finally {
                fileOut.close();
            }
        }
    }

    public void exitFunc(JTextArea textArea) {
        System.exit(0);
    }


    public void fontFunc(JTextArea textArea) {
        MyFrame newFrame = new MyFrame(500, 400, "Font", JFrame.DISPOSE_ON_CLOSE, false, Color.WHITE);
        newFrame.setLayout(null);


        JPanel lPanel = new JPanel();
        lPanel.setLayout(null);
        lPanel.setBounds(0, 0, 250, 400);
        JPanel rPanel = new JPanel();
        rPanel.setLayout(null);
        rPanel.setBounds(250, 0, 250, 400);

        JLabel fontLabel = new JLabel("Font Type: ");
        fontLabel.setBounds(30, 40, 125, 20);
        fontLabel.setFont(new Font("Arial", Font.BOLD, 16));
        JLabel fontStyleLabel = new JLabel("Font Style: ");
        fontStyleLabel.setBounds(30, 140, 125, 20);
        fontStyleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        JLabel fontSizeLabel = new JLabel("Font Size: ");
        fontSizeLabel.setBounds(30, 240, 125, 20);
        fontSizeLabel.setFont(new Font("Arial", Font.BOLD, 16));


        String [] fontFamily = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        String [] fontType = {"Plain", "Bold", "Italic"};
        JSpinner fontSize = new JSpinner();

        fontSize.setValue(textArea.getFont().getSize());
        JComboBox<String> FFamily = new JComboBox<>(fontFamily);
        JComboBox<String> FType = new JComboBox<>(fontType);
        FFamily.setSelectedItem(textArea.getFont().getFamily());
        FType.setSelectedIndex(textArea.getFont().getStyle());

        FFamily.setBounds(0, 40, 200, 20);
        FType.setBounds(0, 140, 200, 20);
        fontSize.setBounds(75, 240, 50, 20);

        JButton save = new JButton("Save");
        save.setFont(new Font("Arial", Font.PLAIN, 12));
        save.setBounds(20, 310, 70, 20);
        save.setFocusable(false);
        save.addActionListener(e -> {
            textArea.setFont(new Font((String) FFamily.getSelectedItem(), FType.getSelectedIndex(), (int)fontSize.getValue()));
            newFrame.dispose();
        });

        JButton cancel = new JButton("Cancel");
        cancel.setFont(new Font("Arial", Font.PLAIN, 12));
        cancel.setBounds(130, 310, 80, 20);
        cancel.setFocusable(false);
        cancel.addActionListener(e -> {
            newFrame.dispose();
        });

        lPanel.add(fontLabel);
        lPanel.add(fontStyleLabel);
        lPanel.add(fontSizeLabel);
        lPanel.add(save);

        rPanel.add(FFamily);
        rPanel.add(FType);
        rPanel.add(fontSize);
        rPanel.add(cancel);

        newFrame.add(lPanel);
        newFrame.add(rPanel);

        newFrame.setVisible(true);
    }

    public void colorFunc(JTextArea textArea) {
        JColorChooser colorChooser = new JColorChooser();

        Color color = JColorChooser.showDialog(null, "Pick a font Color", textArea.getForeground());
        textArea.setForeground(color);
    }
}
