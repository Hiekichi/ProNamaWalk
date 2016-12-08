package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class Controller implements Initializable {
    @FXML
    Canvas canvas;
    GraphicsContext gc;
    Image image;
    int x, y;
    Image[] imageWalk;
    Timer timer;
    int timerCounter;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gc = canvas.getGraphicsContext2D();
        image = new Image(Paths.get("02.png").toUri().toString());
        gc.drawImage(image, 200, 100);
        x = 200;
        y = 100;
        imageWalk = new Image[2];
        imageWalk[0] = new Image(Paths.get("01-1.png").toUri().toString());
        imageWalk[1] = new Image(Paths.get("01-4.png").toUri().toString());
    }

    @FXML
    void keyPressed(KeyEvent event) {
        System.out.println("keyPressed.");
        if (timer == null) {
            timer = new Timer();
            timer.schedule(
                    new TimerTask() {
                        @Override
                        public void run() {
                            if (timerCounter < 10) {
                                gc.setFill(Color.WHITE);
                                gc.fillRect(x, y, 32, 64);
                                x -= 4;
                                gc.drawImage(imageWalk[timerCounter % 2], x, y);
                                timerCounter += 1;
                            } else {
                                gc.setFill(Color.WHITE);
                                gc.fillRect(x, y, 32, 64);
                                gc.drawImage(image, x, y);
                                timerCounter = 0;
                                timer.cancel();
                                timer = null;
                                cancel();
                            }
                        }
                    },
                    0, 100);
        }

    }
}
