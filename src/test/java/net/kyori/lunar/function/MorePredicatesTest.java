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
package net.kyori.lunar.function;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.junit.jupiter.api.Test;

import java.util.Objects;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

final class MorePredicatesTest {
  @Test
  void testTrue() {
    final Predicate<?> a = MorePredicates.alwaysTrue();
    final Predicate<?> b = MorePredicates.alwaysTrue();
    assertEquals(a, b);
  }

  @Test
  void testFalse() {
    final Predicate<?> a = MorePredicates.alwaysFalse();
    final Predicate<?> b = MorePredicates.alwaysFalse();
    assertEquals(a, b);
  }

  @Test
  void testTruePrivately1() {
    final Predicate<?> a = MorePredicatesTest.alwaysTrue();
    final Predicate<?> b = MorePredicatesTest.alwaysTrue();
    assertEquals(a, b);
  }

  @Test
  void testTruePrivately2() {
    final Predicate<?> a = MorePredicatesTest.alwaysTrue();
    final Predicate<?> b = MorePredicatesTest.alwaysTrueAgain();
    assertNotEquals(a, b);
  }

  @Test
  void testObjectsNonNull1() {
    final Predicate<?> a = MorePredicatesTest.objNonNull();
    final Predicate<?> b = MorePredicatesTest.objNonNull();
    assertEquals(a, b);
  }

  @Test
  void testObjectsNonNull2() {
    final Predicate<?> a = MorePredicatesTest.objNonNull();
    final Predicate<?> b = MorePredicatesTest.objNonNullToo();
    assertNotEquals(a, b);
  }

  private static <T> @NonNull Predicate<T> alwaysTrue() {
    return t -> true;
  }

  private static <T> @NonNull Predicate<T> alwaysTrueAgain() {
    return t -> true;
  }

  private static <T> @NonNull Predicate<T> objNonNull() {
    return Objects::nonNull;
  }

  private static <T> @NonNull Predicate<T> objNonNullToo() {
    return Objects::nonNull;
  }
}
