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

import java.util.Collection;

/**
 * A class of exception indicating that a failure of topological sort comes from the
 * presence of cycles in the graph. This class provides extra details on those cycles.
 *
 * <p>A strongly connected component is a part of a graph in which every node in the
 * component can reach all other nodes in the components via the paths in the
 * component; their existence makes topological sort impossible (unless the components
 * are single nodes).</p>
 *
 * <p>The class cannot carry a generic type because of Java limitations on
 * {@link Throwable} classes. However, {@link #components()} still allows you to
 * restore the generic types.</p>
 */
public final class CyclePresentException extends IllegalArgumentException {
  /**
   * This special generic type eases the transition from and to
   * {@code Collection<Collection<T>>}.
   */
  @NonNull private final Collection<? extends Collection<?>> components;

  /**
   * Constructs the exception. Only used by the topological sorters.
   *
   * @param message the error message
   * @param components the collection of strongly connected components
   */
  CyclePresentException(@NonNull final String message, @NonNull final Collection<? extends Collection<?>> components) {
    super(message);
    this.components = components;
  }

  /**
   * Accesses the strongly connected components.
   *
   * <p>Only those components with two or more nodes blocks the process
   * of topological sorting.</p>
   *
   * @param <T> the node type restored
   * @return the strongly connected components
   */
  @NonNull
  @SuppressWarnings("unchecked")
  public <T> Collection<Collection<T>> components() {
    return (Collection<Collection<T>>) this.components;
  }
}
