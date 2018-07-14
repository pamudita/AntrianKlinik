package skripsi.dita.antrianklinik.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Dita on 03/06/2018.
 */

public class Dokter {

    @SerializedName("KodeRuang")
    @Expose
    private String koderuang;

    @SerializedName("KodeDokter")
    @Expose
    private String kodedokter;

    @SerializedName("Nama")
    @Expose
    private String nama;

    public String getKoderuang() {
        return koderuang;
    }
    public void setKoderuang(String koderuang) {
        this.koderuang = koderuang;
    }

    public String getKodedokter() { return kodedokter; }
    public void setKodedokter(String kodedokter) { this.kodedokter = kodedokter; }

    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }

    public Dokter(){

    }
    public Dokter(String nama) {
        this.nama = nama;
    }
}
