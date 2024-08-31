import java.awt.MenuBar;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

public class MenuController extends MenuBar {
	private Frame parent;
	private PresentationController presentationController; // Responsible for commands

	public MenuController(Frame frame, PresentationController controller) {
		this.parent = frame;
		this.presentationController = controller;
		initializeMenu();
	}
	//Menu initialization
	private void initializeMenu() {
		MenuItem menuItem;

		//File Menu
		Menu fileMenu = new Menu("File");
		fileMenu.add(menuItem = mkMenuItem("Open"));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				//Loading presentation
				presentationController.loadPresentation("testPresentation.xml");
				parent.repaint();
			}
		});

		fileMenu.add(menuItem = mkMenuItem("New"));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				presentationController.setSlideNumber(0);
				parent.repaint();
			}
		});

		fileMenu.add(menuItem = mkMenuItem("Save"));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				presentationController.savePresentation("savedPresentation.xml");
			}
		});

		fileMenu.addSeparator();
		fileMenu.add(menuItem = mkMenuItem("Exit"));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				presentationController.exit();
			}
		});
		add(fileMenu);

		//View Menu
		Menu viewMenu = new Menu("View");
		viewMenu.add(menuItem = mkMenuItem("Next"));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				presentationController.nextSlide();
			}
		});

		viewMenu.add(menuItem = mkMenuItem("Prev"));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				presentationController.prevSlide();
			}
		});

		viewMenu.add(menuItem = mkMenuItem("Go to"));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				String pageNumberStr = JOptionPane.showInputDialog(parent, "Page number?");
				try {
					int pageNumber = Integer.parseInt(pageNumberStr);
					presentationController.goToSlide(pageNumber - 1);
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(parent, "Invalid number", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		add(viewMenu);

		//Help Menu
		Menu helpMenu = new Menu("Help");
		helpMenu.add(menuItem = mkMenuItem("About"));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				AboutBox.show(parent);
			}
		});
		setHelpMenu(helpMenu);
	}

	//Helper method to create menu items
	private MenuItem mkMenuItem(String name) {
		return new MenuItem(name, new MenuShortcut(name.charAt(0)));
	}
}
