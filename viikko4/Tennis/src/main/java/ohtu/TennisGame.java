package ohtu;

public class TennisGame {

    private static final int WINNING_POINTS = 4;

    private static final String[] POINTS_AS_STRING = { "Love", "Fifteen", "Thirty", "Forty" };

    private Player firstPlayer;
    private Player secondPlayer;

    public TennisGame(String firstName, String secondName) {
        firstPlayer = new Player(firstName);
        secondPlayer = new Player(secondName);
    }

    public void wonPoint(String playerName) {
        if (playerName.equals(firstPlayer.getName()))
            firstPlayer.incrementScore();
        else
            secondPlayer.incrementScore();
    }

    private boolean evenPoints() {
        return firstPlayer.getScore() == secondPlayer.getScore();
    }

    private boolean winningPointsReached() {
        return Math.max(firstPlayer.getScore(), secondPlayer.getScore()) >= WINNING_POINTS;
    }

    private String evenPointsAsString(int points) {
        return points < 4 ? POINTS_AS_STRING[points] + "-All" : "Deuce";
    }

    private String winningPointsAsString(int pointDifference) {
        String winningPlayer = pointDifference > 0 ? firstPlayer.getName() : secondPlayer.getName();
        String resultString = (Math.abs(pointDifference)) > 1 ? "Win for " : "Advantage ";
        return resultString + winningPlayer;
    }

    private String intermediateScore() {
        return POINTS_AS_STRING[firstPlayer.getScore()] + "-" + POINTS_AS_STRING[secondPlayer.getScore()];
    }

    public String getScore() {
        if (evenPoints()) {
            return evenPointsAsString(firstPlayer.getScore());
        } else if (winningPointsReached()) {
            return winningPointsAsString(firstPlayer.getScore() - secondPlayer.getScore());
        } else {
            return intermediateScore();
        }
    }
}