package ohtu;

public class Player implements Comparable<Player> {

    private String name;
    private String nationality;
    private int assists;
    private int goals;
    private int penalties;
    private String team;
    private int games;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public int getGoals() {
        return goals;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public int getPenalties() {
        return penalties;
    }

    public void setPenalties(int penalties) {
        this.penalties = penalties;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public int getGames() {
        return games;
    }

    public void setGames(int games) {
        this.games = games;
    }

    public int points() {
        return this.goals + this.assists;
    }

    @Override
    public int compareTo(Player player) {
        int p = player.points() - this.points();
        if (p == 0) {
            return this.name.compareTo(player.name);
        }
        return p;
    }

    @Override
    public String toString() {
        return name + "\t\t" + team + "\t" + goals + " + " + assists + " = " + (points());
    }
}
