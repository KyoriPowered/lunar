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
package net.kyori.lunar;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EvenMoreObjectsTest {
  @Test
  void testMakeSupplier() {
    final List<String> values = EvenMoreObjects.make(() -> Arrays.asList("abc", "def"));
    assertEquals(2, values.size());
    assertEquals("abc", values.get(0));
    assertEquals("def", values.get(1));
  }

  @Test
  void testMakeConsumer() {
    final List<String> values = EvenMoreObjects.make(new ArrayList<>(), (list) -> {
      list.add("abc");
      list.add("def");
    });
    assertEquals(2, values.size());
    assertEquals("abc", values.get(0));
    assertEquals("def", values.get(1));
  }

  @Test
  void testMakeCreator() {
    final List<String> values = EvenMoreObjects.make(
      new ArrayList<String>(),
      (list) -> {
        list.add("abc");
        list.add("def");
      },
      Collections::unmodifiableList
    );
    assertEquals(2, values.size());
    assertEquals("abc", values.get(0));
    assertEquals("def", values.get(1));
    assertThrows(UnsupportedOperationException.class, () -> values.add("ghi"));
  }

  @Test
  void testEquals() {
    assertEquals(new Foo(0), new Foo(0));
    assertNotEquals(new Foo(0), new Foo(1));
  }

  private static class Foo {
    private final int baz;

    private Foo(final int baz) {
      this.baz = baz;
    }

    @Override
    public boolean equals(final Object other) {
      return EvenMoreObjects.equals(this, other, that -> {
        return this.baz == that.baz;
      });
    }
  }
}
