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
package net.kyori.lunar.reflect;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.function.Predicate;

/**
 * A collection of utilities for working with class hierarchy.
 */
public final class Hierarchy {
  private Hierarchy() {
  }

  /**
   * Finds a class in the hierarchy matching the provided {@code type} and {@code predicate}.
   *
   * @param first the first class, used to start the search
   * @param type the type to allow
   * @param predicate the predicate to find a result
   * @param <T> the type
   * @return a class matching the {@code type} and {@code predicate}
   */
  // thanks, kenzie
  public static <T> @Nullable Class<? extends T> find(final @NonNull Class<? extends T> first, final @NonNull Class<T> type, final @NonNull Predicate<Class<? extends T>> predicate) {
    final Deque<Class<? extends T>> classes = new ArrayDeque<>();
    classes.add(first);

    while(!classes.isEmpty()) {
      final /* @Nullable */ Class<? extends T> next = classes.remove();

      if(predicate.test(next)) {
        return next;
      }

      final /* @Nullable */ Class<?> parent = next.getSuperclass();
      if(parent != null && type.isAssignableFrom(parent)) {
        classes.add(parent.asSubclass(type));
      }

      for(final /* @NonNull */ Class<?> interfaceType : next.getInterfaces()) {
        if(type.isAssignableFrom(interfaceType)) {
          classes.add(interfaceType.asSubclass(type));
        }
      }
    }

    return null;
  }
}
