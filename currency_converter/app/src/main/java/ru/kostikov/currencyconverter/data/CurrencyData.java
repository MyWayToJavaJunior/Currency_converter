package ru.kostikov.currencyconverter.data;

/**
 * Class contain converter param for one currency
 */
public class CurrencyData{
    private final int mNumCode;
    private final int mNominal;
    private final float mValue;
    private final String mCharCode;
    private final String mName;

    public CurrencyData(int numCode, int nominal, float value, String charCode, String name) {
        this.mNumCode = numCode;
        this.mNominal = nominal;
        this.mValue = value;
        this.mCharCode = charCode;
        this.mName = name;
    }

    public int getNumCode() {
        return mNumCode;
    }

    public int getNominal() {
        return mNominal;
    }

    public float getValue() {
        return mValue;
    }

    public String getCharCode() {
        return mCharCode;
    }

    public String getName() {
        return mName;
    }
}
