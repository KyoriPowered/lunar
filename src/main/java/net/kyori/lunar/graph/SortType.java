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
package net.kyori.lunar.graph;

import net.kyori.blizzard.NonNull;

import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * The type of sorts performed by the topological sorter.
 * <p>This is not an enum because of generics.</p>
 */
@FunctionalInterface
interface SortType<T> {
  @NonNull
  Queue<T> makeQueue();

  SortType<?> RANDOM = ArrayDeque::new;
  SortType<? extends Comparable<?>> COMPARABLE = PriorityQueue::new;

  @SuppressWarnings("unchecked")
  @NonNull
  static <T> SortType<T> random() {
    return (SortType<T>) RANDOM;
  }

  @SuppressWarnings("unchecked")
  @NonNull
  static <T extends Comparable<?>> SortType<T> comparable() {
    return (SortType<T>) COMPARABLE;
  }
}