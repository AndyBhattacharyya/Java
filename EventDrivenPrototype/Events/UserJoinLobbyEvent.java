package EventDrivenPrototype.Events;

import EventDrivenPrototype.Lobby;

public class UserJoinLobbyEvent extends Event {
    private User user;
    public UserJoinLobbyEvent(User user, Lobby lobby) {
        //perform action associated with event
        this.user = user;
        user.lobby = lobby;
    }
    //useful aux methods for events
    public Lobby getUserLobby(){
        return user.lobby;
    }

    public User getUser() {
        return user;
    }
}
