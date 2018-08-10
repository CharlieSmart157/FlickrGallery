package example.code.flickrgallery.gallery;

import android.support.annotation.NonNull;
import android.util.Log;

import example.code.flickrgallery.network.ApiService;
import example.code.flickrgallery.network.model.JsonFlickrFeed;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GalleryPresenter implements GalleryContract.Presenter {

    private final GalleryContract.View galleryView;
    private final ApiService apiService;


    public GalleryPresenter(@NonNull GalleryContract.View galleryView, @NonNull ApiService apiService) {
        this.galleryView = galleryView;
        this.apiService = apiService;
        galleryView.setPresenter(this);
    }

    @Override
    public void result(@NonNull JsonFlickrFeed jsonFlickrFeed) {
        galleryView.showImages(jsonFlickrFeed.getItems());
    }

    @Override
    public void loadGallery(boolean forceUpdate) {

        galleryView.setLoadingIndicator(true);
        Call<JsonFlickrFeed> jsonFlickrFeedCall = apiService.getFeed();

        jsonFlickrFeedCall.clone().enqueue(new Callback<JsonFlickrFeed>() {
            @Override
            public void onResponse(@NonNull Call<JsonFlickrFeed> call, @NonNull Response<JsonFlickrFeed> response) {
                if (response.body() != null) result(response.body());
                galleryView.setLoadingIndicator(false);
            }

            @Override
            public void onFailure(@NonNull Call<JsonFlickrFeed> call, @NonNull Throwable t) {
                Log.e("Error", t.getMessage());
            }
        });
    }

    @Override
    public void start() {
        loadGallery(true);
    }
}
