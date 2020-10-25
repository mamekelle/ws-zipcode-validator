package com.ws.zipcode.domain;

import com.ws.zipcode.utilities.Utils;
import com.ws.zipcode.validation.AbstractBuilder;
import javax.validation.constraints.NotNull;

/**
 * Immutable class and expressive code with builder pattern
 */
public class ZipcodePair implements Comparable<ZipcodePair> {
    @NotNull(message = "Please insert value")
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
        return Utils.ZIPCODE_PAIR_COMPARATOR.compare(this,zipcodePair);
    }

    //Used nested class for Builder design pattern
    public static class Builder extends AbstractBuilder<ZipcodePair> {
        private Integer  minRange;
        private Integer maxRange;

        public Builder withMinRange(Integer minRange){
            this.minRange=minRange;
            return this;
        }
        public Builder withMaxRange(Integer maxRange){
            this.maxRange=maxRange;
            return this;
        }

        @Override
        protected ZipcodePair buildInternal() {
            return new ZipcodePair(this);
        }
    }

    private ZipcodePair(Builder builder){
        this.maxRange=builder.maxRange;
        this.minRange=builder.minRange;
    }

    @Override
    public String toString() {
        return "[" + minRange +","+ maxRange +"]";
    }
}
