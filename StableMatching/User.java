package StableMatching;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class User {

    private String name;
    private File userImage;
    private boolean isHost;
    public boolean isUserHost(){
        return this.isHost;
    }
    private boolean isMale;
    public boolean userIsMale(){
        return this.isMale;
    }
    private Object userOut;
    public User(String name, File userImage, boolean isMale, boolean isHost){
        this.name = name;
        this.userImage = userImage;
        this.isMale = isMale;
        this.isHost = isHost;
    }
    public User(String name, boolean isMale, boolean isHost){
        this.name = name;
        this.userImage = null;
        this.isMale = isMale;
        this.isHost = isHost;
    }
   private boolean isInLobby;
   public boolean isUserInLobby() {
       return this.isInLobby;
   }
   public void setUserInLobby(boolean isInLobby) {
       this.isInLobby = isInLobby;
   }
   private UserLeaveLobbyEventHandler userLeaveLobbyEventHandler;
   public void registerUserLeaveLobbyEventHandler(UserLeaveLobbyEventHandler userLeaveLobbyEventHandler) {
       this.userLeaveLobbyEventHandler = userLeaveLobbyEventHandler;
   }
    public void unregisterUserLeaveLobbyEventHandler(){
        this.userLeaveLobbyEventHandler = null;
    }
    private boolean isMatched;
    private User userMatch;
    private boolean isReady;
    public boolean isUserReady(){
        return this.isReady;
    }
    public void setUserReady(boolean isReady){
        this.isReady = isReady;
        readyEventHandler.dispatch(this);
    }
    private UserReadyEventHandler readyEventHandler;
    public void registerUserReadyEventHandler(UserReadyEventHandler readyEventHandler){
        this.readyEventHandler = readyEventHandler;
    }
    public void unregisterUserReadyEventHandler(){
        this.readyEventHandler = null;
    }

    private boolean hasSelected;
    private List<User> userSelection;
    public void unsetUserSelection(){
        this.userSelection = null;
        this.hasSelected = false;
    }
    public boolean hasUserSelected(){
        return this.hasSelected;
    }
    public void setUserSelected(boolean hasSelected){
        this.hasSelected = hasSelected;

    }
    public void setUserSelection(List<User> userSelection){
        this.userSelection = userSelection;
        userSelectedEventHandler.dispatch(this);
    }
    private UserSelectedEventHandler userSelectedEventHandler;
    public void registerUserSelectedEventHandler(UserSelectedEventHandler userSelectedEventHandler){
        this.userSelectedEventHandler = userSelectedEventHandler;
    }
    public void unregisterUserSelectedEventHandler(){
        this.userSelectedEventHandler = null;
    }
    public void resetUser(){
        unsetUserSelection();
        setUserReady(false);
    }
    public void disconnectUserFromLobby(){
        resetUser();
        unregisterUserReadyEventHandler();
        unregisterUserSelectedEventHandler();
        userLeaveLobbyEventHandler.dispatch(this);
        unregisterUserLeaveLobbyEventHandler();
    }
}
