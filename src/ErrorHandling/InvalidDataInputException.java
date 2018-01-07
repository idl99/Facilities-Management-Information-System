package ErrorHandling;

import Forms.MessageDialog;
import javafx.scene.image.Image;

public class InvalidDataInputException extends Exception {

    private String message;

    public InvalidDataInputException(String message){
        this.message = message;
    }

    public static void showErrorDialog(String message){
        String response = new MessageDialog.MessageDialogBuilder()
                .withTitle("ERROR: INVALID DATA INPUT")
                .withHeader("Invalid Data Input")
                .withContentText(message+" Please refill the form with valid data.")
                .withGraphic(new Image("/Graphics/Icons/Warning_Icon.png")).build().show();
    }

    public void showErrorDialog(){
        this.showErrorDialog(this.message);
    }

}
