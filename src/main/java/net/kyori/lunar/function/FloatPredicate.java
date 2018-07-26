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
package net.kyori.lunar.function;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.function.Predicate;

import static java.util.Objects.requireNonNull;

/**
 * A predicate of one {@code float}-valued argument.
 *
 * @see Predicate
 */
@FunctionalInterface
public interface FloatPredicate {
  /**
   * Evaluates this predicate on the given argument.
   *
   * @param input the input argument
   * @return {@code true} if the input argument matches the predicate, {@code false} otherwise
   */
  boolean test(final float input);

  /**
   * Returns a composed predicate that represents a short-circuiting logical
   * AND of this predicate and another.
   *
   * @param other a predicate that will be logically-ANDed with this predicate
   * @return a composed predicate that represents the short-circuiting logical
   *   AND of this predicate and the {@code other} predicate
   */
  default @NonNull FloatPredicate and(final @NonNull FloatPredicate other) {
    requireNonNull(other, "other");
    return (value) -> this.test(value) && other.test(value);
  }

  /**
   * Returns a predicate that represents the logical negation of this predicate.
   *
   * @return a predicate that represents the logical negation of this predicate
   */
  default @NonNull FloatPredicate negate() {
    return (input) -> !this.test(input);
  }

  /**
   * Returns a composed predicate that represents a short-circuiting logical
   * OR of this predicate and another.
   *
   * @param other a predicate that will be logically-ORed with this predicate
   * @return a composed predicate that represents the short-circuiting logical
   *   OR of this predicate and the {@code other} predicate
   */
  default @NonNull FloatPredicate or(final @NonNull FloatPredicate other) {
    requireNonNull(other, "other");
    return (value) -> this.test(value) || other.test(value);
  }
}
