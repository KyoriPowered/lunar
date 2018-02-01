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
package net.kyori.lunar.exception;

import com.google.common.base.Throwables;
import net.kyori.blizzard.NonNull;
import net.kyori.blizzard.Nullable;
import net.kyori.lunar.function.ThrowingBiConsumer;
import net.kyori.lunar.function.ThrowingBiFunction;
import net.kyori.lunar.function.ThrowingConsumer;
import net.kyori.lunar.function.ThrowingFunction;
import net.kyori.lunar.function.ThrowingSupplier;

import java.lang.reflect.InvocationTargetException;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * A collection of methods for working with exceptions.
 */
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
  @NonNull
  public static <T, E extends Exception> Consumer<T> rethrowConsumer(@NonNull final ThrowingConsumer<T, E> consumer) {
    return consumer;
  }

  /**
   * Returns a consumer which will unwrap and rethrow any throwables caught in {@code consumer}.
   *
   * @param consumer the consumer
   * @param <T> the input type
   * @param <E> the exception type
   * @return a consumer
   */
  @NonNull
  public static <T, E extends Exception> Consumer<T> unwrappingRethrowConsumer(@NonNull final ThrowingConsumer<T, E> consumer) {
    return input -> {
      try {
        consumer.throwingAccept(input);
      } catch(final Throwable t) {
        throw rethrow(unwrap(t));
      }
    };
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
  @NonNull
  public static <T, U, E extends Exception> BiConsumer<T, U> rethrowConsumer(@NonNull final ThrowingBiConsumer<T, U, E> consumer) {
    return consumer;
  }

  /**
   * Returns a consumer which will unwrap and rethrow any throwables caught in {@code consumer}.
   *
   * @param consumer the consumer
   * @param <T> the first input type
   * @param <U> the second input type
   * @param <E> the exception type
   * @return a consumer
   */
  @NonNull
  public static <T, U, E extends Exception> BiConsumer<T, U> unwrappingRethrowConsumer(@NonNull final ThrowingBiConsumer<T, U, E> consumer) {
    return (first, second) -> {
      try {
        consumer.throwingAccept(first, second);
      } catch(final Throwable th) {
        throw rethrow(unwrap(th));
      }
    };
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
  @NonNull
  public static <T, R, E extends Exception> Function<T, R> rethrowFunction(@NonNull final ThrowingFunction<T, R, E> function) {
    return function;
  }

  /**
   * Returns a function which will unwrap and rethrow any throwables caught in {@code function}.
   *
   * @param function the function
   * @param <T> the input type
   * @param <R> the output type
   * @param <E> the exception type
   * @return a function
   */
  @NonNull
  public static <T, R, E extends Exception> Function<T, R> unwrappingRethrowFunction(@NonNull final ThrowingFunction<T, R, E> function) {
    return input -> {
      try {
        return function.throwingApply(input);
      } catch(final Throwable t) {
        throw rethrow(unwrap(t));
      }
    };
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
  @NonNull
  public static <T, U, R, E extends Exception> BiFunction<T, U, R> rethrowFunction(@NonNull final ThrowingBiFunction<T, U, R, E> function) {
    return function;
  }

  /**
   * Returns a function which will unwrap and rethrow any throwables caught in {@code function}.
   *
   * @param function the function
   * @param <T> the first input type
   * @param <U> the second input type
   * @param <R> the output type
   * @param <E> the exception type
   * @return a bi-function
   */
  @NonNull
  public static <T, U, R, E extends Exception> BiFunction<T, U, R> unwrappingRethrowFunction(@NonNull final ThrowingBiFunction<T, U, R, E> function) {
    return (first, second) -> {
      try {
        return function.throwingApply(first, second);
      } catch(final Throwable t) {
        throw rethrow(unwrap(t));
      }
    };
  }

  /**
   * Returns the same throwing supplier.
   *
   * @param supplier the supplier
   * @param <T> the output type
   * @param <E> the exception type
   * @return the supplier
   */
  @NonNull
  public static <T, E extends Exception> Supplier<T> rethrowSupplier(@NonNull final ThrowingSupplier<T, E> supplier) {
    return supplier;
  }

  /**
   * Returns a supplier which will unwrap and rethrow any throwables caught in {@code supplier}.
   *
   * @param supplier the supplier
   * @param <T> the output type
   * @param <E> the exception type
   * @return a supplier
   */
  @NonNull
  public static <T, E extends Exception> Supplier<T> unwrappingRethrowSupplier(@NonNull final ThrowingSupplier<T, E> supplier) {
    return () -> {
      try {
        return supplier.throwingGet();
      } catch(final Throwable t) {
        throw rethrow(unwrap(t));
      }
    };
  }

  /**
   * Re-throws an exception, sneakily.
   *
   * @param exception the exception
   * @param <E> the exception type
   * @return nothing
   * @throws E the exception
   */
  @NonNull
  public static <E extends Throwable> RuntimeException rethrow(@NonNull final Throwable exception) throws E {
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
  public static RuntimeException propagate(@NonNull final Throwable throwable) {
    Throwables.throwIfUnchecked(throwable);
    throw new RuntimeException(throwable);
  }

  /**
   * Unwraps a throwable.
   *
   * @param throwable the throwable
   * @return the unwrapped throwable, or the original throwable
   */
  @NonNull
  public static Throwable unwrap(@NonNull final Throwable throwable) {
    if(throwable instanceof InvocationTargetException) {
      @Nullable final Throwable cause = throwable.getCause();
      if(cause != null) {
        return cause;
      }
    }
    return throwable;
  }
}
