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

import com.google.common.base.MoreObjects;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import net.kyori.lunar.proxy.Proxied;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

public class FeatureContextImpl implements FeatureContext {
  protected final Table<Class<?>, String, FeatureContextEntry<?>> entries = HashBasedTable.create();

  @Override
  public <F> @NonNull F get(final @NonNull Class<F> type, final @NonNull String id) {
    return this.entry(type, id).get();
  }

  @Override
  public <F> F add(final @NonNull Class<F> type, final @Nullable String id, final @NonNull F feature) {
    // Don't insert a proxied feature.
    if(feature instanceof Proxied) {
      return feature;
    }

    // This feature has an id, and can be referenced.
    if(id != null) {
      this.entry(type, id).define(feature);
    }

    return feature;
  }

  protected <F> FeatureContextEntry<F> entry(final Class<F> type, final @NonNull String id) {
    FeatureContextEntry<F> entry = (FeatureContextEntry<F>) this.entries.get(type, id);
    if(entry == null) {
      entry = this.createEntry(type, id);
      this.entries.put(type, id, entry);
    }
    return entry;
  }

  protected <F> FeatureContextEntry<F> createEntry(final @NonNull Class<F> type, final @NonNull String id) {
    return new FeatureContextEntryImpl<>(type, id);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
      .addValue(this.entries)
      .toString();
  }
}
