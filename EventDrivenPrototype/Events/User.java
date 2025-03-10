package EventDrivenPrototype.Events;

import EventDrivenPrototype.Lobby;

public class User {
    private String name;
    boolean isReady;
    boolean isUploading;
    boolean hasSelected;
    Lobby lobby;

    public User(String name){
        this.name=name;
        this.isReady=false;
        this.isUploading=false;
        this.hasSelected=false;
        this.lobby = null;
    }
    public String toString(){
        return this.name;
    }
}
