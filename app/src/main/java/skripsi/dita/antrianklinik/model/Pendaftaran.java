package skripsi.dita.antrianklinik.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Dita on 21/06/2018.
 */

public class Pendaftaran {

    @SerializedName("norm")
    @Expose
    private String norm;

    @SerializedName("nama")
    @Expose
    private String nama;

    @SerializedName("tgl_lahir")
    @Expose
    private String tgl_lahir;

    public String getNorm() {
        return norm;
    }
    public void setNorm(String norm) {
        this.norm = norm;
    }

    public String getNama() {
        return nama;
    }
    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTgl_lahir() { return tgl_lahir; }
    public void setTgl_lahir(String tgl_lahir) {
        this.tgl_lahir = tgl_lahir;
    }
}
