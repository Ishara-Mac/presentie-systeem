package code;

public class Acces {
    private String username;
    private String wachwoord;
    private String acces;
    private boolean loggedin=false;

    public String getUsername() {
        return username;
    }

    public String getWachwoord() {
        return wachwoord;
    }

    public void setLoggedin(boolean loggedin) {
        this.loggedin = loggedin;
    }

    public Acces(String username, String wachwoord, String acces) {
        this.username=username;
        this.wachwoord=wachwoord;
        this.acces=acces;






    }
    public String toString(){
        return String.format("%s : %s : %s",username,wachwoord,acces);
    }
}
