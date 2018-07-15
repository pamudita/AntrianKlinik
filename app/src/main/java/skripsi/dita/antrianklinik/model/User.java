package skripsi.dita.antrianklinik.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Kristiawan on 21/04/18.
 * this class for example
 */
public class User {

    @SerializedName("norm")
    @Expose
    private String norm;

    @SerializedName("password")
    @Expose
    private String password;

    @SerializedName("tgl_lahir")
    @Expose
    private String tgl_lahir;

    @SerializedName("alert")
    @Expose
    private String alert;

    @SerializedName("status")
    @Expose
    private int status;


    public String getNorm() {
        return norm;
    }
    public void setNorm(String norm) {
        this.norm = norm;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getTgl_lahir() {
        return tgl_lahir;
    }
    public void setTgl_lahir(String tgl_lahir) {
        this.tgl_lahir = tgl_lahir;
    }

    public String getAlert() {
        return alert;
    }
    public void setAlert(String alert) {
        this.alert = alert;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
