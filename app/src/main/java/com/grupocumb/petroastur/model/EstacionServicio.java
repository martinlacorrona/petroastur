package com.grupocumb.petroastur.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class EstacionServicio {

    @SerializedName("C.P.")
    @Expose
    private String codigoPostal;
    @SerializedName("Direcci\u00f3n")
    @Expose
    private String direccion;
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
    private String precioBiodiesel;
    @SerializedName("Precio Bioetanol")
    @Expose
    private String precioBioetanol;
    @SerializedName("Precio Gas Natural Comprimido")
    @Expose
    private String precioGasNaturalComprimido;
    @SerializedName("Precio Gas Natural Licuado")
    @Expose
    private String precioGasNaturalLicuado;
    @SerializedName("Precio Gases licuados del petr\u00f3leo")
    @Expose
    private String precioGasesLicuadosDelPetroleo;
    @SerializedName("Precio Gasoleo A")
    @Expose
    private String precioGasoleoA;
    @SerializedName("Precio Gasoleo B")
    @Expose
    private String precioGasoleoB;
    @SerializedName("Precio Gasolina 95 Protecci\u00f3n")
    @Expose
    private String precioGasolina95Proteccion;
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
    private String remision;
    @SerializedName("R\u00f3tulo")
    @Expose
    private String empresa;
    @SerializedName("Tipo Venta")
    @Expose
    private String tipoVenta;
    @SerializedName("% BioEtanol")
    @Expose
    private String bioEtanol;
    @SerializedName("% \u00c9ster met\u00edlico")
    @Expose
    private String esterMetilico;
    @SerializedName("IDEESS")
    @Expose
    @PrimaryKey
    @NonNull
    private String id;
    @SerializedName("IDMunicipio")
    @Expose
    private String idMunicipio;
    @SerializedName("IDProvincia")
    @Expose
    private String idProvincia;
    @SerializedName("IDCCAA")
    @Expose
    private String idCCAA;

    public String toString() {
        return id;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public void setLongitudWGS84(String longitudWGS84) {
        this.longitudWGS84 = longitudWGS84;
    }

    public void setMargen(String margen) {
        this.margen = margen;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public void setPrecioBiodiesel(String precioBiodiesel) {
        this.precioBiodiesel = precioBiodiesel;
    }

    public void setPrecioBioetanol(String precioBioetanol) {
        this.precioBioetanol = precioBioetanol;
    }

    public void setPrecioGasNaturalComprimido(String precioGasNaturalComprimido) {
        this.precioGasNaturalComprimido = precioGasNaturalComprimido;
    }

    public void setPrecioGasNaturalLicuado(String precioGasNaturalLicuado) {
        this.precioGasNaturalLicuado = precioGasNaturalLicuado;
    }

    public void setPrecioGasesLicuadosDelPetroleo(String precioGasesLicuadosDelPetroleo) {
        this.precioGasesLicuadosDelPetroleo = precioGasesLicuadosDelPetroleo;
    }

    public void setPrecioGasoleoA(String precioGasoleoA) {
        this.precioGasoleoA = precioGasoleoA;
    }

    public void setPrecioGasoleoB(String precioGasoleoB) {
        this.precioGasoleoB = precioGasoleoB;
    }

    public void setPrecioGasolina95Proteccion(String precioGasolina95Proteccion) {
        this.precioGasolina95Proteccion = precioGasolina95Proteccion;
    }

    public void setPrecioGasolina98(String precioGasolina98) {
        this.precioGasolina98 = precioGasolina98;
    }

    public void setPrecioNuevoGasoleoA(String precioNuevoGasoleoA) {
        this.precioNuevoGasoleoA = precioNuevoGasoleoA;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public void setRemision(String remision) {
        this.remision = remision;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public void setTipoVenta(String tipoVenta) {
        this.tipoVenta = tipoVenta;
    }

    public void setBioEtanol(String bioEtanol) {
        this.bioEtanol = bioEtanol;
    }

    public void setEsterMetilico(String esterMetilico) {
        this.esterMetilico = esterMetilico;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public void setIdMunicipio(String idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public void setIdProvincia(String idProvincia) {
        this.idProvincia = idProvincia;
    }

    public void setIdCCAA(String idCCAA) {
        this.idCCAA = idCCAA;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getHorario() {
        return horario;
    }

    public String getLatitud() {
        return latitud;
    }

    public String getLocalidad() {
        return localidad;
    }

    public String getLongitudWGS84() {
        return longitudWGS84;
    }

    public String getMargen() {
        return margen;
    }

    public String getMunicipio() {
        return municipio;
    }

    public String getPrecioBiodiesel() {
        return precioBiodiesel;
    }

    public String getPrecioBioetanol() {
        return precioBioetanol;
    }

    public String getPrecioGasNaturalComprimido() {
        return precioGasNaturalComprimido;
    }

    public String getPrecioGasNaturalLicuado() {
        return precioGasNaturalLicuado;
    }

    public String getPrecioGasesLicuadosDelPetroleo() {
        return precioGasesLicuadosDelPetroleo;
    }

    public String getPrecioGasoleoA() {
        return precioGasoleoA;
    }

    public String getPrecioGasoleoB() {
        return precioGasoleoB;
    }

    public String getPrecioGasolina95Proteccion() {
        return precioGasolina95Proteccion;
    }

    public String getPrecioGasolina98() {
        return precioGasolina98;
    }

    public String getPrecioNuevoGasoleoA() {
        return precioNuevoGasoleoA;
    }

    public String getProvincia() {
        return provincia;
    }

    public String getRemision() {
        return remision;
    }

    public String getEmpresa() {
        return empresa;
    }

    public String getTipoVenta() {
        return tipoVenta;
    }

    public String getBioEtanol() {
        return bioEtanol;
    }

    public String getEsterMetilico() {
        return esterMetilico;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public String getIdMunicipio() {
        return idMunicipio;
    }

    public String getIdProvincia() {
        return idProvincia;
    }

    public String getIdCCAA() {
        return idCCAA;
    }

    //DOUBLE RETURNS
    public Double getPrecioCombustible(FuelType type) {
        if (getPrecioCombustibleConcreto(type) != null)
            return Double.parseDouble(getPrecioCombustibleConcreto(type).replace(",", "."));
        return 0.0;
    }

    private String getPrecioCombustibleConcreto(FuelType type) {
        String typeFormatted = null;
        switch (type) {
            case BIODIESEL:
                typeFormatted = getPrecioBiodiesel();
                break;
            case BIOETANOL:
                typeFormatted =  getPrecioBioetanol();
                break;
            case GASOLEO_A:
                typeFormatted =  getPrecioGasoleoA();
                break;
            case GASOLEO_B:
                typeFormatted =  getPrecioGasoleoB();
                break;
            case GASOLINA_95:
                typeFormatted =  getPrecioGasolina95Proteccion();
                break;
            case GASOLINA_98:
                typeFormatted =  getPrecioGasolina98();
                break;
            case NUEVO_GASOLEO_A:
                typeFormatted =  getPrecioNuevoGasoleoA();
                break;
            case GAS_NATURAL_LICUADO:
                typeFormatted =  getPrecioGasNaturalLicuado();
                break;
            case GAS_NATURAL_COMPRIMIDO:
                typeFormatted =  getPrecioGasNaturalComprimido();
                break;
            case GASES_LICUADOS_PETROLEO:
                typeFormatted =  getPrecioGasesLicuadosDelPetroleo();
                break;
        }

        return typeFormatted.contains(",") ? typeFormatted : null;
    }
}
