package StableMatching;

public class Main {

    public static void main(String args[]){
        Lobby test = new Lobby(4);
        Lobby.User u1 = test.addUserToLobby("Andy", true);
        Lobby.User u2 = test.addUserToLobby("Andy", false);
        Lobby.User u3 = test.addUserToLobby("Andy", false);
        Lobby.User u4 = test.addUserToLobby("Andy", false);
        u1.userSetReady(true);
        u2.userSetReady(true);
        u3.userSetReady(true);
        u4.userSetReady(true);
        u1.userSetSelection();
        u2.userSetSelection();
        u3.userSetSelection();
        u4.userSetSelection();

    }
}
