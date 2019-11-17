
package ohtu;

public class Player {
    private String name, team, nationality, birthdate;
    private int goals, assists, penalties;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getNationality() {
        return nationality;
    }

    public int getScore() {
        return assists+goals;
    }

    @Override
    public String toString() {
        return name + " " + goals + " " + assists + " " + penalties + " " + team + " " + nationality + " " + birthdate;
    }
      
}
