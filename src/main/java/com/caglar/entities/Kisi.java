package com.caglar.entities;

import javax.persistence.*;

@Entity
@Table(name="tbl_kisi")
public class Kisi {



    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private  Long id;

    @Column(name="AD")
    private  String Adi;

    @Column(name="SOYADI")
    private  String SoyAdi;

    @Column(name="TEL")
    private  String Tel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAd() {
        return Adi;
    }

    public void setAd(String Ad) {
        this.Adi = Ad;
    }

    public String getSoyAdi() {
        return SoyAdi;
    }

    public void setSoyAdi(String SOYAd) {
        this.SoyAdi= SOYAd;
    }

    public String getTel() {
        return Tel;
    }

    public void setTel(String tel) {
        this.Tel= tel;
    }
    @Override
    public String toString() {
        return "Araba{" +
                "id=" + id +
                ", ADI='" + Adi + '\'' +
                ", SOYADI='" + SoyAdi + '\'' +
                ", TEL='" + Tel + '\'' +
                '}';
    }

    public Long findByAdi(String adi){
        if(Adi.equals(adi)) {
            return id;
        }
        return null;
    }


}
