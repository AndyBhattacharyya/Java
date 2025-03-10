package EventDrivenPrototype;

import EventDrivenPrototype.Events.User;
import EventDrivenPrototype.Events.UserCreateLobbyEvent;
import EventDrivenPrototype.Events.UserJoinLobbyEvent;

public class Lobby {

    private User host;
    public Lobby(){

    }

    //set up event handler instance method references
    public void UserCreateLobbyEventHandler(UserCreateLobbyEvent uEvent){
        if(this==uEvent.getUserLobby()){
            host = uEvent.getUser();
            System.out.println("User has created this lobby: " + this.host);
        }
    }

    public void UserJoinLobbyEventHandler(UserJoinLobbyEvent uEvent){
        if(this==uEvent.getUserLobby()){
            host = uEvent.getUser();
            System.out.println("User has created this lobby: " + this.host);
        }
    }


}
