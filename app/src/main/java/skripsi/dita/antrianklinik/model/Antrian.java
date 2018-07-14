package skripsi.dita.antrianklinik.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Dita on 20/05/2018.
 */

public class Antrian {
    @SerializedName("no_antrian")
    @Expose
    private String no_antrian;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("Ruang")
    @Expose
    private String Ruang;

    @SerializedName("Nama")
    @Expose
    private String Nama;

    @SerializedName("TglPeriksa")
    @Expose
    private String TglPeriksa;

    @SerializedName("JamPeriksa")
    @Expose
    private String JamPeriksa;


    public String getNo_antrian() {
        return no_antrian;
    }
    public void setNo_antrian(String no_antrian) {
        this.no_antrian = no_antrian;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getRuang() {
        return Ruang;
    }
    public void setRuang(String Ruang) {
        this.Ruang = Ruang;
    }

    public String getNama() {
        return Nama;
    }
    public void setNama(String Nama) {
        this.Nama = Nama;
    }

    public String getTglPeriksa() {
        return TglPeriksa;
    }
    public void setTglPeriksa(String TglPeriksa) {
        this.TglPeriksa = TglPeriksa;
    }

    public String getJamPeriksa() {
        return JamPeriksa;
    }
    public void setJamPeriksa(String JamPeriksa) {
        this.JamPeriksa = JamPeriksa;
    }

}
