import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Run {
    public static void main(String[] args) {
        LocalDateTime nu=LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String na=nu.format(formatter);
        System.out.println(nu);
        System.out.println(na);

    }
}
