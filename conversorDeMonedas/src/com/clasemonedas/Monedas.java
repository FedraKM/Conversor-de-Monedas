package com.clasemonedas;

import com.google.gson.annotations.SerializedName;

public class Monedas {
    @SerializedName("base_code")
    public String codigoBase;

    @SerializedName("target_code")
    public String codigoFinal;

    @SerializedName("conversion_rate")
    public double tasaDeConversion;

    @SerializedName("conversion_result")
    public double resultadoConversion;

}
