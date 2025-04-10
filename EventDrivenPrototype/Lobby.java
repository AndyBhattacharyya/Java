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
    private int usersUploaded;
    private int maxUsers;
    private int usersSelected;
    private String lobbyName;


    private GAMESTATE gameState;

    public Lobby(User host, String lobbyName){
        this.host = host;
        this.lobbyName = lobbyName;
        users = new HashSet<>();
        users.add(host);
        maxUsers =  4;
        usersReady = 0;
        usersUploaded = 0;
        usersSelected = 0;
        gameState = GAMESTATE.READYUP;
        //review anonymous inner class scoping: Lobby.this construct
        EventDispatcher.registerHandler(UserCreateLobbyEvent.class, this::UserCreateLobbyEventHandler);
        EventDispatcher.registerHandler(UserJoinLobbyEvent.class, this::UserJoinLobbyEventHandler);
        EventDispatcher.registerHandler(UserReadyUpEvent.class, this::UserReadyUpEventHandler);
        EventDispatcher.registerHandler(UserLeaveLobbyEvent.class, this::UserLeaveLobbyEventHandler);
        EventDispatcher.registerHandler(UserUploadedFileEvent.class, this::UserUploadedFileEventHandler);
        EventDispatcher.registerHandler(UserSelectedEvent.class, this::UserSelectedEventHandler);
    }


    //set up event handler instance method references
    public void UserSelectedEventHandler(UserSelectedEvent uEvent){
        if(uEvent.getUserLobby() == this){
            System.out.println(uEvent.getUser() + "has made their selection");
            usersSelected++;
            if(usersSelected == maxUsers){
                gameState = GAMESTATE.DISPLAY;
                System.out.println(GAMESTATE.DISPLAY);
            }
        }

    }


    public void UserUploadedFileEventHandler(UserUploadedFileEvent uEvent){
        if(uEvent.getUserLobby() == this && gameState != GAMESTATE.SELECT) {
            usersUploaded++;
            System.out.println("User  " + uEvent.getUser() + "has uploaded a file");
            if (usersUploaded == maxUsers) {
                gameState = GAMESTATE.SELECT;
                System.out.println(GAMESTATE.SELECT);
            }
        }
    }


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
            usersSelected--;
            System.out.println("User " + uEvent.getUser() + " left the lobby " + this);

            if (gameState != GAMESTATE.READYUP) {
                gameState = GAMESTATE.READYUP;
                System.out.println(GAMESTATE.READYUP);
            }

        }
    }

    public void UserReadyUpEventHandler(UserReadyUpEvent uEvent){
        if(this==uEvent.getUserLobby()){
            System.out.println("User " + uEvent.getUser() + " is ready in lobby " + this);
            usersReady++;
            if(usersReady == maxUsers && usersUploaded ==maxUsers){
                gameState = GAMESTATE.SELECT;
                System.out.println(GAMESTATE.SELECT);
            }
            else if (usersReady == maxUsers) {
                gameState = GAMESTATE.UPLOAD;
                System.out.println(GAMESTATE.UPLOAD);
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
                if(uEvent.getUser().hasUploadedFile()){
                    usersUploaded++;
                }
            }
        }
    }


}
