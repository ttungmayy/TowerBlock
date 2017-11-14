package towerbloxx;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import javafx.animation.AnimationTimer;
import javafx.animation.PathTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

public class Block extends Pane{
    int blockX = 0;
    int blockY = 0;
    int blockWidth = 100;
    int blockHeight = 100;
    int blockSpeed = 5;
    int totalBlock = 0;
    int detachPosY = 600;
    int score = 0;
    boolean isDetach = false;
    boolean isOver = false;
    int count = 0;
    int angle = 0;
    int rotation = 0;
    int radius = 1;
    Path path;
    PathTransition pathTransition;
    AnimationTimer blockMovement;
    InputStream block;
    ImageView imgBlock;
    Block newBlock;
    
    int posXOne = 0;
    int posYOne = 0;
    
    public Block()
    {
        try {
        block = Files.newInputStream(Paths.get("/Users/namedojimo/NetBeansProjects/Project/src/towerbloxx/box.png"));
        imgBlock = new ImageView(new Image(block));
        imgBlock.setFitWidth(blockWidth);
        imgBlock.setFitHeight(blockWidth);
        imgBlock.setTranslateX(blockX);
        imgBlock.setTranslateY(blockY);
        
        getChildren().add(imgBlock);
        } catch (IOException e)
        {
            System.out.println("Cannot load box.png");
        }
    }
    
    public void swing()
    { 
        path = new Path();
        MoveTo moveTo = new MoveTo(200, 50);
        CubicCurveTo cubicCurveTo = new CubicCurveTo(200, 50, 250, 250, 700, 50);

        path.getElements().add(moveTo);
        path.getElements().add(cubicCurveTo);

        pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.millis(1000));
         if (count == 0){
            pathTransition.setNode(imgBlock);
            count++;
        }
        else{      
             
            newBlock = new Block();
            getChildren().add(newBlock); 
            pathTransition.setNode(newBlock);
        }
        pathTransition.setPath(path);
        pathTransition.setCycleCount(100);
     
        pathTransition.setAutoReverse(true);
        pathTransition.play();
    }
// 
    public void detach() 
    {

        pathTransition.stop();
        totalBlock++;
        score += 10;
        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setDuration(Duration.millis(1000));
        if (count == 1){
            translateTransition.setNode(imgBlock);
            count++;
        }
        else{      
            translateTransition.setNode(newBlock);
        }
        
       translateTransition.setToY(detachPosY);
        translateTransition.setCycleCount(1);
        translateTransition.setAutoReverse(true);
        translateTransition.play();
        detachPosY -= 83;
        //CheckCollision();

        swing();
        
    }
      

}
