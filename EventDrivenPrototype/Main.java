package EventDrivenPrototype;

import EventDrivenPrototype.Events.*;

import java.util.ArrayList;

public class Main {

    public static void main(String args[]){
        User u1 = new User("Andy");
        new UserCreateLobbyEvent(u1);
        User u2 = new User("George");
        User u3 = new User("Partrick");
        User u4 = new User("Jordan");
        new UserJoinLobbyEvent(u2, u1);
        new UserJoinLobbyEvent(u3, u1);
        new UserJoinLobbyEvent(u4, u1);
        new UserReadyUpEvent(u1);
        new UserReadyUpEvent(u2);
        new UserReadyUpEvent(u3);
        new UserReadyUpEvent(u4);
        new UserLeaveLobbyEvent(u1);
        new UserJoinLobbyEvent(u1, u3);

    }
}
