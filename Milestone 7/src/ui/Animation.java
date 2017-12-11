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


	public void animate(ImageView img) {

	     //fade out transition
	 	FadeTransition ft = new FadeTransition(Duration.millis(1000), img);
	 	ft.setFromValue(1.0);
	 	ft.setToValue(0.0);
	 	ft.setOnFinished(e2 -> {
	 		img.setOpacity(1);
	 	});
	 	ft.play();


	}

	public void animateLong(ImageView img) {

	     //fade out transition
	 	FadeTransition ft = new FadeTransition(Duration.millis(3000), img);
	 	ft.setFromValue(0.0);
	 	ft.setToValue(1.0);

	 	ft.play();


	}



}
