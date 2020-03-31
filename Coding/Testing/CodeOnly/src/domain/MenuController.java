package domain;

import code.Rooster;
import code.ZiekteMelding;
import javafx.event.ActionEvent;

public class MenuController {

    public void meldziek(ActionEvent actionEvent) {
        ZiekteMelding melding =new ZiekteMelding(Rooster.getCurrentUser());


    }
}
