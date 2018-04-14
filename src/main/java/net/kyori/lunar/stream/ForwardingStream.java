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

import java.util.Comparator;
import java.util.Iterator;
import java.util.Optional;
import java.util.Spliterator;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;
import java.util.stream.Collector;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * A stream which forwards all its method calls to another stream.
 *
 * @param <T> the type of the stream elements
 */
public interface ForwardingStream<T> extends Stream<T> {
  /**
   * Gets the forwarded stream.
   *
   * @return the forwarded stream
   */
  @NonNull Stream<T> stream();

  @Override
  default Stream<T> filter(final Predicate<? super T> predicate) {
    return this.stream().filter(predicate);
  }

  @Override
  default <R> Stream<R> map(final Function<? super T, ? extends R> mapper) {
    return this.stream().map(mapper);
  }

  @Override
  default IntStream mapToInt(final ToIntFunction<? super T> mapper) {
    return this.stream().mapToInt(mapper);
  }

  @Override
  default LongStream mapToLong(final ToLongFunction<? super T> mapper) {
    return this.stream().mapToLong(mapper);
  }

  @Override
  default DoubleStream mapToDouble(final ToDoubleFunction<? super T> mapper) {
    return this.stream().mapToDouble(mapper);
  }

  @Override
  default <R> Stream<R> flatMap(final Function<? super T, ? extends Stream<? extends R>> mapper) {
    return this.stream().flatMap(mapper);
  }

  @Override
  default IntStream flatMapToInt(final Function<? super T, ? extends IntStream> mapper) {
    return this.stream().flatMapToInt(mapper);
  }

  @Override
  default LongStream flatMapToLong(final Function<? super T, ? extends LongStream> mapper) {
    return this.stream().flatMapToLong(mapper);
  }

  @Override
  default DoubleStream flatMapToDouble(final Function<? super T, ? extends DoubleStream> mapper) {
    return this.stream().flatMapToDouble(mapper);
  }

  @Override
  default Stream<T> distinct() {
    return this.stream().distinct();
  }

  @Override
  default Stream<T> sorted() {
    return this.stream().sorted();
  }

  @Override
  default Stream<T> sorted(final Comparator<? super T> comparator) {
    return this.stream().sorted(comparator);
  }

  @Override
  default Stream<T> peek(final Consumer<? super T> action) {
    return this.stream().peek(action);
  }

  @Override
  default Stream<T> limit(final long maxSize) {
    return this.stream().limit(maxSize);
  }

  @Override
  default Stream<T> skip(final long n) {
    return this.stream().skip(n);
  }

  @Override
  default Stream<T> takeWhile(final Predicate<? super T> predicate) {
    return this.stream().takeWhile(predicate);
  }

  @Override
  default Stream<T> dropWhile(final Predicate<? super T> predicate) {
    return this.stream().dropWhile(predicate);
  }

  @Override
  default void forEach(final Consumer<? super T> action) {
    this.stream().forEach(action);
  }

  @Override
  default void forEachOrdered(final Consumer<? super T> action) {
    this.stream().forEachOrdered(action);
  }

  @Override
  default Object[] toArray() {
    return this.stream().toArray();
  }

  @Override
  default <A> A[] toArray(final IntFunction<A[]> generator) {
    return this.stream().toArray(generator);
  }

  @Override
  default T reduce(final T identity, final BinaryOperator<T> accumulator) {
    return this.stream().reduce(identity, accumulator);
  }

  @Override
  default Optional<T> reduce(final BinaryOperator<T> accumulator) {
    return this.stream().reduce(accumulator);
  }

  @Override
  default <U> U reduce(final U identity, final BiFunction<U, ? super T, U> accumulator, final BinaryOperator<U> combiner) {
    return this.stream().reduce(identity, accumulator, combiner);
  }

  @Override
  default <R> R collect(final Supplier<R> supplier, final BiConsumer<R, ? super T> accumulator, final BiConsumer<R, R> combiner) {
    return this.stream().collect(supplier, accumulator, combiner);
  }

  @Override
  default <R, A> R collect(final Collector<? super T, A, R> collector) {
    return this.stream().collect(collector);
  }

  @Override
  default Optional<T> min(final Comparator<? super T> comparator) {
    return this.stream().min(comparator);
  }

  @Override
  default Optional<T> max(final Comparator<? super T> comparator) {
    return this.stream().max(comparator);
  }

  @Override
  default long count() {
    return this.stream().count();
  }

  @Override
  default boolean anyMatch(final Predicate<? super T> predicate) {
    return this.stream().anyMatch(predicate);
  }

  @Override
  default boolean allMatch(final Predicate<? super T> predicate) {
    return this.stream().allMatch(predicate);
  }

  @Override
  default boolean noneMatch(final Predicate<? super T> predicate) {
    return this.stream().noneMatch(predicate);
  }

  @Override
  default Optional<T> findFirst() {
    return this.stream().findFirst();
  }

  @Override
  default Optional<T> findAny() {
    return this.stream().findAny();
  }

  @Override
  default Iterator<T> iterator() {
    return this.stream().iterator();
  }

  @Override
  default Spliterator<T> spliterator() {
    return this.stream().spliterator();
  }

  @Override
  default boolean isParallel() {
    return this.stream().isParallel();
  }

  @Override
  default Stream<T> sequential() {
    return this.stream().sequential();
  }

  @Override
  default Stream<T> parallel() {
    return this.stream().parallel();
  }

  @Override
  default Stream<T> unordered() {
    return this.stream().unordered();
  }

  @Override
  default Stream<T> onClose(final Runnable closeHandler) {
    return this.stream().onClose(closeHandler);
  }

  @Override
  default void close() {
    this.stream().close();
  }
}
