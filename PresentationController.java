import java.io.File;
import java.io.IOException;

public class PresentationController {
    //Interactions with the presentation
    private Presentation presentation;
    private SlideViewerComponent slideViewerComponent;

    public PresentationController(Presentation presentation, SlideViewerComponent slideViewerComponent) {
        this.presentation = presentation;
        this.slideViewerComponent = slideViewerComponent;
        this.presentation.setShowView(slideViewerComponent);
    }

    //Refreshing the presentation
    public void updateView() {
        this.slideViewerComponent.update(presentation, presentation.getCurrentSlide());
    }

    //Next slide
    public void nextSlide() {
        int currentSlideNumber = presentation.getSlideNumber();
        if (currentSlideNumber < (presentation.getSize() - 1)) {
            presentation.setSlideNumber(currentSlideNumber + 1);
            updateView();
        }
    }

    //Previous slide
    public void prevSlide() {
        int currentSlideNumber = presentation.getSlideNumber();
        if (currentSlideNumber > 0) {
            presentation.setSlideNumber(currentSlideNumber - 1);
            updateView();
        }
    }

    //Sets the slide number
    public void setSlideNumber(int number) {
        presentation.setSlideNumber(number);
        updateView();
    }

    //Go to slide
    public void goToSlide(int number) {
        setSlideNumber(number);
    }

    //Exit
    public void exit() {
        System.exit(0);
    }

    //Save the presentation
    public void savePresentation(String filename) {
        try {
            new XMLAccessor().saveFile(presentation, filename);
        } catch (IOException e) {
            System.err.println("Error saving the presentation: " + e.getMessage());
        }
    }

    //Checks if the file is valid
    private boolean isValidFile(String filename) {
        File file = new File(filename);
        if (!file.exists()) {
            System.err.println("File does not exist: " + filename);
            return false;
        }
        if (!file.isFile()) {
            System.err.println("Not a valid file: " + filename);
            return false;
        }
        if (!filename.endsWith(".xml")) {
            System.err.println("File is not an XML file: " + filename);
            return false;
        }
        return true;
    }

    //Load from a file
    public void loadPresentation(String filename) {
        if (isValidFile(filename)) {
            try {
                new XMLAccessor().loadFile(presentation, filename);
                //To set to open the first slide
                presentation.setSlideNumber(0);
                updateView();
            } catch (IOException e) {
                System.err.println("Cannot load presentation: " + e.getMessage() + "!");
                System.out.println("Please try again.");
            }
        } else {
            System.err.println(filename + " the uploaded file cannot be opened!");
        }
    }
}
