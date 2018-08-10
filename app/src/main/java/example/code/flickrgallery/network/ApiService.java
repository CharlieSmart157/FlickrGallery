package example.code.flickrgallery.network;

import example.code.flickrgallery.network.model.JsonFlickrFeed;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("photos_public.gne?format=json")
    Call<JsonFlickrFeed> getFeed();
}
