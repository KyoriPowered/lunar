/*
 * This file is part of lunar, licensed under the MIT License.
 *
 * Copyright (c) 2017-2018 KyoriPowered
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package net.kyori.lunar;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.function.DoubleToIntFunction;
import java.util.function.DoubleUnaryOperator;

import static java.util.Objects.requireNonNull;

/**
 * A collection of utilities for working with primitive optionals.
 */
public final class PrimitiveOptionals {
  private PrimitiveOptionals() {
  }

  /**
   * Apply the provided mapping operator to the {@code optional} if a value is present,
   * and return an {@code OptionalDouble} describing the result.
   *
   * @param optional the optional
   * @param operator the operator
   * @return an optional
   */
  @NonNull
  public static OptionalDouble map(@NonNull final OptionalDouble optional, @NonNull final DoubleUnaryOperator operator) {
    requireNonNull(operator, "operator");
    return optional.isPresent() ? OptionalDouble.of(operator.applyAsDouble(optional.getAsDouble())) : OptionalDouble.empty();
  }

  /**
   * Apply the provided mapping function to the {@code optional} if a value is present,
   * and return an {@code OptionalInt} describing the result.
   *
   * @param optional the optional
   * @param function the function
   * @return an optional
   */
  @NonNull
  public static OptionalInt mapToInt(@NonNull final OptionalDouble optional, @NonNull final DoubleToIntFunction function) {
    requireNonNull(function, "function");
    return optional.isPresent() ? OptionalInt.of(function.applyAsInt(optional.getAsDouble())) : OptionalInt.empty();
  }
}
