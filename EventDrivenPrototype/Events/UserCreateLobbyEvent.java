package EventDrivenPrototype.Events;

import EventDrivenPrototype.Lobby;

public class UserCreateLobbyEvent extends Event{

    private User user;
    public UserCreateLobbyEvent(User user) {
        //perform action associated with event
        this.user = user;
        user.lobby = new Lobby(user, user.toString());
        //Dispatch our own event
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
