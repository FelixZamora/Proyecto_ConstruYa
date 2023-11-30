package co.edu.ue.practica_login_api.api;

import co.edu.ue.practica_login_api.model.Loger;
import co.edu.ue.practica_login_api.model.ResponseCredentials;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ServiceLogin {
    @POST("login")
    public Call<ResponseCredentials> accesslogin(@Body Loger login);
}
