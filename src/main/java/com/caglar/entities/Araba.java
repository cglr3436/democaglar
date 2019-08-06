package com.caglar.entities;

import javax.persistence.*;

@Entity
@Table(name="tbl_araba")
public class Araba {



    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private  Long id;

    @Column(name="marka_ad")
    private  String markaAd;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarkaAd() {
        return markaAd;
    }

    public void setMarkaAd(String markaAd) {
        this.markaAd = markaAd;
    }

    @Override
    public String toString() {
        return "Araba{" +
                "id=" + id +
                ", markaAd='" + markaAd + '\'' +
                '}';
    }

}
