package ohtu;

public class TennisGame {

    private int player1_game_score = 0;
    private int player2_game_score = 0;
    private final int DEUCE_THRESHOLD = 4;
    private final int PLAYER_ONE_LEAD_ONE_POINT = 1;
    private final int PLAYER_TWO_LEAD_ONE_POINT = -1;
    private final int PLAYER_ONE_LEAD_TWO_POINTS = 2;
    private final int SINGLE_WON_POINT = 1;
    private final int LOVE = 0;
    private final int FIFTEEN = 1;
    private final int THIRTY = 2;
    private final int FORTY = 3;
    private final String player1;
    private final String player2;

    public TennisGame(String player1Name, String player2Name) {
        this.player1 = player1Name;
        this.player2 = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName.equalsIgnoreCase(this.player1)) {
            player1_game_score += SINGLE_WON_POINT;
        } else {
            player2_game_score += SINGLE_WON_POINT;
        }
    }

    public String getLevelOfEvenScore() {
        String evenScore;
        switch (player1_game_score) {
            case LOVE:
                evenScore = "Love-All";
                break;
            case FIFTEEN:
                evenScore = "Fifteen-All";
                break;
            case THIRTY:
                evenScore = "Thirty-All";
                break;
            case FORTY:
                evenScore = "Forty-All";
                break;
            default:
                evenScore = "Deuce";
                break;

        }
        return evenScore;
    }

    public String getDeuceBattleScore() {
        String deuceScore;
        int minusResult = player1_game_score - player2_game_score;
        if (minusResult == PLAYER_ONE_LEAD_ONE_POINT) {
            deuceScore = "Advantage " + player1;
        } else if (minusResult == PLAYER_TWO_LEAD_ONE_POINT) {
            deuceScore = "Advantage " + player2;
        } else if (minusResult >= PLAYER_ONE_LEAD_TWO_POINTS) {
            deuceScore = "Win for " + player1;
        } else {
            deuceScore = "Win for " + player2;
        }
        return deuceScore;
    }

    public String getCurrentScore(int playerScore) {
        String currentScore = "";
        switch (playerScore) {
            case LOVE:
                currentScore += "Love";
                break;
            case FIFTEEN:
                currentScore += "Fifteen";
                break;
            case THIRTY:
                currentScore += "Thirty";
                break;
            case FORTY:
                currentScore += "Forty";
                break;
        }
        return currentScore;
    }

    public String getScore() {
        if (player1_game_score == player2_game_score) {
            return getLevelOfEvenScore();
        } else if (player1_game_score >= DEUCE_THRESHOLD || player2_game_score >= DEUCE_THRESHOLD) {
            return getDeuceBattleScore();
        } else {
            return getCurrentScore(player1_game_score) + "-" + getCurrentScore(player2_game_score);
        }
    }
}
