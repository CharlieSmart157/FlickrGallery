package example.code.flickrgallery.network;

import android.content.Context;

import example.code.flickrgallery.utils.Const;
import example.code.flickrgallery.utils.JsonConverter;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiClient {
    private static Retrofit retrofit = null;
    private static int REQUEST_TIMEOUT = 60;
    private static OkHttpClient okHttpClient = new OkHttpClient();

    public static Retrofit getClient(Context context) {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Const.BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(JsonConverter.create())
                    .build();
        }
        return retrofit;
    }
}

