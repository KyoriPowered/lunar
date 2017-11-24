/*
 * This file is part of lunar, licensed under the MIT License.
 *
 * Copyright (c) 2017 KyoriPowered
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

import net.kyori.blizzard.NonNull;

import java.util.Arrays;
import java.util.Optional;

/**
 * A collection of utilities for working with {@link Optional}.
 */
public final class Optionals {
  private Optionals() {
  }

  /**
   * Gets the first optional with a present value.
   *
   * @param optionals the optionals
   * @param <T> the type
   * @return an optional
   */
  @NonNull
  public static <T> Optional<T> first(final Optional<T>... optionals) {
    return Arrays.stream(optionals)
      .filter(Optional::isPresent)
      .findFirst()
      .orElse(Optional.empty());
  }
}
