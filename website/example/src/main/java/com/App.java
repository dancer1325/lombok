package com;

public class App
{
    public static void main( String[] args ) {
        // 1. @NoArgsConstructor
        System.out.println("--  1. @NoArgsConstructor -- \n");
        // 1.1
        System.out.println("-- 1.1 -- \n");
        NoArgsConstructorClass noArgsConstructorClass = new NoArgsConstructorClass();
        System.out.println(noArgsConstructorClass);
        // 1.2          + @Data
        System.out.println("-- 1.2 + @Data -- \n");
        NoArgsConstructorWithData simple = new NoArgsConstructorWithData();
        System.out.println(simple);
        // 1.4          + @Data      &       final field    & force=true
        System.out.println("-- 1.4          + @Data      &       final field    & force=true -- \n");
        NoArgsConstructorWithForce forced = new NoArgsConstructorWithForce();
        System.out.println(forced);
        // 1.5          + @Data      &   final field    &   force=true
        System.out.println("-- 1.5          + @Data      &   constraint (@NonNull) overcome | instance -- \n");
        NoArgsConstructorNonNull nonNull = new NoArgsConstructorNonNull();
        System.out.println(nonNull);        //  ⚠️requiredField=null          == constraint omitted | instance ⚠️
        // you should MANUALLY initialize the required field
        nonNull.setRequiredField("Setting required value");
        System.out.println(nonNull);
    }
}
