import java.awt.*;
import java.awt.event.*;
import java.awt.Component;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * <h1>Main Window</h1>
 * 
 * The class for SongBird's main startup window, from which SongBird's various 
 * functions can be accessed
 * 
 * @author Devon Hubert
 * @version 3.0
 */
public class MainWindow extends Frame implements WindowListener, ActionListener
{
    Button b;
    TextField text = new TextField(25);
    
    public MainWindow(String title) {
        super(title);
        setLayout(new FlowLayout());
        addWindowListener(this);
        add(text);
        text.setBackground(Color.GRAY);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("Helvetica", Font.ITALIC, 30));
        text.setEditable(false);
        text.setText("        Welcome to SongBird!");
        
        Button b = new Button("Write a New Song");
        add(b);
        b.addActionListener(this);
        B2 b2 = new B2();
        
        Button c = new Button("Find a Scale");
        add(c);
        c.addActionListener(b2);
        B3 b3 = new B3();
        
        Button d = new Button("Find a Chord");
        add(d);
        d.addActionListener(b3);
        B4 b4 = new B4();
        
        /*
        JLabel picture = new JLabel(new ImageIcon("songBird.png"));
        add(picture);
        */
        
        JLabel label = new JLabel("Developed by Devon Hubert");
        add(label);
        label.setFont(new Font("Helvetica", Font.PLAIN, 18));
    }
    
    /**
     * Handles the pressing of the "Song Generator" button (opens a new window)
     * 
     * @param e The ActionEvent triggered by pressing the "Song Generator" button
     */
    public void actionPerformed(ActionEvent e) {
        songGenerator myWindow = new songGenerator("SongBird: Song Generator");
        myWindow.setSize(500,500);
        myWindow.setResizable(false);
        myWindow.setVisible(true);
    }
    
    /**
     * Handles the closing of the Main window, re-setting settings to
     * their defaults
     * 
     * @param e The WindowEvent triggered by closing the Main window
     */
    public void windowClosing(WindowEvent e) {
        dispose();
        System.exit(0);
    }

    public void windowOpened(WindowEvent e) {}
    public void windowActivated(WindowEvent e) {}
    public void windowIconified(WindowEvent e) {}
    public void windowDeiconified(WindowEvent e) {}
    public void windowDeactivated(WindowEvent e) {}
    public void windowClosed(WindowEvent e) {}
}

/**
 * The ActionListener class that handles the "Scale Finder" button
 */
class B2 implements ActionListener {
    /**
     * Handles the pressing of the "Scale Finder" button (opens a new window)
     * 
     * @param e The ActionEvent triggered by pressing the "Scale Finder" button
     */
    public void actionPerformed(ActionEvent e) {
        ScaleFinder myWindow = new ScaleFinder("SongBird: Scale Finder");
        myWindow.setSize(500,400);
        myWindow.setResizable(false);
        myWindow.setVisible(true);
    }
}

/**
 * The ActionListener class that handles the "Chord Finder" button
 */
class B3 implements ActionListener {
    /**
     * Handles the pressing of the "Chord Finder" button (opens a new window)
     * 
     * @param e The ActionEvent triggered by pressing the "Chord Finder" button
     */
    public void actionPerformed(ActionEvent e) {
        ChordPrinter myWindow = new ChordPrinter("SongBird: Chord Finder");
        myWindow.setSize(500,400);
        myWindow.setResizable(false);
        myWindow.setVisible(true);
    }
}