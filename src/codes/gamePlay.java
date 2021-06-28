package codes;

import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

public class gamePlay {

    public void restartGame() {
        Main main = new Main();
        try {
            Window window = getRunningWindow();
            closeWindow(window);
            main.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void justCloseWindow() {
        Window window = getRunningWindow();
        closeWindow(window);
    }

    private Window getRunningWindow() {
        Main main = new Main();
        return main.getStage().getScene().getWindow();
    }

    private void closeWindow(Window window) {
        window.fireEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSE_REQUEST));
    }
}
