import java.util.ArrayList;

/**
 * <p>Presentations keeps track of the slides in a presentation.</p>
 * <p>Only one instance of this class is available.</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class Presentation {
	private String showTitle; //The title of the presentation
	private ArrayList<Slide> showList = new ArrayList<>(); //An ArrayList with slides
	private int currentSlideNumber = 0; //The number of the current slide
	private SlideViewerComponent slideViewComponent = null; //The view component of the slides

	public Presentation() {
		clear();
	}

	public Presentation(SlideViewerComponent slideViewerComponent) {
		this.slideViewComponent = slideViewerComponent;
		clear();
	}

	public int getSize() {
		return this.showList.size();
	}

	public String getTitle() {
		return this.showTitle;
	}

	public void setTitle(String nt) {
		this.showTitle = nt;
	}

	public void setShowView(SlideViewerComponent slideViewerComponent) {
		this.slideViewComponent = slideViewerComponent;
	}

	//Returns the number of the current slide
	public int getSlideNumber() {
		return this.currentSlideNumber;
	}

	//Change the current slide number and report it in the window
	public void setSlideNumber(int number) {
		currentSlideNumber = number;
		if (number < 0 || number >= showList.size()) {

			//Making sure that it will open a slide, if the num is negative
			if (number < 0) {
				currentSlideNumber = 0;
			}

			//If the number is too big it will open the last slide
			else {
				currentSlideNumber = showList.size() - 1;
			}
		}

		//If the number is correct it will go to the selected slide
		else {
			currentSlideNumber = number;
		}

		if (slideViewComponent != null) {
			slideViewComponent.update(this, getCurrentSlide());
		}
	}

	//Remove the presentation
	void clear() {
		showList.clear();
		setSlideNumber(-1); //Reset slide number
	}

	//Add a slide to the presentation
	public void append(Slide slide) {
		if (slide != null) {
			showList.add(slide);
		}
	}

	//Return a slide with a specific number
	public Slide getSlide(int number) {
		if (number < 0 || number >= getSize()) {
			return null;
		}
		return showList.get(number);
	}

	//Return the current slide
	public Slide getCurrentSlide() {
		return getSlide(this.currentSlideNumber);
	}
}
