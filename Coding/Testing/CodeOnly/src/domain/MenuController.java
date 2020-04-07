package domain;

import code.Rooster;
import code.ZiekteMelding;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class MenuController {

    public TextField dagenZiek;

    public void meldziek(ActionEvent actionEvent) {
        String text=dagenZiek.getText();
        ZiekteMelding melding =new ZiekteMelding(Rooster.getCurrentUser(),
                LocalDate.now().plusDays(Long.parseLong(text)));



    }
}
