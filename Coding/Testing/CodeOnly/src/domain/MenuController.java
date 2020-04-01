package domain;

import code.Rooster;
import code.ZiekteMelding;
import javafx.event.ActionEvent;

import java.time.LocalDate;

public class MenuController {

    public void meldziek(ActionEvent actionEvent) {
        ZiekteMelding melding =new ZiekteMelding(Rooster.getCurrentUser(), LocalDate.now());



    }
}
