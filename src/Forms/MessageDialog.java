package Forms;

import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Dialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.List;
import java.util.Optional;

public class MessageDialog {
    
    private Dialog dialog;

    private MessageDialog(MessageDialogBuilder builder){
        this.dialog = builder.dialog;
    }

    public Dialog getDialog() {
        return dialog;
    }

    public void setDialog(Alert dialog) {
        this.dialog = dialog;
    }

    public String show(){
        Optional<String> result =  dialog.showAndWait();
        return result.get();
    }

    public static class MessageDialogBuilder{
    
        private Dialog dialog;
        
        public MessageDialogBuilder(){
            dialog = new Alert(Alert.AlertType.CONFIRMATION);
        }

        public MessageDialogBuilder choiceDialogType(List<String> choices){
            dialog = new ChoiceDialog<>("",choices);
            return this;
        }
        
        public MessageDialogBuilder withTitle(String text){
            dialog.setTitle(text);
            return this;
        }
        
        public MessageDialogBuilder withHeader(String text){
            dialog.setHeaderText(text);
            return this;
        }
        
        public MessageDialogBuilder withContentText(String text){
            dialog.setContentText(text);
            return this;
        }
        
        public MessageDialogBuilder withGraphic(Image graphic){
            dialog.setGraphic(new ImageView(graphic));
            return this;
        }

        public MessageDialog build(){
            return new MessageDialog(this);
        }
    }
}

