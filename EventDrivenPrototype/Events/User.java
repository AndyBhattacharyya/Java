package EventDrivenPrototype.Events;

import EventDrivenPrototype.Lobby;

import java.io.File;

public class User {
    private String name;
    boolean isReady;
    boolean isUploading;
    boolean hasSelected;
    Lobby lobby;
    File userUploadedFile;

    public User(String name){
        this.name=name;
        this.isReady=false;
        this.isUploading=false;
        this.hasSelected=false;
        this.lobby = null;
        this.userUploadedFile = null;
    }
    public String toString(){
        return this.name;
    }
    public boolean hasUploadedFile(){
        return this.userUploadedFile!=null;
    }
}
