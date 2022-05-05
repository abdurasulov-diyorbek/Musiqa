package android.example.musiqa;

import java.io.Serializable;

public class Music implements Serializable {
    String SongName;
    String Singer;
    int song;
    public Music(String SongName, String Singer, int song){
        this.SongName = SongName;
        this.Singer = Singer;
        this.song = song;
    }

    public String getSongName() {
        return SongName;
    }

    public String getSinger(){
        return Singer;
    }

    public int getSong(){
        return song;
    }
}
