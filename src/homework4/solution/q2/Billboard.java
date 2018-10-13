package homework4.solution.q2;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;


/***
 * Abs. Func.
 * Allows the display of a window which can control the type of strategy currently being used
 * and also display all the panels as defined in the HW assignment question
 * 
 * Rep Inv.
 * A window contains a mean of allowing the user to switch strategies and the panels themselves
 *
 */
public class Billboard extends JFrame {
	public static final int PANEL_ROWS = 5;
	public static final int PANEL_COLS = 5;
	private static final int PANELS_MARGIN = 10;
	private static final int MENUBAR_SLACK = 20;
	
	// Hold pointers to all the panels this window holds
	private BBPanel[][] panels = new BBPanel[PANEL_ROWS][PANEL_COLS]; 
	
	Container contentPane = null;

	private static final long serialVersionUID = -6925365549045763645L;

	/***
	 * Creates a window and populates all menus, panels and show the window
	 * @effects this
	 * @throws HeadlessException
	 */
	public Billboard() throws HeadlessException {
		initialize();
	}

	/***
	 * Creates a window and populates all menus, panels and show the window
	 * @effects this
	 * @throws HeadlessException
	 */
	public Billboard(GraphicsConfiguration arg0) {
		super(arg0);
		initialize();
	}

	/***
	 * Creates a window and populates all menus, panels and show the window
	 * @effects this
	 * @throws HeadlessException
	 */
	public Billboard(String arg0) throws HeadlessException {
		super(arg0);
		initialize();
	}

	/***
	 * Creates a window and populates all menus, panels and show the window
	 * @effects this
	 * @throws HeadlessException
	 */
	public Billboard(String arg0, GraphicsConfiguration arg1) {
		super(arg0, arg1);
		initialize();
	}
	
	/***
	 * Builds the window, menus and Panels, will make the window show
	 * @effects this
	 */
	private void initialize() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		contentPane = getContentPane();
		
		buildMenu();
		
		contentPane.setLayout(new GridLayout(PANEL_ROWS, PANEL_COLS));
		for(int row=0; row<(PANEL_ROWS); ++row) {
			for(int col=0; col < PANEL_COLS; ++col) {
				BBPanel bb = new BBPanel(row, col);
				panels[row][col] = bb;
				contentPane.add(bb);
			}
		}

		setSize(PANEL_COLS*(BBPanel.WIDTH+PANELS_MARGIN), PANEL_ROWS*(BBPanel.HEIGHT+PANELS_MARGIN)+MENUBAR_SLACK);
		setVisible(true);
	}

	/***
	 * Build the windows's menus
	 * @effects this
	 */
	private void buildMenu() {
		JMenuBar menubar = new JMenuBar();
		JMenu strategies = new JMenu("Strategies");
		
		JMenuItem menuAsc = new JMenuItem("Ascending");
		JMenuItem menuDsc = new JMenuItem("Descending");
		JMenuItem menuOddEven = new JMenuItem("Odd Even");
		JMenuItem menuRandom = new JMenuItem("Random");
		JMenuItem space = new JMenuItem("----------");
		JMenuItem reset = new JMenuItem("Reset");
		
		menubar.add(strategies);
		
		strategies.add(menuAsc);
		strategies.add(menuDsc);
		strategies.add(menuOddEven);
		strategies.add(menuRandom);
		strategies.add(space);
		strategies.add(reset);
		
		menuAsc.addActionListener(new ActionListener() {
		@Override
			public void actionPerformed(ActionEvent e) {
				ColorGenerator.getInstance().setStrategy(new AscendingStrategy());
			}
		});
		
		menuDsc.addActionListener(new ActionListener() {
		@Override
			public void actionPerformed(ActionEvent e) {
				ColorGenerator.getInstance().setStrategy(new DescendingStrategy());
			}
		});
		
		menuOddEven.addActionListener(new ActionListener() {
		@Override
			public void actionPerformed(ActionEvent e) {
				ColorGenerator.getInstance().setStrategy(new OddEvenStrategy());
			}
		});

		menuRandom.addActionListener(new ActionListener() {
		@Override
			public void actionPerformed(ActionEvent e) {
				ColorGenerator.getInstance().setStrategy(new RandomStrategy());
			}
		});

		reset.addActionListener(new ActionListener() {
		@Override
			public void actionPerformed(ActionEvent e) {
				for(int i=0; i < (PANEL_COLS*PANEL_ROWS); ++i) {
					int row = i/PANEL_COLS;
					int col = i%PANEL_COLS;
					
					panels[row][col].reset();
				}
				repaint();
			}
		});
		
		setJMenuBar(menubar);
	}

	/***
	 * Abs. Func.
	 * Represents a Billboard panel as described in our assignment. allows easy update of colors
	 * via an observable object which signlas this about the change
	 * 
	 * Rep. Inv.
	 * A rectangle which can change color
	 *
	 */
	class BBPanel extends JPanel implements Observer {
		/**
		 * 
		 */
		private static final long serialVersionUID = 2052619855349473824L;
		private static final int WIDTH = 100;
		private static final int HEIGHT = 100;		
		private Color panelColor = Color.WHITE;
		private int row = -1;
		private int col = -1;
		
		public BBPanel(int row, int col) {
			this.row = row;
			this.col = col;
			ColorGenerator.getInstance().addObserver(this);
			checkRep();
		}
		
		/***
		 * Redraw this panel 
		 */
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D)g;
			int x = 0;
			int y = 0;
			g2.setColor(Color.BLACK); // Outline color
			g2.drawRect(x, y, WIDTH, HEIGHT);
			g2.setColor(panelColor);
			g2.fillRect(x, y, WIDTH, HEIGHT);
		}
		
		private void checkRep() {
			assert((row > 0)&&(row <Billboard.PANEL_ROWS)): "Row is unbound";
			assert((col > 0)&&(col <Billboard.PANEL_COLS)): "Col is unbound";
			assert(panelColor!=null): "Color can't be null";
		}

		/***
		 * A handler for color change event. if the event is relevant to this, new color will be accepted and panel will be repainted
		 * @effects this
		 */
		@Override
		public void update(Observable arg0, Object arg1) {
			if(arg1 instanceof ColorGenerator.UpdatePanel) {
				ColorGenerator.UpdatePanel up = (ColorGenerator.UpdatePanel)arg1;
				if((up.getCol()==col)&&(up.getRow()==row)){ // Event was delegated to me
					panelColor = up.getColor();
					repaint();
				}
			}
			checkRep();
		}
		
		/***
		 * Sets panel to be white, will not redraw
		 */
		public void reset() {
			panelColor = Color.WHITE;
		}
	}
}
