package com.spaziocodice.labs.solr.qty;

/**
 * A simple value object encapsulating a quantity occurrence within a (query) string.
 *
 * @author agazzarini
 * @since 1.0
 * @see https://martinfowler.com/tags/analysis%20patterns.html
 * @see http://www.dsc.ufcg.edu.br/~jacques/cursos/map/recursos/fowler-ap/Analysis%20Pattern%20Quantity.htm
 */
public class QuantityOccurrence implements Comparable<QuantityOccurrence> {
    final Number amount;
    final String unit;
    final String fieldName;
    final int indexOfAmount;
    final int indexOfUnit;

    /**
     * Builds a new {@link QuantityOccurrence} with the given data.
     *
     * @param amount the amount.
     * @param unit the unit.
     * @param fieldName the field name in the Solr schema.
     * @param indexOfAmount the start index of the amount within the (query) string.
     * @param indexOfUnit the start index of the unit within the (query) string.
     */
    private QuantityOccurrence(final Number amount, final String unit, final String fieldName, final int indexOfAmount, final int indexOfUnit) {
        this.amount = amount;
        this.unit = unit;
        this.fieldName = fieldName;
        this.indexOfAmount = indexOfAmount;
        this.indexOfUnit = indexOfUnit;
    }

    static QuantityOccurrence createNew(final Number amount, final String unit, final String fieldName, final int indexOfUnit, final int indexOfAmount) {
        return new QuantityOccurrence(amount, unit, fieldName, indexOfAmount, indexOfUnit);
    }

    @Override
    public String toString() {
        return amount + " " + unit + "(" + fieldName + ")" + indexOfAmount + "," + indexOfUnit;
    }

    @Override
    public boolean equals(final Object obj) {
        return obj instanceof QuantityOccurrence
                && ((QuantityOccurrence)obj).amount.equals(amount)
                && ((QuantityOccurrence)obj).unit.equals(unit)
                && ((QuantityOccurrence)obj).indexOfAmount == indexOfAmount
                && ((QuantityOccurrence)obj).indexOfUnit == indexOfUnit;
    }

    @Override
    public int hashCode() {
        return amount.hashCode() +
                unit.hashCode() +
                Integer.valueOf(indexOfAmount).hashCode() +
                Integer.valueOf(indexOfUnit).hashCode();
    }

    @Override
    public int compareTo(final QuantityOccurrence occurrence) {
        return occurrence.indexOfAmount - indexOfAmount;
    }
}