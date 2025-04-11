package StableMatching;

public class Main {

    public static void main(String args[]){

        Lobby test = new Lobby(4);
        User u1 = new User("Andy",true, true);
        User u2 =new User("Patrick",true, false);
        User u3 = new User("Catherina",false, false);
        User u4 = new User("Shanique",false, false);

        test.addUserToLobby(u1);
        test.addUserToLobby(u2);
        test.addUserToLobby(u3);
        test.addUserToLobby(u4);
        u1.setUserReady(true);
        u2.setUserReady(true);
        u3.setUserReady(true);
        u4.setUserReady(true);
        u1.disconnectUserFromLobby();





    }
}
