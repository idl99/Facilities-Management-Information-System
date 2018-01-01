package Forms;

import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MessageDialog {
    
    private Alert alert;

    private MessageDialog(MessageDialogBuilder builder){
        this.alert = builder.alert;
    }

    public Alert getAlert() {
        return alert;
    }

    public void setAlert(Alert alert) {
        this.alert = alert;
    }

    public void show(){
        alert.showAndWait();
    }

    public static class MessageDialogBuilder{
    
        private Alert alert;
        
        public MessageDialogBuilder(){
            alert = new Alert(Alert.AlertType.CONFIRMATION);
        }
        
        public MessageDialogBuilder withTitle(String text){
            alert.setTitle(text);
            return this;
        }
        
        public MessageDialogBuilder withHeader(String text){
            alert.setHeaderText(text);
            return this;
        }
        
        public MessageDialogBuilder withContentText(String text){
            alert.setContentText(text);
            return this;
        }
        
        public MessageDialogBuilder withGraphic(Image graphic){
            alert.setGraphic(new ImageView(graphic));
            return this;
        }

        public MessageDialog build(){
            return new MessageDialog(this);
        }
    }
}

