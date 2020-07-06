package com.grupocumb.petroastur.model;

public enum FuelType {
    BIODIESEL("Biodiesel"),
    BIOETANOL("Bioetanol"),
    GAS_NATURAL_COMPRIMIDO("Gas natural comprimido"),
    GAS_NATURAL_LICUADO("Gas natural licuado"),
    GASES_LICUADOS_PETROLEO("Gases licuados petroleo"),
    GASOLEO_A("Gasoleo A"),
    GASOLEO_B("Gasoleo B"),
    GASOLINA_95("Gasolina 95 E5"),
    GASOLINA_98("Gasolina 98 E5"),
    NUEVO_GASOLEO_A("Nuevo gasoleo A");

    private final String formattedName;

    private FuelType(String formattedName) {
        this.formattedName = formattedName;
    }

    public String getFormattedName() {
        return this.formattedName;
    }


}
