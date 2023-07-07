package umn.ac.id.windytreeproduction.model;

import android.widget.EditText;

public class DataPemesan {
    String lokasi, waktu, fullName, number, event, selectpv, reqselecttemp;

    public DataPemesan(String lokasi, String waktu, String fullName, String number, String event, String selectpv, String reqselecttemp) {
        this.lokasi = lokasi;
        this.waktu = waktu;
        this.fullName = fullName;
        this.number = number;
        this.event = event;
        this.selectpv = selectpv;
        this.reqselecttemp = reqselecttemp;
    }

    public String getLokasi() {
        return lokasi;
    }

    public String getWaktu() {
        return waktu;
    }

    public String getFullName() {
        return fullName;
    }

    public String getNumber() {
        return number;
    }

    public String getEvent() {
        return event;
    }

    public String getSelectpv() {
        return selectpv;
    }

    public String getReqselecttemp() {
        return reqselecttemp;
    }
}
