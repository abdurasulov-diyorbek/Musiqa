package android.example.musiqa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MusicAdapter extends ArrayAdapter<Music> {
    public MusicAdapter(Context context, ArrayList<Music> songs) {
        super(context, 0, songs);
    }

    @Override
    public View getView(int position,View convertView,ViewGroup parent) {
        Music currentSong = getItem(position);

            View listItemView = convertView;
            if(listItemView == null){
                listItemView = LayoutInflater.from(getContext()).inflate(R.layout.single_song, parent, false);
            }
            TextView songName = listItemView.findViewById(R.id.music_name);
            songName.setText(currentSong.getSongName());
            return listItemView;
    }

    public String getName() {
        return "hello";
    }
}
