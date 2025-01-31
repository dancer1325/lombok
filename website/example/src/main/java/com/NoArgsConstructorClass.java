package com;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

// 1.1
@NoArgsConstructor
public class NoArgsConstructorClass {
    // WITHOUT @Data    -> IMPOSSIBLE to set it
    private String regularField;
}

// 1.2          + @Data
@Data
@NoArgsConstructor
class NoArgsConstructorWithData {
    private String regularField;
}

// 1.3          + @Data      &       final field
//  ⚠️IDE   highlights the error & compiler error  ⚠️       -- Uncomment to see --
/*@Data
@NoArgsConstructor
class NoArgsConstructorWithDataAndFinal {
    private final String regularField;
}*/

// 1.4          + @Data      &       final field    & force=true
// NO compiler error            -- Reason: finalField initialized to null
@Data
@NoArgsConstructor(force = true)
class NoArgsConstructorWithForce {
    private final String finalField;
}

// 1.5          + @Data      &   constraint (@NonNull) overcome | instance
@Data
@NoArgsConstructor
class NoArgsConstructorNonNull {
    @NonNull
    private String requiredField;
}

