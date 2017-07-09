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
package net.kyori.lunar.exception;

import com.google.common.base.Throwables;
import net.kyori.lunar.function.ThrowingBiConsumer;
import net.kyori.lunar.function.ThrowingBiFunction;
import net.kyori.lunar.function.ThrowingConsumer;
import net.kyori.lunar.function.ThrowingFunction;
import net.kyori.lunar.function.ThrowingSupplier;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import javax.annotation.Nonnull;

/**
 * A collection of methods for working with exceptions.
 */
// TODO: tests
public final class Exceptions {

  private Exceptions() {
  }

  /**
   * Returns the same throwing consumer.
   *
   * @param consumer the consumer
   * @param <T> the input type
   * @param <E> the exception type
   * @return the consumer
   */
  @Nonnull
  public static <T, E extends Exception> Consumer<T> rethrowConsumer(@Nonnull final ThrowingConsumer<T, E> consumer) {
    return consumer;
  }

  /**
   * Returns the same throwing bi-consumer.
   *
   * @param consumer the bi-consumer
   * @param <T> the first input type
   * @param <U> the second input type
   * @param <E> the exception type
   * @return the bi-consumer
   */
  @Nonnull
  public static <T, U, E extends Exception> BiConsumer<T, U> rethrowConsumer(@Nonnull final ThrowingBiConsumer<T, U, E> consumer) {
    return consumer;
  }

  /**
   * Returns the same throwing function.
   *
   * @param function the function
   * @param <T> the input type
   * @param <R> the output type
   * @param <E> the exception type
   * @return the function
   */
  @Nonnull
  public static <T, R, E extends Exception> Function<T, R> rethrowFunction(@Nonnull final ThrowingFunction<T, R, E> function) {
    return function;
  }

  /**
   * Returns the same throwing bi-function.
   *
   * @param function the bi-function
   * @param <T> the first input type
   * @param <U> the second input type
   * @param <R> the output type
   * @param <E> the exception type
   * @return the bi-function
   */
  @Nonnull
  public static <T, U, R, E extends Exception> BiFunction<T, U, R> rethrowFunction(@Nonnull final ThrowingBiFunction<T, U, R, E> function) {
    return function;
  }

  /**
   * Returns the same throwing supplier.
   *
   * @param supplier the supplier
   * @param <T> the output type
   * @param <E> the exception type
   * @return the supplier
   */
  @Nonnull
  public static <T, E extends Exception> Supplier<T> rethrowSupplier(@Nonnull final ThrowingSupplier<T, E> supplier) {
    return supplier;
  }

  /**
   * Re-throws an exception, sneakily.
   *
   * @param exception the exception
   * @param <E> the exception type
   * @return nothing
   * @throws E the exception
   */
  public static <E extends Throwable> RuntimeException rethrow(@Nonnull final Throwable exception) throws E {
    throw (E) exception;
  }

  /**
   * Propagates {@code throwable} as-is if it is an instance of {@link RuntimeException} or
   * {@link Error}, otherwise wraps it in a {@code RuntimeException} and then
   * propagates.
   *
   * @param throwable the throwable
   * @return nothing
   * @see Throwables#propagate(Throwable)
   * @see Throwables#throwIfUnchecked(Throwable)
   */
  public static RuntimeException propagate(@Nonnull final Throwable throwable) {
    Throwables.throwIfUnchecked(throwable);
    throw new RuntimeException(throwable);
  }
}
