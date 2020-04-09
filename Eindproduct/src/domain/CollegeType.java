package domain;

public enum CollegeType {
    werkCollege("Werkcollege"),
    hoorCollege("Hoorcollege"),
    spreekUur("Spreekuur");

    private final String type;

    CollegeType(String type) {
        this.type = type;
    }

    public String toString(){
        return this.type;
    }
}
