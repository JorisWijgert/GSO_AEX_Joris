/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aexbanner;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author jvdwi
 */
public class AEXBanner extends Application {

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 100;
    public static final int NANO_TICKS = 20000000;

    private Text text;
    private String stringText;
    private double textLength;
    private double textPosition;
    private BannerController controller;
    private AnimationTimer animationTimer;

    @Override
    public void start(Stage primaryStage) {

        Font font = new Font("Arial", HEIGHT);
        text = new Text();
        text.setFont(font);
        text.setFill(Color.BLACK);

        Pane root = new Pane();
        root.getChildren().add(text);
        Scene scene = new Scene(root, WIDTH, HEIGHT);

        primaryStage.setTitle("AEX banner");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.toFront();

        // Start animation: text moves from right to left
        animationTimer = new AnimationTimer() {
            private long prevUpdate;

            @Override
            public void handle(long now) {
                text.setText(stringText);
                textLength = text.getLayoutBounds().getWidth();
                long lag = now - prevUpdate;
                if (lag >= NANO_TICKS) {
                    if (textPosition + textLength <= 0) {
                        textPosition = WIDTH;
                    } else {
                        textPosition -= 5;
                    }
                    text.relocate(textPosition, 0);
                    prevUpdate = now;
                }
            }

            @Override
            public void start() {
                prevUpdate = System.nanoTime();
                textPosition = WIDTH;
                text.relocate(textPosition, 0);
                controller = new BannerController(AEXBanner.this);
                super.start();
            }
        };
        animationTimer.start();
    }

    public void stop() {
        animationTimer.stop();
        controller.stop();
    }

    public void setKoersen(String koersen) {
        stringText = koersen;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
