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

    @SerializedName("klinik")
    @Expose
    private String klinik;

    @SerializedName("dokter")
    @Expose
    private String dokter;

    @SerializedName("tgl_periksa")
    @Expose
    private String tgl_periksa;

    @SerializedName("antrian_saat_ini")
    @Expose
    private String antrian_saat_ini;

    @SerializedName("JamPeriksa")
    @Expose
    private String JamPeriksa;

    @SerializedName("estimasi")
    @Expose
    private String estimasi;

    @SerializedName("alert")
    @Expose
    private String alert;

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

    public String getKlinik() {
        return klinik;
    }
    public void setKlinik(String klinik) {
        this.klinik = klinik;
    }

    public String getDokter() {
        return dokter;
    }
    public void setDokter(String dokter) {
        this.dokter = dokter;
    }

    public String getTgl_periksa() {
        return tgl_periksa;
    }
    public void setTgl_periksa(String tgl_periksa) {
        this.tgl_periksa = tgl_periksa;
    }

    public String getJamPeriksa() {
        return JamPeriksa;
    }
    public void setJamPeriksa(String JamPeriksa) {
        this.JamPeriksa = JamPeriksa;
    }

    public String getAntrian_saat_ini() {
        return antrian_saat_ini;
    }
    public void setAntrian_saat_ini(String antrian_saat_ini) {
        this.antrian_saat_ini = antrian_saat_ini;
    }

    public String getEstimasi() {
        return estimasi;
    }
    public void setEstimasi(String estimasi) {
        this.estimasi = estimasi;
    }

    public String getAlert() {return alert;}
    public void setAlert(String alert) {this.alert = alert;}

}
