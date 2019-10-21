package com.busbadajoz.Network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WebRequestInterface {

    @GET("tiempoparada.php")
    Call<Stop> getStop(@Query("id1959") String stopID);
}
