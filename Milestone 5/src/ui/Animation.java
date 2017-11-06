package ui;

import javafx.animation.Animation.Status;
import javafx.animation.FadeTransition;
import javafx.animation.PathTransition;
import javafx.animation.PathTransition.OrientationType;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.ImageView;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

/**
 *
 *
 * @author jordan
 * Animation class which is used to animate imageview
 * nodes to move on the pane.
 *
 */


public class Animation {


	public Animation() {

	}


	public void animate(ImageView img, CubicCurveTo curve, boolean rotate) {

		//initial rotation value
		final double rotation = img.getRotate();

		PathTransition pathTransition = new PathTransition();

		//sets path elements
	     Path path = new Path();
	     path.getElements().add (new MoveTo (curve.getControlX1(), curve.getControlY1()));
	     path.getElements().add (curve);

	     //sets animation duration
	     pathTransition.setDuration(Duration.millis(3000));
	     pathTransition.setNode(img);
	     pathTransition.setPath(path);
	     if (rotate)
	    	 pathTransition.setOrientation(OrientationType.ORTHOGONAL_TO_TANGENT);

	     //fade out transition when first animation finishes, and return image
	     //to initial location
	     pathTransition.setOnFinished(e -> {
	 		FadeTransition ft = new FadeTransition(Duration.millis(1000), img);
	 		ft.setFromValue(1.0);
	 		ft.setToValue(0.0);
	 		ft.setOnFinished(e2 -> {
	 			img.setOpacity(1);
	 			img.setTranslateX(0);
	 			img.setTranslateY(0);
	 			img.setRotate(rotation);

	 		});
	 		ft.play();
	     });

	     //start animation
	     pathTransition.play();
	     pathTransition.setAutoReverse(true);


	}





}
