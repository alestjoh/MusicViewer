package com.example.musicviewer;

import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MusicListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MusicListFragment extends Fragment implements Callback<MusicList> {
    private static final String API_TARGET = "com.example.musicviewer.api_target";

    private String apiTarget = "NO TARGET YET GIVEN";
    private static ApiInterface api;

    private RecyclerView recyclerView;

    public MusicListFragment() {
        // Required empty public constructor
    }

    public static MusicListFragment newInstance(String apiTarget) {
        MusicListFragment fragment = new MusicListFragment();
        Bundle args = new Bundle();
        args.putString(API_TARGET, apiTarget);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            apiTarget = getArguments().getString(API_TARGET);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewGroup = inflater.inflate(
                R.layout.fragment_music_list, container, false);

        recyclerView = viewGroup.findViewById(R.id.recycler_view_fragment);

        if (api == null) {
            initializeRetrofit();
        }

        if (apiTarget.equals(getResources().getString(R.string.rock))) {
            recyclerView.setBackgroundColor(
                    ContextCompat.getColor(container.getContext(), R.color.rockColor));
            api.getRockMusic().enqueue(this);
        } else if (apiTarget.equals(getResources().getString(R.string.classic))) {
            recyclerView.setBackgroundColor(
                    ContextCompat.getColor(container.getContext(), R.color.classicColor));
            api.getClassicMusic().enqueue(this);
        } else {
            recyclerView.setBackgroundColor(
                    ContextCompat.getColor(container.getContext(), R.color.popColor));
            api.getPopMusic().enqueue(this);
        }

        return viewGroup;
    }

    private void initializeRetrofit() {
        Retrofit rf = new Retrofit.Builder()
                .baseUrl("https://itunes.apple.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = rf.create(ApiInterface.class);
    }

    /**
     * Invoked for a received HTTP response.
     * <p>
     * Note: An HTTP response may still indicate an application-level failure such as a 404 or 500.
     * Call {@link Response#isSuccessful()} to determine if the response indicates success.
     *
     * @param call
     * @param response
     */
    @Override
    public void onResponse(Call<MusicList> call, Response<MusicList> response) {
        //tv.setText(response.body().getResults().get(0).getTrackName());
    }

    /**
     * Invoked when a network exception occurred talking to the server or when an unexpected
     * exception occurred creating the request or processing the response.
     *
     * @param call
     * @param t
     */
    @Override
    public void onFailure(Call<MusicList> call, Throwable t) {
        Toast.makeText(
                this.getActivity().getBaseContext(),
                "Error accessing music database. :(",
                Toast.LENGTH_SHORT).show();
    }
}
