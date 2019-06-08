import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class TrisGraphics extends JFrame {

	private static final long serialVersionUID = 1L;
	private final static int FRAME_WIDTH = 500;
	private final static int FRAME_HEIGHT = 500;
	private File salvataggio = new File("D:\\Workspace Eclipse EE\\TRIS\\tris_save.bin");
	private Tris t = null;
	
	public TrisGraphics() {
		JMenuBar bar = new JMenuBar();
		setJMenuBar(bar);
		bar.add(createMenu());
		setLayout(new BorderLayout());
		setSize(FRAME_WIDTH, FRAME_HEIGHT);		
		t = new Tris();
		add(t.getPanel(), BorderLayout.CENTER);
		repaint();
	}
	
	public TrisGraphics(Tris tr) {
		JMenuBar bar = new JMenuBar();
		setJMenuBar(bar);
		bar.add(createMenu());
		setLayout(new BorderLayout());
		setSize(FRAME_WIDTH, FRAME_HEIGHT);		
		t = new Tris(tr.getTrisLogic());
		t.setButtons();
		t.addButtonsPanel();
		add(t.getPanel(), BorderLayout.CENTER);
		repaint();
	}
	
	private JMenu createMenu() {
		JMenu menu = new JMenu("File");
		menu.add(createNewGame());
		menu.add(createSaveGame());
		menu.add(createLoadGame());
		return menu;
	}
	
	private JMenuItem createNewGame() {
		JMenuItem newGame = new JMenuItem("Nuova partita");
		newGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				JFrame myFrame = new TrisGraphics();
				myFrame.setTitle("Tris");
				myFrame.setResizable(false);
				myFrame.setLocationRelativeTo(null);
				myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				myFrame.setVisible(true);
			}			
		});	
		return newGame;
	}
	
	private JMenuItem createSaveGame() {
		JMenuItem saveGame = new JMenuItem("Salva partita");
		saveGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(salvataggio.exists()) {
					salvataggio.delete();
				}	
				try {
					FileOutputStream output = new FileOutputStream(salvataggio);
					ObjectOutputStream out = new ObjectOutputStream(output);
					out.writeObject(t);
					out.close();
					JOptionPane.showMessageDialog(saveGame, "Gioco salvato");
				} catch (IOException ex) {
					JOptionPane.showMessageDialog(saveGame, "ERRORE!!! SALVATAGGIO NON ESEGUITO!!!!!!!!!!!");
				}
			}
		});
		return saveGame;
	}
	
	private JMenuItem createLoadGame() {
		JMenuItem loadGame = new JMenuItem("Carica partita");
		loadGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					FileInputStream input = new FileInputStream(salvataggio);
					ObjectInputStream in = new ObjectInputStream(input);
					setVisible(false);
					JFrame myFrame = new TrisGraphics((Tris) in.readObject());
					myFrame.setTitle("Tris");
					myFrame.setResizable(false);
					myFrame.setLocationRelativeTo(null);
					myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
					myFrame.setVisible(true);			
					in.close();
					input.close();
					} catch (IOException ex) {
						JOptionPane.showMessageDialog(loadGame, "ERRORE!!! CARICAMENTO NON RIUSCITO!!!!!!!!!!!");
				 	} catch (ClassNotFoundException ex) {
				 		JOptionPane.showMessageDialog(loadGame, "ERRORE!!!!!!!!!!!!!");
				 	}
			}
		});
		return loadGame;
	}
	
	class Tris implements Serializable {
		
		private static final long serialVersionUID = 1L;
		private JPanel griglia = null;
		private JButton b1 = null;
		private JButton b2 = null;
		private JButton b3 = null;
		private JButton b4 = null;
		private JButton b5 = null;
		private JButton b6 = null;
		private JButton b7 = null;
		private JButton b8 = null;
		private JButton b9 = null;
		private TrisLogic tris = null;
		private ImageIcon segnoImage = null;
		private Image segnoX = null;
		private Image segnoO = null;
		private String segno = "X";
		private int clickNumber = 0;
		private boolean[] clicked = {false, false, false, false, false, false, false, false, false};
		
		public Tris() {
			griglia = new JPanel();
			griglia.setLayout(new GridLayout(3, 3));
			
			setButtons();					
			addButtonsPanel();
			
			tris = new TrisLogic();
		}
		
		public Tris(TrisLogic tr) {	
			griglia = new JPanel();
			griglia.setLayout(new GridLayout(3, 3));
			setTrisLogic(tr);
		}
		
		public void addButtonsPanel() {
			griglia.add(b1);
			griglia.add(b2);
			griglia.add(b3);
			griglia.add(b4);
			griglia.add(b5);
			griglia.add(b6);
			griglia.add(b7);
			griglia.add(b8);
			griglia.add(b9);
		}
		
		public void setButtons() {
			(b1 = new JButton()).addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					click(1);
				}		
			});
			
			(b2 = new JButton()).addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					click(2);
				}		
			});
			
			(b3 = new JButton()).addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					click(3);
				}		
			});
			
			(b4 = new JButton()).addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					click(4);
				}		
			});
			
			(b5 = new JButton()).addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					click(5);
				}		
			});
			
			(b6 = new JButton()).addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					click(6);
				}		
			});
			
			(b7 = new JButton()).addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					click(7);
				}		
			});
			
			(b8 = new JButton()).addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					click(8);
				}		
			});
			
			(b9 = new JButton()).addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					click(9);
				}		
			});
		}
		
		public void setTrisLogic(TrisLogic tl) {
			tris = tl;
		}
		
		public TrisLogic getTrisLogic() {
			return tris;
		}
		
		public void setPanel(JPanel g) {
			griglia = g;
		}
		
		public JPanel getPanel() {
			return griglia;
		}
		
		public void click(int n) {
			clickNumber++;
			boolean risultato = false;	
			if(n == 1 && !clicked[n-1]) {
				try {
					segnoX = ImageIO.read(new File("D:\\Workspace Eclipse EE\\TRIS\\X_Tris.png"));
					segnoO = ImageIO.read(new File("D:\\Workspace Eclipse EE\\TRIS\\O_Tris.png"));
					segnoX = segnoX.getScaledInstance(b1.getWidth(),b1.getHeight(),Image.SCALE_DEFAULT);
					segnoO = segnoO.getScaledInstance(b1.getWidth(),b1.getHeight(),Image.SCALE_DEFAULT);
					segnoImage = new ImageIcon(segnoX);
				} catch(Exception e) {
					e.printStackTrace();
				}
				b1.setIcon(segnoImage);
				risultato = tris.putSign(0, 0, segno);
				if(risultato) {
					disableButtons();
					JOptionPane.showMessageDialog(griglia, "TRIS!!!!!!!!!!!!");
				} else {
					if(segno.equals("X")) {
						segno = "O";
						segnoImage = new ImageIcon(segnoO);
					}
					else {
						segno = "X";
						segnoImage = new ImageIcon(segnoX);
					}
				}
			} else if (n == 2 && !clicked[n-1]) {
				b2.setIcon(segnoImage);
				risultato = tris.putSign(0, 1, segno);
				if(risultato) {
					disableButtons();
					JOptionPane.showMessageDialog(griglia, "TRIS!!!!!!!!!!!!");
				} else {
					if(segno.equals("X")) {
						segno = "O";
						segnoImage = new ImageIcon(segnoO);
					}
					else {
						segno = "X";
						segnoImage = new ImageIcon(segnoX);
					}
				}
			} else if (n == 3 && !clicked[n-1]) {
				b3.setIcon(segnoImage);
				risultato = tris.putSign(0, 2, segno);
				if(risultato) {
					disableButtons();
					JOptionPane.showMessageDialog(griglia, "TRIS!!!!!!!!!!!!");
				} else {
					if(segno.equals("X")) {
						segno = "O";
						segnoImage = new ImageIcon(segnoO);
					}
					else {
						segno = "X";
						segnoImage = new ImageIcon(segnoX);
					}
				}	
			} else if (n == 4 && !clicked[n-1]) {
				b4.setIcon(segnoImage);
				risultato = tris.putSign(1, 0, segno);
				if(risultato) {
					disableButtons();
					JOptionPane.showMessageDialog(griglia, "TRIS!!!!!!!!!!!!");
				} else {
					if(segno.equals("X")) {
						segno = "O";
						segnoImage = new ImageIcon(segnoO);
					}
					else {
						segno = "X";
						segnoImage = new ImageIcon(segnoX);
					}
				}
			} else if (n == 5 && !clicked[n-1]) {
				b5.setIcon(segnoImage);
				risultato = tris.putSign(1, 1, segno);
				if(risultato) {
					disableButtons();
					JOptionPane.showMessageDialog(griglia, "TRIS!!!!!!!!!!!!");
				} else {
					if(segno.equals("X")) {
						segno = "O";
						segnoImage = new ImageIcon(segnoO);
					}
					else {
						segno = "X";
						segnoImage = new ImageIcon(segnoX);
					}
				}
			} else if (n == 6 && !clicked[n-1]) {
				b6.setIcon(segnoImage);
				risultato = tris.putSign(1, 2, segno);
				if(risultato) {
					disableButtons();
					JOptionPane.showMessageDialog(griglia, "TRIS!!!!!!!!!!!!");
				} else {
					if(segno.equals("X")) {
						segno = "O";
						segnoImage = new ImageIcon(segnoO);
					}
					else {
						segno = "X";
						segnoImage = new ImageIcon(segnoX);
					}
				}
			} else if (n == 7 && !clicked[n-1]) {
				b7.setIcon(segnoImage);
				risultato = tris.putSign(2, 0, segno);
				if(risultato) {
					disableButtons();
					JOptionPane.showMessageDialog(griglia, "TRIS!!!!!!!!!!!!");
				} else {
					if(segno.equals("X")) {
						segno = "O";
						segnoImage = new ImageIcon(segnoO);
					}
					else {
						segno = "X";
						segnoImage = new ImageIcon(segnoX);
					}
				}
			} else if (n == 8 && !clicked[n-1]) {
				b8.setIcon(segnoImage);
				risultato = tris.putSign(2, 1, segno);
				if(risultato) {
					disableButtons();
					JOptionPane.showMessageDialog(griglia, "TRIS!!!!!!!!!!!!");
				} else {
					if(segno.equals("X")) {
						segno = "O";
						segnoImage = new ImageIcon(segnoO);
					}
					else {
						segno = "X";
						segnoImage = new ImageIcon(segnoX);
					}
				}
			} else if (n == 9 && !clicked[n-1]) {
				b9.setIcon(segnoImage);
				risultato = tris.putSign(2, 2, segno);
				if(risultato) {
					disableButtons();
					JOptionPane.showMessageDialog(griglia, "TRIS!!!!!!!!!!!!");
				} else {
					if(segno.equals("X")) {
						segno = "O";
						segnoImage = new ImageIcon(segnoO);
					}
					else {
						segno = "X";
						segnoImage = new ImageIcon(segnoX);
					}
				}
			}
			if(clickNumber == 9 && risultato == false  
					&& clicked[1] && clicked[2] && clicked[3] 
					&& clicked[4] && clicked[5] && clicked[6] 
					&& clicked[7] && clicked[8] && clicked[9]) {
				JOptionPane.showMessageDialog(griglia, "PAREGGIO!!!!!!!!!!!!");
			}
		}
		
		private void disableButtons() {
			b1.setEnabled(false);
			b2.setEnabled(false);
			b3.setEnabled(false);
			b4.setEnabled(false);
			b5.setEnabled(false);
			b6.setEnabled(false);
			b7.setEnabled(false);
			b8.setEnabled(false);
			b9.setEnabled(false);
		}
		
	}

}
