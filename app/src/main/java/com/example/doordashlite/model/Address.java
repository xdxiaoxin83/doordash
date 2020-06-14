package com.example.doordashlite.model;

import com.google.gson.annotations.SerializedName;

public class Address {

    @SerializedName("printable_address")
    private String printableAddress;

    public Address(String printableAddress) {
        this.printableAddress = printableAddress;
    }

    public String getPrintableAddress() {
        return printableAddress;
    }
}
