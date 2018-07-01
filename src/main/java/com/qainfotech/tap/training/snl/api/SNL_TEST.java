package com.qainfotech.tap.training.snl.api;

import java.io.*;
import java.util.UUID;
import org.json.*;
import org.testng.annotations.Test;

public class SNL_TEST {
	
	//Test for checking the uniqueness of the game player
	@Test (description="player uniqueness test", expectedExceptions= {PlayerExistsException.class})
	public void Player_Uniqueness_Test()throws GameInProgressException,
	InvalidTurnException, MaxPlayersReachedExeption, PlayerExistsException, NoUserWithSuchUUIDException, IOException{
		Board Register_Player=new Board();
		Register_Player.registerPlayer("Mayank");
		Register_Player.registerPlayer("Mayank");
		System.out.println("Throws the PlayerExistsException");
	}
	
	//Test for checking the maximum number of players
	@Test (description="maximum number of players test", expectedExceptions= {MaxPlayersReachedExeption.class})
	public void Max_Number_of_Players_Test()throws GameInProgressException, InvalidTurnException,
	MaxPlayersReachedExeption, PlayerExistsException, NoUserWithSuchUUIDException, IOException{
		Board Register_Player=new Board();
		Register_Player.registerPlayer("Player 1");
		Register_Player.registerPlayer("Player 2");
		Register_Player.registerPlayer("Player 3");
		Register_Player.registerPlayer("Player 4");
		Register_Player.registerPlayer("Player 5");
		System.out.println("Throws MaxPlayersReachedExeption when number of players are more than 4");
	}
	
	//Test for registering a new player in middle of a game
	@Test (description="Register Player in middle of game test", expectedExceptions= {GameInProgressException.class})
	public void Register_Player_In_Middle_Of_Game_Test()throws GameInProgressException, InvalidTurnException,
	MaxPlayersReachedExeption, PlayerExistsException, NoUserWithSuchUUIDException, IOException{
		Board Register_Player=new Board();
		Register_Player.registerPlayer("Player 1");
		Register_Player.registerPlayer("Player 2");
		JSONObject Get_Data=Register_Player.getData();
		JSONArray Data_Array=Get_Data.getJSONArray("Players");
		JSONObject One_Player=Data_Array.getJSONObject(0);
		One_Player.put("position", 10);
		Register_Player.registerPlayer("Player 3");
		System.out.println("Throws GameInProgressException when player is registered in middle of a game");
	}
	
	//Test for checking the turn of a player
	@Test (description="Invalid turn test", expectedExceptions= {InvalidTurnException.class})
	public void Invalid_Turn_Test() throws GameInProgressException, InvalidTurnException,
		MaxPlayersReachedExeption, NoUserWithSuchUUIDException, IOException, JSONException, PlayerExistsException{
		 Board board=new Board();
		 UUID player1=UUID.fromString((String) board.registerPlayer("Player 1").getJSONObject(0).get("uuid"));
		 UUID player2=UUID.fromString((String) board.registerPlayer("Player 2").getJSONObject(0).get("uuid"));
         board.rollDice(player2);
         System.out.println("Throws Invalid Turn Exception when wrong player roll the dice");
	}
	
	//Test for deleting a player
	@Test (description="Delete a user test")
	public void Delete_User_Test()throws GameInProgressException, InvalidTurnException, MaxPlayersReachedExeption,
	NoUserWithSuchUUIDException, IOException, JSONException, PlayerExistsException{
		Board Register_Player=new Board();
		UUID player = (UUID)Register_Player.registerPlayer("Player 1").getJSONObject(0).get("uuid");
		System.out.println(Register_Player.deletePlayer(player));
	}
	
}
