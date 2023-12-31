import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {
    MyFrame() {

    }
    MyFrame(int width, int height, String Title, int defaultCloseOperation, boolean Resizable, Color color) {
        this.setSize(new Dimension(width, height));
        this.setDefaultCloseOperation(defaultCloseOperation);
        this.setResizable(Resizable);
        this.setTitle(Title);
        this.setBackground(color);
    }
}
