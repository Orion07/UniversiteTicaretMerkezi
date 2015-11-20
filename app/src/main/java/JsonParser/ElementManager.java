package JsonParser;

import android.os.Parcel;

/**
 * Created by Hakan on 15.11.2015.
 */
public class ElementManager {
    private String resim,baslik,konum,fiyat;

    public ElementManager(String resim, String baslik, String konum, String fiyat) {
        this.resim = resim;
        this.baslik = baslik;
        this.konum = konum;
        this.fiyat = fiyat;
    }

    public String getBaslik() {
        return baslik;
    }

    public void setBaslik(String baslik) {
        this.baslik = baslik;
    }

    public String getResim() {
        return resim;
    }

    public void setResim(String resim) {
        this.resim = resim;
    }

    public String getFiyat() {
        return fiyat;
    }

    public void setFiyat(String fiyat) {
        this.fiyat = fiyat;
    }

    public String getKonum() {
        return konum;
    }

    public void setKonum(String konum) {
        this.konum = konum;
    }

}
