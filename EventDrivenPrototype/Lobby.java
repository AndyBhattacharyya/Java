package EventDrivenPrototype;

import EventDrivenPrototype.Events.*;

import java.io.PipedReader;
import java.util.HashSet;
import java.util.Set;


enum GAMESTATE {
    READYUP, UPLOAD, SELECT, DISPLAY
    /*
    Encapsulate Game state transition as enums, directly modifying the Lobby object passed to it
    with methods
    Ex: A user leaving during the readyup will require less action then a user leaving during upload
     */


}

public class Lobby {

    private User host;
    private Set<User> users;
    private int usersReady;
    private int maxUsers;


    private GAMESTATE gameState;

    public Lobby(User host){
        /*
        Thread Initialization
         */
        this.host = host;
        users = new HashSet<>();
        users.add(host);
        maxUsers =  4;
        usersReady = 0;
        gameState = GAMESTATE.READYUP;
        //review anonymous inner class scoping: Lobby.this construct
        EventDispatcher.registerHandler(UserCreateLobbyEvent.class, this::UserCreateLobbyEventHandler);
        EventDispatcher.registerHandler(UserJoinLobbyEvent.class, this::UserJoinLobbyEventHandler);
        EventDispatcher.registerHandler(UserReadyUpEvent.class, this::UserReadyUpEventHandler);
        EventDispatcher.registerHandler(UserLeaveLobbyEvent.class, this::UserLeaveLobbyEventHandler);
         }

    //set up event handler instance method references
    public void UserLeaveLobbyEventHandler(UserLeaveLobbyEvent uEvent){
        if(users.contains(uEvent.getUser())){
            users.remove(uEvent.getUser());
            if(users.isEmpty()){
                /*
                Handle "disbanding" lobby
                 */
            }
            if(uEvent.getUser() == host){
                /*
                switch lobby hosts when hosts decides to leave, so the lobby is still good
                 */
                host = users.iterator().next();
                System.out.println("User " + host + " is now the lobby host of " + this);
            }
            usersReady--;
            System.out.println("User " + uEvent.getUser() + " left the lobby " + this);
        }
    }

    public void UserReadyUpEventHandler(UserReadyUpEvent uEvent){
        if(this==uEvent.getUserLobby()){
            System.out.println("User " + uEvent.getUser() + " is ready in lobby " + this);
            usersReady++;
            if(usersReady == maxUsers){
                gameState = GAMESTATE.UPLOAD;
                System.out.println("Lobby " + this + " has begun");
            }
        }
    }
    public void UserCreateLobbyEventHandler(UserCreateLobbyEvent uEvent){
        //this check ensures that our lobby handles the associated event and not others
        if(this==uEvent.getUserLobby()){
            System.out.println("User " + this.host + " has created this lobby: " + this);
        }
    }

    public void UserJoinLobbyEventHandler(UserJoinLobbyEvent uEvent){
        if(this==uEvent.getUserLobby()){
            if(users.size() < 4 && users.add(uEvent.getUser())) {
                System.out.println("User " + uEvent.getUser() + " has joined this lobby: " + this.host);
            }
        }
    }


}
