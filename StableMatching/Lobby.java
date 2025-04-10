package StableMatching;

import java.io.File;

enum EVENT{
    LOBBYCREATED, USERJOINED, USERLEFT, USERREADY, USERUNREADY, USERSELECTED,
}

enum GAMESTATE{
    WAITING, READYUP, SELECT, MATCH, DISPLAY
}

public class Lobby {

    private int maxPlayers;
    private int currentPlayers;
    private int userReady;
    private int userSelected;
    private GAMESTATE gameState;

    public Lobby(int maxPlayers){
        this.maxPlayers = maxPlayers;
        currentPlayers = 0;
        userReady = 0;
        userSelected = 0;
        gameState = GAMESTATE.WAITING;
        outLobbyState();
    }

    public void outLobbyState(){
        String message = "Lobby is now in State: " + this.gameState + "\n";
        System.out.println(message);
    }

    public void lobbyCreatedEventHandler(){
        currentPlayers++;
        outLobbyState();
    }

    public void userJoinedOrLeftEventHandler(boolean hasJoined){
        if(hasJoined && currentPlayers < maxPlayers){
            currentPlayers++;
            if(currentPlayers == maxPlayers)
                gameState = GAMESTATE.READYUP;
        }
        else{
            if(currentPlayers > 0) {
                currentPlayers--;
                gameState = GAMESTATE.WAITING;
            }
            if(currentPlayers == 0)
                System.out.println("All users have left the lobby");
        }
        outLobbyState();
    }
    public void userReadyOrUnreadyEventHandler(boolean isReady){
        if(isReady && userReady < maxPlayers){
            userReady++;
            if(userReady == maxPlayers)
                gameState = GAMESTATE.SELECT;
        }
        else{
            if(userReady > 0) {
                userReady--;
                if(gameState == GAMESTATE.SELECT && userSelected > 0){
                    userSelected--;
                    gameState = GAMESTATE.READYUP;
                }
            }
            if(userReady == 0)
                System.out.println("All users have unready in the lobby");
        }
        outLobbyState();
    }

    public void userSelectedEventHandler(){
        if(userSelected < maxPlayers){
            userSelected++;
            if(userSelected == maxPlayers)
                gameState = GAMESTATE.MATCH;
        }
        outLobbyState();
    }

    public void dispatchLobbyEvents(EVENT event){
        switch(event){
            case LOBBYCREATED:
                lobbyCreatedEventHandler();
                break;
            case USERJOINED:
                userJoinedOrLeftEventHandler(true);
                break;
            case USERLEFT:
                userJoinedOrLeftEventHandler(false);
                break;
            case USERREADY:
                userReadyOrUnreadyEventHandler(true);
                break;
            case USERUNREADY:
                userReadyOrUnreadyEventHandler(false);
                break;
            case USERSELECTED:
                userSelectedEventHandler();
                break;
            default:
                System.out.println("Unknown event, error");
        }
    }

    public Lobby.User addUserToLobby(String name, File userImage, boolean isHost){
        return new Lobby.User(name, userImage, isHost);
    }
    public Lobby.User addUserToLobby(String name, boolean isHost){
        return new Lobby.User(name, isHost);
    }

    class User{
        private String name;
        private File userImage;
        private boolean isHost;
        private boolean isReady;

        public User(String name, File userImage, boolean isHost){
            this.name = name;
            this.userImage = userImage;
            this.isHost = isHost;
            if(isHost)
                dispatchLobbyEvents(EVENT.LOBBYCREATED);
            else
                dispatchLobbyEvents(EVENT.USERJOINED);
        }

        public User(String name, boolean isHost){
            this.name = name;
            this.userImage = null;
            if(isHost)
                dispatchLobbyEvents(EVENT.LOBBYCREATED);
            else
                dispatchLobbyEvents(EVENT.USERJOINED);
        }

        public void userSetReady(boolean isReady){
            this.isReady = isReady;
            if(this.isReady)
                dispatchLobbyEvents(EVENT.USERREADY);
            else
                dispatchLobbyEvents(EVENT.USERUNREADY);
        }

        public void userSetSelection(){
            dispatchLobbyEvents(EVENT.USERSELECTED);
        }

    }









}
