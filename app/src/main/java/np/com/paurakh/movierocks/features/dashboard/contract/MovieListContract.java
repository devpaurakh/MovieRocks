package np.com.paurakh.movierocks.features.dashboard.contract;

import java.util.List;

import np.com.paurakh.movierocks.features.dashboard.model.Movies;

// Contract interface for the movie list feature
public interface MovieListContract {

    // Interface for the Model layer of the feature
    interface Model{

        // Interface for callback to be implemented by the presenter
        interface onFinishListener{
            void onFinished(List<Movies> moviesList); // Called when movie list is successfully fetched from server
            void onFailure(Throwable t); // Called when there is an error while fetching movie list
        }

        // Method to get the popular movie list from server
        void getMovieList(onFinishListener onFinishListener, int pageNo);

        // Method to get the upcoming movie list from server
        void getUpComingMovieList(onFinishListener onFinishListener, int pageNo);
    }

    // Interface for the View layer of the feature
    interface View{

        // Method to show loading progress
        void showProgress();

        // Method to hide loading progress
        void hideProgress();

        // Method to set fetched movie list to the RecyclerView
        void setDataToRecyclerView(List<Movies> moviesList);

        // Method to handle error while fetching movie list
        void onResponseFailure(Throwable throwable);
    }

    // Interface for the Presenter layer of the feature
    interface Presenter{

        // Method to destroy the presenter and free up resources
        void onDestroy();

        // Method to fetch more data from server for pagination
        void getMoreData(int pageNo);

        // Method to request initial data from server
        void requestDataFromServer();
    }
}
