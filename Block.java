package towerbloxx;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import javafx.animation.PathTransition;
import java.nio.file.Paths;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.*;
import javafx.util.Duration;

public class Block extends Pane {

    int blockX = 100;
    int blockY = 200;
    int blockWidth = 50;
    int radius = 150;
    int angle = 0;
    int blockHeight = 50;
    int blockSpeed = 10;
    int totalBlock = 0;
    int score = 0;
    boolean isDetach = false;
    Path path;
    InputStream block;
    ImageView imgBlock;
    PathTransition pathTransition;

    public Block() {
        try {
            block = Files.newInputStream(Paths.get("/Users/namedojimo/NetBeansProjects/Project/src/towerbloxx/box.png"));
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

                if (angle < 50)
                {
                    angle++;
                    blockX += blockSpeed + (ovalCenterX + Math.sin(Math.toRadians(angle)) * radius);
                    blockY += blockSpeed  + (ovalCenterY + Math.cos(Math.toRadians(angle)) * radius);
                }
                
                else
                {
                    angle++;
                    blockX += blockSpeed + (ovalCenterX + Math.cos(Math.toRadians(angle)) * radius);
                    blockY += blockSpeed  + (ovalCenterY + Math.sin(Math.toRadians(angle)) * radius);
               
                }

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
