package np.com.paurakh.movierocks.features.dashboard.dashboardpages;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import np.com.paurakh.movierocks.R;
import np.com.paurakh.movierocks.features.dashboard.contract.MovieListContract;
import np.com.paurakh.movierocks.features.dashboard.dashboardpages.adapter.MovieAdapter;
import np.com.paurakh.movierocks.features.dashboard.dashboardpages.adapter.UpComingAdapter;
import np.com.paurakh.movierocks.features.dashboard.model.Movies;
import np.com.paurakh.movierocks.features.dashboard.presenter.MoviePresenter;
import np.com.paurakh.movierocks.features.dashboard.presenter.UpComingPresenter;


public class UpcomingFragment extends Fragment implements MovieListContract.View {

    private RecyclerView movieRecyclerView;
    private List<Movies> moviesList;
    private UpComingAdapter upComingAdapter;
    private ProgressBar progressBar;
    private Context context;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_upcoming, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        movieRecyclerView = view.findViewById(R.id.rvUpComingMovieListing);
        progressBar=view.findViewById(R.id.pbUpComingMoviesProgressing);
        moviesList = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        movieRecyclerView.setLayoutManager(linearLayoutManager);
        movieRecyclerView.setHasFixedSize(true);

        UpComingPresenter moviePresenter = new UpComingPresenter(this);
        moviePresenter.requestDataFromServer();
    }
    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideProgress() {

        progressBar.setVisibility(View.GONE);

    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void setDataToRecyclerView(List<Movies> moviesList) {

        this.moviesList.addAll(moviesList);
        if (upComingAdapter == null) {
            upComingAdapter = new UpComingAdapter(this.moviesList, context);
            movieRecyclerView.setAdapter(upComingAdapter);
        } else {
            upComingAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void onResponseFailure(Throwable throwable) {

        Log.e("error",throwable.getMessage());
        Toast.makeText(context,"error",Toast.LENGTH_SHORT).show();

    }
}