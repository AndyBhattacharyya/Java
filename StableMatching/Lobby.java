package StableMatching;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

enum GAMESTATE{
    WAITING, READYUP, SELECT, MATCH, DISPLAY
}

public class Lobby {

    private int maxPlayers;
    private int currentPlayers;
    private int usersReady;
    private int usersSelected;
    private GAMESTATE gameState;

    private List<User> usersMale;
    private List<User> usersFemale;
    private void computeUserMatchings(){
        System.out.println("Matches being computed");
    }


    public Lobby(int maxPlayers){
        this.maxPlayers = maxPlayers;
        currentPlayers = 0;
        usersReady = 0;
        usersSelected = 0;
        gameState = GAMESTATE.WAITING;
        usersMale = new ArrayList<>();
        usersFemale = new ArrayList<>();
    }

    public void displayLobby(){
        System.out.println("Lobby is now in state: " + gameState);
    }

    //also functions as user joins/creates lobby event
    public void addUserToLobby(User user){
        if(currentPlayers<maxPlayers) {
            if (user.isUserInLobby()) {
                user.disconnectUserFromLobby();
            }
            user.setUserInLobby(true);
            if (user.userIsMale())
                usersMale.add(user);
            else
                usersFemale.add(user);

            //User created lobby
            currentPlayers++;
            if (currentPlayers == maxPlayers) {
                gameState = GAMESTATE.READYUP;
            }
            user.registerUserReadyEventHandler(this::userReadyEvent);
            user.registerUserSelectedEventHandler(this::userSelectedEvent);
            user.registerUserLeaveLobbyEventHandler(this::userLeaveEvent);
        }
        else
            System.out.println("Lobby is full");
        displayLobby();
    }

    public void userLeaveEvent(User user){
        if(currentPlayers>0){
            currentPlayers--;
            if(user.userIsMale())
                usersMale.remove(user);
            else
                usersFemale.remove(user);

            //Make them redo readyup/selections
            for(User member : usersMale){
                member.resetUser();
            }

            for(User member : usersFemale){
                member.resetUser();
            }
            usersReady = 0;
            usersSelected = 0;
            //current players will be less than max but need to reset user selections
            gameState = GAMESTATE.WAITING;
        }
        displayLobby();
    }

    public void userReadyEvent(User user){
        boolean isReady = user.isUserReady();
        if(isReady && usersReady < maxPlayers){
            usersReady++;
            if(usersReady == maxPlayers){
                gameState = GAMESTATE.SELECT;
            }
        }
        else{
            if(usersReady > 0){
                usersReady--;
                if(gameState == GAMESTATE.SELECT){
                    usersSelected--;
                    user.unsetUserSelection();
                    gameState = GAMESTATE.READYUP;
                }
                else if(gameState == GAMESTATE.MATCH){
                    usersSelected--;
                    user.unsetUserSelection();
                    gameState = GAMESTATE.READYUP;
                }
            }
        }
        displayLobby();
    }

    public void userSelectedEvent(User user){
        //don't care if user resubmits selection, won't affect anything
        if(!user.hasUserSelected()) {
            usersSelected++;
            user.setUserSelected(true);
        }
        if(usersSelected == maxPlayers){
            gameState = GAMESTATE.MATCH;
            computeUserMatchings();
        }
        displayLobby();
    }





}
