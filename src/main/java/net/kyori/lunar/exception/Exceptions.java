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
import net.kyori.lunar.function.ThrowingBiConsumer;
import net.kyori.lunar.function.ThrowingBiFunction;
import net.kyori.lunar.function.ThrowingConsumer;
import net.kyori.lunar.function.ThrowingFunction;
import net.kyori.lunar.function.ThrowingPredicate;
import net.kyori.lunar.function.ThrowingRunnable;
import net.kyori.lunar.function.ThrowingSupplier;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.lang.reflect.InvocationTargetException;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
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
  public static <T, E extends Throwable> @NonNull Consumer<T> rethrowConsumer(final @NonNull ThrowingConsumer<T, E> consumer) {
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
  public static <T, E extends Throwable> @NonNull Consumer<T> unwrappingRethrowConsumer(final @NonNull ThrowingConsumer<T, E> consumer) {
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
  public static <T, U, E extends Throwable> @NonNull BiConsumer<T, U> rethrowBiConsumer(final @NonNull ThrowingBiConsumer<T, U, E> consumer) {
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
  public static <T, U, E extends Throwable> @NonNull BiConsumer<T, U> unwrappingRethrowBiConsumer(final @NonNull ThrowingBiConsumer<T, U, E> consumer) {
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
  public static <T, R, E extends Throwable> @NonNull Function<T, R> rethrowFunction(final @NonNull ThrowingFunction<T, R, E> function) {
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
  public static <T, R, E extends Throwable> @NonNull Function<T, R> unwrappingRethrowFunction(final @NonNull ThrowingFunction<T, R, E> function) {
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
  public static <T, U, R, E extends Throwable> @NonNull BiFunction<T, U, R> rethrowBiFunction(final @NonNull ThrowingBiFunction<T, U, R, E> function) {
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
  public static <T, U, R, E extends Throwable> @NonNull BiFunction<T, U, R> unwrappingRethrowBiFunction(final @NonNull ThrowingBiFunction<T, U, R, E> function) {
    return (first, second) -> {
      try {
        return function.throwingApply(first, second);
      } catch(final Throwable t) {
        throw rethrow(unwrap(t));
      }
    };
  }

  /**
   * Returns the same throwing predicate.
   *
   * @param predicate the predicate
   * @param <T> the input type
   * @param <E> the exception type
   * @return the predicate
   */
  public static <T, E extends Throwable> @NonNull Predicate<T> rethrowPredicate(final @NonNull ThrowingPredicate<T, E> predicate) {
    return predicate;
  }

  /**
   * Returns a predicate which will unwrap and rethrow any throwables caught in {@code predicate}.
   *
   * @param predicate the predicate
   * @param <T> the input type
   * @param <E> the exception type
   * @return a predicate
   */
  public static <T, E extends Throwable> @NonNull Predicate<T> unwrappingRethrowPredicate(final @NonNull ThrowingPredicate<T, E> predicate) {
    return input -> {
      try {
        return predicate.throwingTest(input);
      } catch(final Throwable t) {
        throw rethrow(unwrap(t));
      }
    };
  }

  /**
   * Runs a throwable and, sneakily, re-throws any exceptions it encounters.
   *
   * @param runnable the runnable
   * @param <E> the exception type
   */
  public static <E extends Throwable> @NonNull Runnable rethrowRunnable(final @NonNull ThrowingRunnable<E> runnable) {
    return runnable;
  }

  /**
   * Returns the same throwing supplier.
   *
   * @param supplier the supplier
   * @param <T> the output type
   * @param <E> the exception type
   * @return the supplier
   */
  public static <T, E extends Throwable> @NonNull Supplier<T> rethrowSupplier(final @NonNull ThrowingSupplier<T, E> supplier) {
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
  public static <T, E extends Throwable> @NonNull Supplier<T> unwrappingRethrowSupplier(final @NonNull ThrowingSupplier<T, E> supplier) {
    return () -> {
      try {
        return supplier.throwingGet();
      } catch(final Throwable t) {
        throw rethrow(unwrap(t));
      }
    };
  }

  /**
   * Gets the result of {@code supplier}, or re-throws an exception, sneakily.
   *
   * @param supplier the supplier
   * @param <T> the result type
   * @param <E> the exception type
   * @return the result
   */
  public static <T, E extends Throwable> @NonNull T getOrRethrow(final @NonNull ThrowingSupplier<T, E> supplier) {
    return supplier.get(); // get() rethrows for us
  }

  /**
   * Re-throws an exception, sneakily.
   *
   * @param exception the exception
   * @param <E> the exception type
   * @return nothing
   * @throws E the exception
   */
  public static <E extends Throwable> @NonNull RuntimeException rethrow(final @NonNull Throwable exception) throws E {
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
  public static RuntimeException propagate(final @NonNull Throwable throwable) {
    Throwables.throwIfUnchecked(throwable);
    throw new RuntimeException(throwable);
  }

  /**
   * Unwraps a throwable.
   *
   * @param throwable the throwable
   * @return the unwrapped throwable, or the original throwable
   */
  public static @NonNull Throwable unwrap(final @NonNull Throwable throwable) {
    if(throwable instanceof InvocationTargetException) {
      final /* @Nullable */ Throwable cause = throwable.getCause();
      if(cause != null) {
        return cause;
      }
    }
    return throwable;
  }
}
