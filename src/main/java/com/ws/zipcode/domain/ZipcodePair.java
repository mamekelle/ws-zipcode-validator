package com.ws.zipcode.domain;

import com.ws.zipcode.utilities.Utils;
import com.ws.zipcode.validation.AbstractBuilder;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Immutable class and expressive code with builder pattern
 */
public class ZipcodePair implements Comparable<ZipcodePair> {
    @NotNull()
    @Min(1)
    @Max(99999)
    private final Integer minRange;
    @NotNull(message = "Null value not allowed")
    @Min(1)
    @Max(99999)
    private final Integer maxRange;

    public Integer getMinRange() {
        return minRange;
    }
    public Integer getMaxRange() {
        return maxRange;
    }

    /**
     * implementing/overriding the compareTo methods of  Comparable inorder to customize the sorting of the ZipcodePair
     * custom class. Also need to make sure that in the utils class both pairs required to be compared for object consistency
     * Thanks to the functional interface and method reference features of Java 8 we can solve this in couple of lines
     * @param zipcodePair zipcode input to compare for sorting
     * @return return -1 if current instance less than, 0 if equal, 1 if greater than
     */
    @Override
    public int compareTo(ZipcodePair zipcodePair) {
        // I may need to override the hashCode and equals methods of the object class
        return Utils.ZIPCODE_PAIR_COMPARATOR.compare(this, zipcodePair);
    }

    /**
     * Used nested class for Builder design pattern
     */
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

        /**
         * internal builder pattern build overrides the AbstractBuilder method and creates the object ZipcodePair
         * @return return factory method or object instance of ZipCodePair
         */
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

    /**
     * Constructor with the Builder class
     * @param builder compiled object with min and max range
     */
    private ZipcodePair(Builder builder) {
        this.maxRange = builder.maxRange;
        this.minRange = builder.minRange;
    }

    /**
     * overrides the object classes string method for well formatted and human readable object representation
     * can be used for log processes
     * @return overrides the object classes string method to match with the expected output
     */
    @Override
    public String toString() {
        return "[" + minRange + "," + maxRange + "]";
    }
}
