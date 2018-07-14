package skripsi.dita.antrianklinik.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Dita on 21/06/2018.
 */

public class Pendaftaran {

    @SerializedName("id_pendaftaran")
    @Expose
    private String id_pendaftaran;

    @SerializedName("norm")
    @Expose
    private String norm;

    @SerializedName("nama")
    @Expose
    private String nama;

    @SerializedName("NomorRM")
    @Expose
    private String NomorRM;

    @SerializedName("NamaPasien")
    @Expose
    private String NamaPasien;

    @SerializedName("tgl_lahir")
    @Expose
    private String tgl_lahir;

    @SerializedName("KodeCaraPembayaran")
    @Expose
    private String KodeCaraPembayaran;

    @SerializedName("NoKartu")
    @Expose
    private String NoKartu;

    @SerializedName("TglPeriksa")
    @Expose
    private String TglPeriksa;

    @SerializedName("Ruang")
    @Expose
    private String Ruang;

    @SerializedName("Kunjungan")
    @Expose
    private String Kunjungan;

    @SerializedName("KodeDokter")
    @Expose
    private String KodeDokter;

    @SerializedName("NamaDokter")
    @Expose
    private String NamaDokter;

    @SerializedName("keluhan")
    @Expose
    private String keluhan;

    @SerializedName("jk")
    @Expose
    private String jk;

    @SerializedName("no_antrian")
    @Expose
    private String no_antrian;

    @SerializedName("alert")
    @Expose
    private String alert;

    public String getId_pendaftaran() {
        return id_pendaftaran;
    }
    public void setId_pendaftaran(String id_pendaftaran) { this.id_pendaftaran = id_pendaftaran;}

    public String getNomorRM() {
        return NomorRM;
    }
    public void setNomorRM(String NomorRM) { this.NomorRM = NomorRM;}

    public String getNorm() {
        return norm;
    }
    public void setNorm(String norm) { this.norm = norm;}

    public String getNamaPasien() {return NamaPasien;}
    public void setNamaPasien(String NamaPasien) {this.NamaPasien = NamaPasien;}

    public String getNama() {return nama;}
    public void setNama(String nama) {this.nama = nama;}

    public String getTgl_lahir() { return tgl_lahir; }
    public void setTgl_lahir(String tgl_lahir) {
        this.tgl_lahir = tgl_lahir;
    }

    public String getKodeCaraPembayaran() {
        return KodeCaraPembayaran;
    }
    public void setKodeCaraPembayaran(String kodeCaraPembayaran) {
        this.KodeCaraPembayaran = KodeCaraPembayaran;
    }

    public String getNoKartu() {
        return NoKartu;
    }
    public void setNoKartu(String NoKartu) {
        this.NoKartu = NoKartu;
    }

    public String getTglPeriksa() {
        return TglPeriksa;
    }
    public void setTglPeriksa(String TglPeriksa) {
        this.TglPeriksa = TglPeriksa;
    }

    public String getRuang() {return Ruang;}
    public void setRuang(String Ruang) {this.Ruang = Ruang;}

    public String getKodeDokter() {return KodeDokter;}
    public void setKodeDokter(String KodeDokter) {this.KodeDokter = KodeDokter;}

    public String getNamaDokter() {return NamaDokter;}
    public void setNamaDokter(String NamaDokter) {this.NamaDokter = NamaDokter;}

    public String getKeluhan() {return keluhan;}
    public void setKeluhan(String keluhan) {this.keluhan = keluhan;}

    public String getKunjungan() {return Kunjungan;}
    public void setKunjungan(String Kunjungan) {this.Kunjungan = Kunjungan;}

    public String getJk() {return jk;}
    public void setJk (String jk) {this.jk = jk;}

    public String getNo_antrian() {return no_antrian;}
    public void setNo_antrian(String no_antrian) {this.no_antrian = no_antrian;}

    public String getAlert() {
        return alert;
    }
    public void setAlert(String alert) {
        this.alert = alert;
    }
}
