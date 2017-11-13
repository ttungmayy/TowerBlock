package towerbloxx;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import javafx.animation.PathTransition;
import javafx.scene.shape.Path;
import java.nio.file.Paths;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.*;
import javafx.util.Duration;

public class Block extends Pane {

    int blockX = 0;
    int blockWidth = 50;
    int blockHeight = 50;
    int blockSpeed = 10;
    int totalBlock = 0;
    int score = 0;
    boolean isDetach = false;
    Path path;
    InputStream block;
    ImageView imgBlock;

    public Block() {
        try {
            block = Files.newInputStream(Paths.get("C:/Users/PM/Documents/NetBeansProjects/TowerBloxx/src/towerbloxx/box.png"));
            imgBlock = new ImageView(new Image(block));
            imgBlock.setFitWidth(blockWidth);
            imgBlock.setFitHeight(blockWidth);
            imgBlock.setTranslateX(blockX);
            getChildren().add(imgBlock);
        } catch (IOException e) {
            System.out.println("Cannot load box.png");
        }
    }

    public void move() {

        AnimationTimer blockMovement = new AnimationTimer() {
            public void handle(long arg0) {
                path = new Path();
                MoveTo moveTo = new MoveTo(100, 0);
                CubicCurveTo cubicCurveTo = new CubicCurveTo(100, 0, 200, 250, 500, 0);
                path.getElements().add(moveTo);
                path.getElements().add(cubicCurveTo);
                PathTransition pathTransition = new PathTransition();
                pathTransition.setDuration(Duration.millis(2000));
                pathTransition.setNode(imgBlock);
                pathTransition.setPath(path);
                pathTransition.setCycleCount(50);
                pathTransition.setAutoReverse(true);
            }
        };
        blockMovement.start();
    }

    public void detach() {
        isDetach = true;
        totalBlock++;
        score += 10;
    }

    public Block makeNew() {
        Block newBlock = new Block();
        newBlock.blockX = 0;
        isDetach = false;
        return newBlock;
    }
}
