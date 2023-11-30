package co.edu.ue.practica_login_api.model;

import java.util.ArrayList;

public class ResponseCredentials {
    private ArrayList<Credentials> credentials;
    private String mensaje;

    public ResponseCredentials(){}

    public ArrayList<Credentials> getCredentials() {
        return credentials;
    }

    public void setCredentials(ArrayList<Credentials> credentials) {
        this.credentials = credentials;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
