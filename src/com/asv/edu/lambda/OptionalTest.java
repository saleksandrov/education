package com.asv.edu.lambda;

import java.util.Optional;

/**
 * @author alexandrov
 * @since 07.07.2016
 */
public class OptionalTest {

    public static void main(String[] args) {
        Optional<Integer> integerOptional = Optional.empty();
        Optional<Integer> integerOptionalNullable = Optional.ofNullable(null);

        System.out.println("integerOptional.isPresent() = " + integerOptional.isPresent());
        System.out.println("integerOptional.orElse(1) = " + integerOptional.orElse(1));

        System.out.println("integerOptionalNullable.isPresent() = " + integerOptionalNullable.isPresent());
        System.out.println("integerOptionalNullable.orElse(1) = " + integerOptionalNullable.orElse(1));

    }
}
