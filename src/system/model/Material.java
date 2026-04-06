package system.model;

import java.io.Serializable;

public class Material implements Serializable{
    private String material;
    private double factorDesgaste;
    private double factorConsumo;

    public Material(String material, double factorDesgaste, double factorConsumo) {
        this.material = material;
        this.factorDesgaste = factorDesgaste;
        this.factorConsumo = factorConsumo;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public double getFactorDesgaste() {
        return factorDesgaste;
    }

    public void setFactorDesgaste(float factorDesgaste) {
        this.factorDesgaste = factorDesgaste;
    }

    public double getFactorConsumo() {
        return factorConsumo;
    }

    public void setFactorConsumo(float factorConsumo) {
        this.factorConsumo = factorConsumo;
    }

    @Override
    public String toString() {
        return material;
    }
    
    
}
