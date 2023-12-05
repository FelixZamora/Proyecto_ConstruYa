package co.edu.ue.practica_login_api.model;

import java.util.Date;
import java.text.SimpleDateFormat;

public class Register {
    private String use_pss;
    private String use_mail;
    private String use_name;

    public String getUse_dateCreate() {
        return use_dateCreate;
    }

    public void setUse_dateCreate(String use_dateCreate) {
        this.use_dateCreate = use_dateCreate;
    }

    private String use_dateCreate;


    public String getUse_pss() {
        return use_pss;
    }

    public void setUse_pss(String use_pss) {
        this.use_pss = use_pss;
    }

    public String getUse_mail() {
        return use_mail;
    }

    public void setUse_mail(String use_mail) {
        this.use_mail = use_mail;
    }

    public String getUse_name() {
        return use_name;
    }

    public void setUse_name(String use_name) {
        this.use_name = use_name;
    }
}
