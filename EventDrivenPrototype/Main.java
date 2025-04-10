package EventDrivenPrototype;

import EventDrivenPrototype.Events.*;

import java.util.ArrayList;

public class Main {

    public static void main(String args[]){
        User u1 = new User("Andy");
        User u2 = new User("George");
        User u3 = new User("Patrick");
        User u4 = new User("Jordan");

        //Regular Lobby Flow
        //User Join
        new UserCreateLobbyEvent(u1);
        new UserJoinLobbyEvent(u2,u1);
        new UserJoinLobbyEvent(u3,u1);
        new UserJoinLobbyEvent(u4,u1);
        //User Ready Up
        new UserReadyUpEvent(u1);
        new UserReadyUpEvent(u2);
        new UserReadyUpEvent(u3);
        new UserReadyUpEvent(u4);
        //User Upload
        new UserUploadedFileEvent(u1, null);
        new UserUploadedFileEvent(u2, null);
        new UserUploadedFileEvent(u3, null);
        new UserUploadedFileEvent(u4, null);
        //User Select
        new UserSelectedEvent(u1);
        new UserSelectedEvent(u2);
        new UserSelectedEvent(u3);
        new UserSelectedEvent(u4);

    }
}
