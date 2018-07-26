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
package net.kyori.lunar.stream;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *  A stream with friendly methods.
 *
 * @param <T> the type of the stream elements
 */
public interface FriendlyStream<T> extends Stream<T> {
  /**
   * Creates a wrapped stream.
   *
   * @param stream the stream
   * @param <T> the type of the stream elements
   * @return a friendly stream
   */
  static <T> @NonNull FriendlyStream<T> of(final @NonNull Stream<T> stream) {
    if(stream instanceof FriendlyStream<?>) {
      return (FriendlyStream<T>) stream;
    }
    return new FriendlyStreamImpl<>(stream);
  }

  /**
   * Collection.
   *
   * @param collectionFactory a supplier which returns a new, empty collection of the appropriate type
   * @param <C> the type of the resulting collection
   * @return a collection
   * @see Collectors#toCollection(Supplier)
   */
  default <C extends Collection<T>> @NonNull C toCollection(final @NonNull Supplier<C> collectionFactory) {
    return this.collect(Collectors.toCollection(collectionFactory));
  }

  /**
   * List.
   *
   * @return a list
   * @see Collectors#toList()
   */
  default @NonNull List<T> toList() {
    return this.collect(Collectors.toList());
  }

  /**
   * Map.
   *
   * @param keyMapper a mapping function to produce keys
   * @param valueMapper a mapping function to produce values
   * @param <K> the output type of the key mapping function
   * @param <V> the output type of the value mapping function
   * @return a map whose keys and values are the result of applying mapping functions to the input elements
   * @see Collectors#toMap(Function, Function)
   */
  default <K, V> @NonNull Map<K, V> toMap(final @NonNull Function<? super T, ? extends K> keyMapper, final @NonNull Function<? super T, ? extends V> valueMapper) {
    return this.collect(Collectors.toMap(keyMapper, valueMapper));
  }

  /**
   * Set.
   *
   * @return a set
   * @see Collectors#toSet()
   */
  default @NonNull Set<T> toSet() {
    return this.collect(Collectors.toSet());
  }
}
