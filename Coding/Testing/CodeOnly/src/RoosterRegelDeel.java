import java.time.LocalDateTime;

public class RoosterRegelDeel {
    private College les;
    private LocalDateTime begintijd;
    private LocalDateTime eindtijd;
    private TijdBlok blok;

    public RoosterRegelDeel(LocalDateTime begintijd,LocalDateTime eindtijd, College les) {
        this.begintijd =begintijd;
        this.eindtijd=eindtijd;
        this.les=les;
    }

    public void setLes(College les) {
        this.les = les;
    }
}
