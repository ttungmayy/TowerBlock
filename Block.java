package towerbloxx;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Block extends Pane{
    int blockX = 0;
    int blockWidth = 50;
    int blockHeight = 50;
    int blockSpeed = 10;
    int totalBlock = 0;
    int score = 0;
    boolean isDetach = false;
    
    public Block()
    {
        try {
        InputStream block = Files.newInputStream(Paths.get("C:/Users/PM/Documents/NetBeansProjects/TowerBloxx/src/towerbloxx/box.png"));
        ImageView imgBlock = new ImageView(new Image(block));
        imgBlock.setFitWidth(blockWidth);
        imgBlock.setFitHeight(blockWidth);
        imgBlock.setTranslateX(blockX);
        getChildren().add(imgBlock);
        } catch (IOException e)
        {
            System.out.println("Cannot load box.png");
        }
    }
    
    public void move()
    {
        AnimationTimer blockMovement = new AnimationTimer()
        {
            public void handle(long arg0)
            {
                blockX += blockSpeed;
                if (blockX + blockWidth >= Main.width)
                {
                    blockX = Main.width - blockWidth;
                    blockSpeed *= -1;
                }
                else if (blockX + blockWidth < 0)
                {
                    blockX += blockWidth;
                    blockSpeed *= -1;
                }
                setTranslateX(blockX);
            }
        };
        blockMovement.start();
  }
    
    public void detach()
    {
        isDetach = true;
        totalBlock++;
        score += 10;
    }
    
    public Block makeNew()
    {
        Block newBlock = new Block();
        newBlock.blockX = 0;
        isDetach = false;
        System.out.println("Eiei");
        return newBlock;
    }
}
