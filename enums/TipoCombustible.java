package enums;

public enum TipoCombustible {
    NAFTA("NAFTA"),
    GASOIL("GASOIL"),
    GAS("GAS");

    private String tipoCombustible;

    TipoCombustible(String tipoCombustible) {
        this.tipoCombustible = tipoCombustible;
    }
}
