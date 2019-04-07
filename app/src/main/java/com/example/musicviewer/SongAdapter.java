package com.example.musicviewer;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongHolder> {

    private MusicList data;
    private Context context;

    public SongAdapter(MusicList data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public SongHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new SongHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.song_data_layout, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SongHolder songHolder, int i) {
        SongItem song = data.getResults().get(i);

        songHolder.collectionName.setText(song.getCollectionName());
        songHolder.price.setText("$" + song.getTrackPrice());
        songHolder.artist.setText(song.getArtistName());

        songHolder.cardView.setOnClickListener(
                view -> playPreview(song.getPreviewUrl()));

        Picasso.get().load(song.getArtworkUrl60()).into(songHolder.albumCover);
    }

    private void playPreview(String songUrl) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(songUrl));
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
        } else {
            Toast.makeText(
                    context,
                    "No music player was found. :(",
                    Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public int getItemCount() {
        return data.getResultCount();
    }

    public class SongHolder extends RecyclerView.ViewHolder {

        ImageView albumCover;
        TextView collectionName, artist, price;
        CardView cardView;

        public SongHolder(@NonNull View itemView) {
            super(itemView);

            albumCover = itemView.findViewById(R.id.iv_album_cover_song);
            collectionName = itemView.findViewById(R.id.tv_title_song);
            artist = itemView.findViewById(R.id.tv_artist_song);
            price = itemView.findViewById(R.id.tv_price_song);
            cardView = itemView.findViewById(R.id.card_view_song);
        }
    }
}
