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
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.Arrays;
import java.util.Optional;

/**
 * A collection of utilities for working with {@link Optional}.
 */
public final class Optionals {
  private Optionals() {
  }

  /**
   * Tests if the value held by {@code optional} is an instance of {@code type}.
   *
   * @param optional the optional
   * @param type the type
   * @return {@code true} if the value held by {@code optional} is an instance of {@code type}, {@code false} otherwise
   */
  public static boolean isInstance(final @NonNull Optional<?> optional, final @NonNull Class<?> type) {
    return optional.isPresent() && type.isInstance(optional.get());
  }

  /**
   * Casts {@code optional} to an optional of type {@code type} if the value held by {@code optional} is an instance of {@code type}
   *
   * @param optional the optional
   * @param type the type
   * @param <T> the type
   * @return the optional
   */
  public static <T> @NonNull Optional<T> cast(final @NonNull Optional<?> optional, final @NonNull Class<T> type) {
    // not necessary to re-wrap, we can just cast
    return isInstance(optional, type) ? (Optional<T>) optional : Optional.empty();
  }

  /**
   * Gets an optional of {@code object} if {@code object} is an instance of {@code type}, or an empty optional.
   *
   * @param object the object
   * @param type the type
   * @param <T> the type
   * @return the optional
   */
  public static <T> @NonNull Optional<T> cast(final @Nullable Object object, final @NonNull Class<T> type) {
    return type.isInstance(object) ? Optional.ofNullable((T) object) : Optional.empty();
  }

  /**
   * Gets the first optional with a present value.
   *
   * @param optionals the optionals
   * @param <T> the type
   * @return an optional
   */
  @SafeVarargs
  public static <T> @NonNull Optional<T> first(final @NonNull Optional<T>... optionals) {
    return Arrays.stream(optionals)
      .filter(Optional::isPresent)
      .findFirst()
      .orElse(Optional.empty());
  }
}
