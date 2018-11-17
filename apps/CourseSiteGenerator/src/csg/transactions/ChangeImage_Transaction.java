package csg.transactions;

import csg.data.CourseSiteData;
import csg.data.TeachingAssistantPrototype;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import jtps.jTPS_Transaction;


/**
 *
 * @author McKillaGorilla
 */
public class ChangeImage_Transaction implements jTPS_Transaction {
    Image oldImage;
    Image newImage;
    ImageView imageView;
    
    public ChangeImage_Transaction(Image initNewImage,ImageView initImageView ) {
        imageView = initImageView;
        oldImage = imageView.getImage();
        newImage = initNewImage;
    }

    @Override
    public void doTransaction() {
        imageView.setImage(newImage);
    }

    @Override
    public void undoTransaction() {
       imageView.setImage(oldImage);
    }
}
