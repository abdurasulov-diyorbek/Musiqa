package android.example.musiqa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.io.Serializable;

public class SongsList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songs_list);

        ArrayList<Music> songs = new ArrayList<>();
        songs.add(new Music("Bailando","Enrique", R.raw.bailando));
        songs.add(new Music("Enrique","Iglesias", R.raw.enrique));
        songs.add(new Music("Ring my bells", "Enrique", R.raw.ring_my_bells));

        MusicAdapter adapter = new MusicAdapter(this, songs);
        ListView listView = findViewById(R.id.songList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String songName = listView.getItemAtPosition(position).toString();
                Intent intent = new Intent(SongsList.this, MainActivity.class);
                intent.putExtra("song", songs);
                intent.putExtra("name", songName);
                intent.putExtra("position", position);
                startActivity(intent);


            }
        });
    }
}

