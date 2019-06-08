import javax.swing.JFrame;

public class Game {

	public static void main(String[] args) {
		JFrame myFrame = new TrisGraphics();
		myFrame.setTitle("Tris");
		myFrame.setResizable(false);
		myFrame.setLocationRelativeTo(null);
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setVisible(true);
	}

}
