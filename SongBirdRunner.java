
/**
 * <h1>SongBird Runner</h1>
 * 
 * The class that runs the SongBird program, setting up the main
 * "SongBird" window
 * 
 * @author Devon Hubert
 * @version 3.0
 */
public class SongBirdRunner
{
    public static void main(String[] args) {
        MainWindow mainWindow = new MainWindow("Planit");
        mainWindow.setSize(600, 135);
        mainWindow.setResizable(false);
        mainWindow.setVisible(true);
    }
}
