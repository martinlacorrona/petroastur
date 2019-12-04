
package com.grupocumb.petroastur.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EstacionServicio {

    @SerializedName("C.P.")
    @Expose
    private String cP;
    @SerializedName("Direcci\u00f3n")
    @Expose
    private String direcciN;
    @SerializedName("Horario")
    @Expose
    private String horario;
    @SerializedName("Latitud")
    @Expose
    private String latitud;
    @SerializedName("Localidad")
    @Expose
    private String localidad;
    @SerializedName("Longitud (WGS84)")
    @Expose
    private String longitudWGS84;
    @SerializedName("Margen")
    @Expose
    private String margen;
    @SerializedName("Municipio")
    @Expose
    private String municipio;
    @SerializedName("Precio Biodiesel")
    @Expose
    private Object precioBiodiesel;
    @SerializedName("Precio Bioetanol")
    @Expose
    private Object precioBioetanol;
    @SerializedName("Precio Gas Natural Comprimido")
    @Expose
    private Object precioGasNaturalComprimido;
    @SerializedName("Precio Gas Natural Licuado")
    @Expose
    private Object precioGasNaturalLicuado;
    @SerializedName("Precio Gases licuados del petr\u00f3leo")
    @Expose
    private Object precioGasesLicuadosDelPetrLeo;
    @SerializedName("Precio Gasoleo A")
    @Expose
    private String precioGasoleoA;
    @SerializedName("Precio Gasoleo B")
    @Expose
    private Object precioGasoleoB;
    @SerializedName("Precio Gasolina 95 Protecci\u00f3n")
    @Expose
    private String precioGasolina95ProtecciN;
    @SerializedName("Precio Gasolina  98")
    @Expose
    private String precioGasolina98;
    @SerializedName("Precio Nuevo Gasoleo A")
    @Expose
    private String precioNuevoGasoleoA;
    @SerializedName("Provincia")
    @Expose
    private String provincia;
    @SerializedName("Remisi\u00f3n")
    @Expose
    private String remisiN;
    @SerializedName("R\u00f3tulo")
    @Expose
    private String rTulo;
    @SerializedName("Tipo Venta")
    @Expose
    private String tipoVenta;
    @SerializedName("% BioEtanol")
    @Expose
    private String bioEtanol;
    @SerializedName("% \u00c9ster met\u00edlico")
    @Expose
    private String sterMetLico;
    @SerializedName("IDEESS")
    @Expose
    private String iDEESS;
    @SerializedName("IDMunicipio")
    @Expose
    private String iDMunicipio;
    @SerializedName("IDProvincia")
    @Expose
    private String iDProvincia;
    @SerializedName("IDCCAA")
    @Expose
    private String iDCCAA;

    public String getCP() {
        return cP;
    }

    public void setCP(String cP) {
        this.cP = cP;
    }

    public String getDirecciN() {
        return direcciN;
    }

    public void setDirecciN(String direcciN) {
        this.direcciN = direcciN;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getLongitudWGS84() {
        return longitudWGS84;
    }

    public void setLongitudWGS84(String longitudWGS84) {
        this.longitudWGS84 = longitudWGS84;
    }

    public String getMargen() {
        return margen;
    }

    public void setMargen(String margen) {
        this.margen = margen;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public Object getPrecioBiodiesel() {
        return precioBiodiesel;
    }

    public void setPrecioBiodiesel(Object precioBiodiesel) {
        this.precioBiodiesel = precioBiodiesel;
    }

    public Object getPrecioBioetanol() {
        return precioBioetanol;
    }

    public void setPrecioBioetanol(Object precioBioetanol) {
        this.precioBioetanol = precioBioetanol;
    }

    public Object getPrecioGasNaturalComprimido() {
        return precioGasNaturalComprimido;
    }

    public void setPrecioGasNaturalComprimido(Object precioGasNaturalComprimido) {
        this.precioGasNaturalComprimido = precioGasNaturalComprimido;
    }

    public Object getPrecioGasNaturalLicuado() {
        return precioGasNaturalLicuado;
    }

    public void setPrecioGasNaturalLicuado(Object precioGasNaturalLicuado) {
        this.precioGasNaturalLicuado = precioGasNaturalLicuado;
    }

    public Object getPrecioGasesLicuadosDelPetrLeo() {
        return precioGasesLicuadosDelPetrLeo;
    }

    public void setPrecioGasesLicuadosDelPetrLeo(Object precioGasesLicuadosDelPetrLeo) {
        this.precioGasesLicuadosDelPetrLeo = precioGasesLicuadosDelPetrLeo;
    }

    public String getPrecioGasoleoA() {
        return precioGasoleoA;
    }

    public void setPrecioGasoleoA(String precioGasoleoA) {
        this.precioGasoleoA = precioGasoleoA;
    }

    public Object getPrecioGasoleoB() {
        return precioGasoleoB;
    }

    public void setPrecioGasoleoB(Object precioGasoleoB) {
        this.precioGasoleoB = precioGasoleoB;
    }

    public String getPrecioGasolina95ProtecciN() {
        return precioGasolina95ProtecciN;
    }

    public void setPrecioGasolina95ProtecciN(String precioGasolina95ProtecciN) {
        this.precioGasolina95ProtecciN = precioGasolina95ProtecciN;
    }

    public String getPrecioGasolina98() {
        return precioGasolina98;
    }

    public void setPrecioGasolina98(String precioGasolina98) {
        this.precioGasolina98 = precioGasolina98;
    }

    public String getPrecioNuevoGasoleoA() {
        return precioNuevoGasoleoA;
    }

    public void setPrecioNuevoGasoleoA(String precioNuevoGasoleoA) {
        this.precioNuevoGasoleoA = precioNuevoGasoleoA;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getRemisiN() {
        return remisiN;
    }

    public void setRemisiN(String remisiN) {
        this.remisiN = remisiN;
    }

    public String getRTulo() {
        return rTulo;
    }

    public void setRTulo(String rTulo) {
        this.rTulo = rTulo;
    }

    public String getTipoVenta() {
        return tipoVenta;
    }

    public void setTipoVenta(String tipoVenta) {
        this.tipoVenta = tipoVenta;
    }

    public String getBioEtanol() {
        return bioEtanol;
    }

    public void setBioEtanol(String bioEtanol) {
        this.bioEtanol = bioEtanol;
    }

    public String getSterMetLico() {
        return sterMetLico;
    }

    public void setSterMetLico(String sterMetLico) {
        this.sterMetLico = sterMetLico;
    }

    public String getIDEESS() {
        return iDEESS;
    }

    public void setIDEESS(String iDEESS) {
        this.iDEESS = iDEESS;
    }

    public String getIDMunicipio() {
        return iDMunicipio;
    }

    public void setIDMunicipio(String iDMunicipio) {
        this.iDMunicipio = iDMunicipio;
    }

    public String getIDProvincia() {
        return iDProvincia;
    }

    public void setIDProvincia(String iDProvincia) {
        this.iDProvincia = iDProvincia;
    }

    public String getIDCCAA() {
        return iDCCAA;
    }

    public void setIDCCAA(String iDCCAA) {
        this.iDCCAA = iDCCAA;
    }

}
