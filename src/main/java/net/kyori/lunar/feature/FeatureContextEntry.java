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
package net.kyori.lunar.feature;

import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * An entry in a {@link FeatureContext}.
 *
 * @param <F> the type
 */
public interface FeatureContextEntry<F> {
  /**
   * Gets the feature type.
   *
   * @return the feature type
   */
  @NonNull Class<F> type();

  /**
   * Gets the feature id.
   *
   * @return the feature id
   */
  @NonNull String id();

  /**
   * Gets the feature.
   *
   * <p>The returned feature may be proxied.</p>
   *
   * @return the feature
   */
  @NonNull F get();

  /**
   * Tests if the feature has been defined.
   *
   * @return {@code true} if the feature has been defined, {@code false} otherwise
   */
  boolean defined();

  /**
   * Defines the feature.
   *
   * @param feature the feature
   * @throws IllegalStateException if the feature has already been defined
   */
  void define(final @NonNull F feature) throws IllegalStateException;
}
