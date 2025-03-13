package EventDrivenPrototype.Events;

import EventDrivenPrototype.Lobby;

public class UserLeaveLobbyEvent extends Event{
    private User user;
    public UserLeaveLobbyEvent(User user) {
        //perform action associated with event
        this.user = user;
        user.lobby = null;
        EventDispatcher.dispatch(this);
    }
    //useful aux methods for events
    public Lobby getUserLobby(){
        return user.lobby;
    }

    public User getUser() {
        return user;
    }
}
