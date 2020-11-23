package ohtu;

public class TennisGame {
    
    private int player1Score = 0;
    private int player2Score = 0;
    private String score;
    private final String player1Name;
    private final String player2Name;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
        this.score = this.evenScoresMessage();
    }

    public void wonPoint(String playerName) {
        if (playerName == player1Name)
            player1Score += 1;
        else
            player2Score += 1;
        updateScore();
    }
    
    private void updateScore() {
        if (player1Score==player2Score) {
            score = evenScoresMessage();
            
        } else if (player1Score>=4 || player2Score>=4) {
            score = gameEndMessage();
            
        } else {
            score = unevenScoresMessage();
        }
    }
    
    private String scoreName(int score) {
        switch (score) {
            case 0:
                return "Love";
            case 1:
                return "Fifteen";
            case 2:
                return "Thirty";
            case 3:
                return "Forty";
            default:
                return "";
        }
    }
    
    private String evenScoresMessage() {
        switch (player1Score) {
            case 0:
                return scoreName(player1Score) + "-All";
            case 1:
                return scoreName(player1Score) + "-All";
            case 2:
                return scoreName(player1Score) + "-All";
            case 3:
                return scoreName(player1Score) + "-All";
            default:
                return "Deuce";
        }
    }
    
    private String unevenScoresMessage() {
        return scoreName(player1Score) + "-" + scoreName(player2Score);
    }
    
    private String gameEndMessage() {
        int minusResult = player1Score-player2Score;
        
        if (minusResult==1) return "Advantage " + player1Name;
        
        if (minusResult ==-1) return "Advantage " + player2Name;
        
        if (minusResult>=2) return "Win for " + player1Name;
        
        return "Win for " + player2Name;
    }

    public String getScore() {
        return score;
    }
}