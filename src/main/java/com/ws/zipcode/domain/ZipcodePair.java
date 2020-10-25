package com.ws.zipcode.domain;

import com.ws.zipcode.utilities.Utils;
import com.ws.zipcode.validation.AbstractBuilder;

import javax.validation.constraints.NotNull;

/**
 * Immutable class and expressive code with builder pattern
 */
public class ZipcodePair implements Comparable<ZipcodePair> {
    @NotNull(message = "{NotEmpty}")
    private Integer minRange;
    @NotNull(message = "Please insert value")
    private Integer maxRange;

    public Integer getMinRange() {
        return minRange;
    }
    public Integer getMaxRange() {
        return maxRange;
    }

    @Override
    public int compareTo(ZipcodePair zipcodePair) {
        // I may need to override the hashCode and equals methods of the object calss
        return Utils.ZIPCODE_PAIR_COMPARATOR.compare(this, zipcodePair);
    }

    //Used nested class for Builder design pattern
    public static class Builder extends AbstractBuilder<ZipcodePair> {
        private Integer minRange;
        private Integer maxRange;

        public Builder withMinRange(Integer minRange) {
            this.minRange = minRange;
            return this;
        }

        public Builder withMaxRange(Integer maxRange) {
            this.maxRange = maxRange;
            return this;
        }

        @Override
        protected ZipcodePair buildInternal() {
            //This part starts early so @Annotation error messages may not get displayed
            if (minRange != null && maxRange != null && minRange > maxRange) {
                throw new IllegalArgumentException("Invalid input minRange must not be greater than maxRange - " + '[' +
                        minRange + "," + maxRange + ']');
            }
            return new ZipcodePair(this);
        }
    }

    private ZipcodePair(Builder builder) {
        this.maxRange = builder.maxRange;
        this.minRange = builder.minRange;
    }

    @Override
    public String toString() {
        return "[" + minRange + "," + maxRange + "]";
    }
}
