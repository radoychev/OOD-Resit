import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

public class KeyController extends KeyAdapter {
	private PresentationController presentationController;

	public KeyController(PresentationController controller) {
		this.presentationController = controller;
	}

	public void keyPressed(KeyEvent keyEvent) {
		switch (keyEvent.getKeyCode()) {
			case KeyEvent.VK_PAGE_DOWN:
			case KeyEvent.VK_DOWN:
			case KeyEvent.VK_ENTER:
			case '+':
				presentationController.nextSlide();
				break;
			case KeyEvent.VK_PAGE_UP:
			case KeyEvent.VK_UP:
			case '-':
				presentationController.prevSlide();
				break;
			case 'q':
			case 'Q':
				presentationController.exit();
				break;
			default:
				break;
		}
	}
}
