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

import com.google.common.base.Predicates;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.Objects;
import java.util.function.Predicate;

/**
 * A collection of utilities for working with {@link Predicate}.
 *
 * @see Predicates
 */
public interface MorePredicates {
  /**
   * Returns a predicate that always returns {@code false}.
   *
   * @param <T> the type
   * @return a predicate
   */
  @SuppressWarnings("unchecked")
  static <T> @NonNull Predicate<T> alwaysFalse() {
    return (Predicate<T>) MorePredicates0.ALWAYS_FALSE;
  }

  /**
   * Returns a predicate that always returns {@code true}.
   *
   * @param <T> the type
   * @return a predicate
   */
  @SuppressWarnings("unchecked")
  static <T> @NonNull Predicate<T> alwaysTrue() {
    return (Predicate<T>) MorePredicates0.ALWAYS_TRUE;
  }

  /**
   * Returns a predicate that always returns {@code true} for {@code null} inputs.
   *
   * @param <T> the type
   * @return a predicate
   */
  @SuppressWarnings("unchecked")
  static <T> @NonNull Predicate<T> isNull() {
    return (Predicate<T>) MorePredicates0.IS_NULL;
  }

  /**
   * Returns a predicate that always returns {@code true} for non-{@code null} inputs.
   *
   * @param <T> the type
   * @return a predicate
   */
  @SuppressWarnings("unchecked")
  static <T> @NonNull Predicate<T> nonNull() {
    return (Predicate<T>) MorePredicates0.NON_NULL;
  }
}

final class MorePredicates0 {
  static final Predicate<?> ALWAYS_FALSE = input -> false;
  static final Predicate<?> ALWAYS_TRUE = input -> true;
  static final Predicate<?> IS_NULL = Objects::isNull;
  static final Predicate<?> NON_NULL = Objects::nonNull;
}
