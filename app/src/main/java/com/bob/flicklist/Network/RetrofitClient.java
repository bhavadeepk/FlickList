package com.bob.flicklist.Network;

import com.bob.flicklist.Model.FlicksResponse;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;

import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient{

    private static final String url= "https://api.flickr.com/";


    private static Retrofit getRetrofitInstance(){
        return new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static FlickrService getFlickrService() {
        return getRetrofitInstance().create(FlickrService.class);
    }

    public interface FlickrService {
        @GET("services/rest/?method=flickr.photos.getRecent&api_key=fee10de350d1f31d5fec0eaf330d2dba&format=json&nojsoncallback=true")
        Call<FlicksResponse> getRecents();
    }
}
