import static org.junit.Assert.*;

import org.junit.Test;

import jdk.nashorn.internal.ir.annotations.Ignore;

public class TennisGameTest {
	
// Here is the format of the scores: "player1Score - player2Score"
// "love - love"
// "15 - 15"
// "30 - 30"
// "deuce"
// "15 - love", "love - 15"
// "30 - love", "love - 30"
// "40 - love", "love - 40"
// "30 - 15", "15 - 30"
// "40 - 15", "15 - 40"
// "player1 has advantage"
// "player2 has advantage"
// "player1 wins"
// "player2 wins"
	@Test
	public void testTennisGame_Start() {
		//Arrange
		TennisGame game = new TennisGame();
		//Act
		String score = game.getScore() ;
		// Assert
		assertEquals("Initial score incorrect", "love - love", score);		
	}
	
	@Test
	public void testTennisGame_EahcPlayerWin4Points_Score_Deuce() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		
		game.player1Scored();
		game.player2Scored();
		//Act
		
		String score = game.getScore() ;
		// Assert
		assertEquals("Tie score incorrect", "deuce", score);		
	}
	
	@Test (expected = TennisGameException.class)
	public void testTennisGame_Player1WinsPointAfterGameEnded_ResultsException() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		//Act
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		//Act
		// This statement should cause an exception
		game.player1Scored();			
	}	
	
	@Test (expected = TennisGameException.class)
	public void testTennisGame_Player2WinsPointAfterGameEnded_ResultsException() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		//Act
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		//Act
		// This statement should cause an exception
		game.player2Scored();			
	}	
	
	@Test
	public void testTennisGame_bothPlayerScoreThreeTimes_resultDeuce() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		
		//Act
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		
		String score = game.getScore();
		
		//Assert
		assertEquals("Score should be deuce", "deuce", score);
		
	}
	
	@Test
	public void testTennisGame_player1HasAdvantage_resultPlayer1Advantage() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		
		//Act
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		
		game.player1Scored();
		
		String score = game.getScore();
		//Assert
		assertEquals("Player1 should have advantage", "player1 has advantage", score);
		
	}
	@Test
	public void testTennisGame_player2HasAdvantage_resultPlayer2Advantage() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		
		//Act
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		
		game.player2Scored();
		
		String score = game.getScore();
		
		//Assert
		assertEquals("Player2 should have advantage", "player2 has advantage", score);
		
	}
	
	@Test
	public void testTennisGame_player1Wins_Result0_4() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		
		//Act
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		
		
		String score = game.getScore();
		
		//Assert
		assertEquals("Player1 should win", "player1 wins", score);
	}
	
	@Test
	public void testTennisGame_player2Wins_Result4_0() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		
		//Act
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		
		String score = game.getScore();
		
		//Assert
		assertEquals("Player2 should win", "player2 wins", score);
	}
	
	@Test
	public void testTennisGame_player1ScoresOnce_Result1_0() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		
		//Act
		game.player1Scored();
		
		String score = game.getScore();
		
		//Assert
		assertEquals("Score should be 15 - love", "15 - love", score);
	}
	
	@Test
	public void testTennisGame_player2ScoresOnce_Result0_1() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		
		//Act
		game.player2Scored();
		
		String score = game.getScore();
		
		//Assert
		assertEquals("Score should be love - 15", "love - 15", score);
	}
	
	@Test
	public void testTennisGame_player1Leads40_30_Result40_30() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		
		//Act
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		
		game.player2Scored();
		game.player2Scored();
		
		String score = game.getScore();
		
		//Assert
		assertEquals("Score should be 40 - 30", "40 - 30", score);
	}
	
	@Test 
	public void testTennisGame_BothPlayersScoreOnce_Result15_15() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		
		//Act
		game.player1Scored();
		
		game.player2Scored();
		
		String score = game.getScore();
		//Assert
		assertEquals("Score should be 15 - 15", "15 - 15", score);
	}	
	
	
}
