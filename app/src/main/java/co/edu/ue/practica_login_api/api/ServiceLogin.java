package co.edu.ue.practica_login_api.api;

import java.util.List;

import co.edu.ue.practica_login_api.model.Loger;
import co.edu.ue.practica_login_api.model.Productos;
import co.edu.ue.practica_login_api.model.Register;
import co.edu.ue.practica_login_api.model.ResponseCredentials;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ServiceLogin {
    @POST("login")
    public Call<ResponseCredentials> accesslogin(@Body Loger  login);

    @POST("users")
    public Call<String> accessregister(@Body Register register);

    @GET("productos")
    public Call<List<Productos>> obtenerproductos();
}
