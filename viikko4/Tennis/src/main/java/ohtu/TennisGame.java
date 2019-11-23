package ohtu;

public class TennisGame {

	private int player1Score = 0;
	private int player2Score = 0;
	private String player1Name;
	private String player2Name;

	public TennisGame(String player1Name, String player2Name) {
		this.player1Name = player1Name;
		this.player2Name = player2Name;
	}

	public void wonPoint(String playerName) {
		if(playerName.equals(player1Name))
			player1Score++;
		else
			player2Score++;
	}

	public String getScore() {
		String score;
		if(player1Score == player2Score) {
			score = getScore(player1Score);
			if(!score.equals("Deuce"))
				score += "-All";
		} else if(player1Score >= 4 || player2Score >= 4) {
			int scoreDiff = player1Score - player2Score;
			if(scoreDiff == 1)
				score = "Advantage " + player1Name;
			else if(scoreDiff == -1)
				score = "Advantage " + player2Name;
			else if(scoreDiff >= 2)
				score = "Win for " + player1Name;
			else
				score = "Win for " + player2Name;
		} else {
			score = getScore(player1Score) + "-" + getScore(player2Score);
		}
		return score;
	}

	public String getScore(int score) {
		switch(score) {
			case 0:
				return "Love";
			case 1:
				return "Fifteen";
			case 2:
				return "Thirty";
			case 3:
				return "Forty";
			default:
				return "Deuce";
		}
	}
}