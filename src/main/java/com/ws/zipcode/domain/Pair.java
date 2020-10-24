package com.ws.zipcode.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class Pair {

    @NotNull(message = "{message.null}")
    @Pattern(regexp = "^[0-9]{5}", message = "regular expression must match")
    Integer minRange;

    @NotNull(message = "{message.null}")
    @Pattern(regexp = "^[0-9]{5}", message = "regular expression must match")
    Integer maxRange;
}
