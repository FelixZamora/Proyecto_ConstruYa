package co.edu.ue.practica_login_api.model;

public class Credentials {
    private String us_identifier;
    private String us_key;
    private String us_id;
    private String use_name;

    public String getUse_name() {
        return use_name;
    }

    public void setUse_name(String use_name) {
        this.use_name = use_name;
    }

    public String getUs_identifier() {
        return us_identifier;
    }

    public void setUs_identifier(String us_identifier) {
        this.us_identifier = us_identifier;
    }

    public String getUs_key() {
        return us_key;
    }

    public void setUs_key(String us_key) {
        this.us_key = us_key;
    }

    public String getUs_id() {
        return us_id;
    }

    public void setUs_id(String us_id) {
        this.us_id = us_id;
    }


}
