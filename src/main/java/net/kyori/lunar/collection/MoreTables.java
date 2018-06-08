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
package net.kyori.lunar.collection;

import com.google.common.collect.Table;
import com.google.common.collect.Tables;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.function.BiFunction;

/**
 * A collection of utilities for working with tables.
 *
 * @see Table
 * @see Tables
 */
public final class MoreTables {
  private MoreTables() {
  }

  /**
   * Gets the value of {@code rowKey} and {@code columnKey} from {@code table}, or computes
   * the value using {@code valueFunction} and inserts it into the table.
   *
   * @param table the table
   * @param rowKey the row key
   * @param columnKey the column key
   * @param valueFunction the value function
   * @param <R> the row type
   * @param <C> the column type
   * @param <V> the value type
   * @return the value
   */
  public static <R, C, V> /* @Nullable */ V computeIfAbsent(final @NonNull Table<R, C, V> table, final @Nullable R rowKey, final @Nullable C columnKey, final @NonNull BiFunction<? super R, ? super C, ? extends V> valueFunction) {
    /* @Nullable */ V value = table.get(rowKey, columnKey);
    if(value == null) {
      value = valueFunction.apply(rowKey, columnKey);
      table.put(rowKey, columnKey, value);
    }
    return value;
  }
}
