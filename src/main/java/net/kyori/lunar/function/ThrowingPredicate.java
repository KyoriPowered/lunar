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

import net.kyori.lunar.exception.Exceptions;

import java.util.function.Predicate;

/**
 * A {@link Predicate} that allows for throwing checked exceptions.
 *
 * @param <T> the input type
 * @param <E> the exception type
 */
@FunctionalInterface
public interface ThrowingPredicate<T, E extends Throwable> extends Predicate<T> {
  /**
   * Evaluates this predicate on the given argument.
   *
   * @param input the input
   * @return {@code true} if the input argument matches the predicate, {@code false} otherwise
   * @throws E potential exception
   */
  boolean throwingTest(final T input) throws E;

  @Override
  default boolean test(final T input) {
    try {
      return this.throwingTest(input);
    } catch(final Throwable t) {
      throw Exceptions.rethrow(t);
    }
  }
}
