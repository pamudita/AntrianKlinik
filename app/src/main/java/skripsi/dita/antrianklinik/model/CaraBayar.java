package skripsi.dita.antrianklinik.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Dita on 24/06/2018.
 */

public class CaraBayar {

    @SerializedName("Kode")
    @Expose
    private String kode;

    @SerializedName("Keterangan")
    @Expose
    private String keterangan;

    public String getKode() {
        return kode;
    }
    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getKeterangan() {
        return keterangan;
    }
    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public CaraBayar() {
    }

    public CaraBayar(String keterangan) {
        this.keterangan = keterangan;
    }
}
