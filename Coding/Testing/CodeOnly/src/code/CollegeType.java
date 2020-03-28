package code;

public enum CollegeType {
    werkCollege("Werkcollege"),
    hoorCollege("Hoorcollege"),
    spreekUur("Spreekuur");

    private final String type;

    private CollegeType(String type) {
        this.type = type;
    }

    public String toString(){
        return this.type;
    }
}
