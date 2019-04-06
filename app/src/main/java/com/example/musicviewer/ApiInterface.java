package com.example.musicviewer;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Tab 1:
 * https://itunes.apple.com/search?term=rock&amp;amp;media=music&amp;amp;entity=song&amp;amp;limit=50
 * Tab 2:
 * https://itunes.apple.com/search?term=classick&amp;amp;media=music&amp;amp;entity=song&amp;amp;limit=50
 * Tab 3:
 * https://itunes.apple.com/search?term=pop&amp;amp;media=music&amp;amp;entity=song&amp;amp;limit=50
 */

public interface ApiInterface {
    @GET("search?term=rock&amp;amp;media=music&amp;amp;entity=song&amp;amp;limit=50")
    Call<MusicList> getRockMusic();

    @GET("search?term=classick&amp;amp;media=music&amp;amp;entity=song&amp;amp;limit=50")
    Call<MusicList> getClassicMusic();

    @GET("search?term=pop&amp;amp;media=music&amp;amp;entity=song&amp;amp;limit=50")
    Call<MusicList> getPopMusic();
}
