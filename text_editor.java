import javax.swing.*;
import java.awt.*;

public class text_editor {
    public static void main(String [] args) {

        MyFrame mainFrame = new MyFrame(1000,500, "Pratt_Text_Editor", JFrame.EXIT_ON_CLOSE, false, Color.white);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setLayout(new FlowLayout());

        ImageIcon appIcon = new ImageIcon("icons8-text-editor-64.png");
        JTextArea textArea = new JTextArea();
        textArea.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        textArea.setBackground(mainFrame.getBackground());
        textArea.setForeground(Color.BLACK);
        textArea.setBackground(Color.white);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        MenuBar menu = new MenuBar(textArea);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(980, 480));
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        mainFrame.setIconImage(appIcon.getImage());
        mainFrame.setJMenuBar(menu.menuBar);
        mainFrame.add(scrollPane);
        mainFrame.setVisible(true);
    }
}
