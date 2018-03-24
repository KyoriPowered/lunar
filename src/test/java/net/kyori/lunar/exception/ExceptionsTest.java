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
package net.kyori.lunar.exception;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.fail;

class ExceptionsTest {
  @Test
  void testRethrowConsumer() {
    try {
      this.consumer(Exceptions.rethrowConsumer(input -> { throw new TestException(); }));
    } catch(final TestException e) { return;
    } catch(final Exception ignored) {}
    fail("should have thrown TestException");
  }

  @Test
  void testUnwrappingRethrowConsumer() {
    try {
      this.consumer(Exceptions.unwrappingRethrowConsumer(input -> { throw new InvocationTargetException(new TestException()); }));
    } catch(final TestException e) { return;
    } catch(final Exception ignored) {}
    fail("should have thrown TestException");
  }

  @Test
  void testRethrowBiConsumer() {
    try {
      this.consumer(Exceptions.rethrowBiConsumer((a, b) -> { throw new TestException(); }));
    } catch(final TestException e) { return;
    } catch(final Exception ignored) {}
    fail("should have thrown TestException");
  }

  @Test
  void testUnwrappingRethrowBiConsumer() {
    try {
      this.consumer(Exceptions.unwrappingRethrowBiConsumer((a, b) -> { throw new InvocationTargetException(new TestException()); }));
    } catch(final TestException e) { return;
    } catch(final Exception ignored) {}
    fail("should have thrown TestException");
  }

  @Test
  void testRethrowFunction() {
    try {
      this.function(Exceptions.rethrowFunction(input -> { throw new TestException(); }));
    } catch(final TestException e) { return;
    } catch(final Exception ignored) {}
    fail("should have thrown TestException");
  }

  @Test
  void testUnwrappingRethrowFunction() {
    try {
      this.function(Exceptions.unwrappingRethrowFunction(input -> { throw new InvocationTargetException(new TestException()); }));
    } catch(final TestException e) { return;
    } catch(final Exception ignored) {}
    fail("should have thrown TestException");
  }

  @Test
  void testRethrowBiFunction() {
    try {
      this.function(Exceptions.rethrowBiFunction((a, b) -> { throw new TestException(); }));
    } catch(final TestException e) { return;
    } catch(final Exception ignored) {}
    fail("should have thrown TestException");
  }

  @Test
  void testUnwrappingRethrowBiFunction() {
    try {
      this.function(Exceptions.unwrappingRethrowBiFunction((a, b) -> { throw new InvocationTargetException(new TestException()); }));
    } catch(final TestException e) { return;
    } catch(final Exception ignored) {}
    fail("should have thrown TestException");
  }

  @Test
  void testRethrowSupplier() {
    try {
      this.supplier(Exceptions.rethrowSupplier(() -> { throw new TestException(); } ));
    } catch(final TestException e) { return;
    } catch(final Exception ignored) {}
    fail("should have thrown TestException");
  }

  @Test
  void testUnwrappingRethrowSupplier() {
    try {
      this.supplier(Exceptions.unwrappingRethrowSupplier(() -> { throw new InvocationTargetException(new TestException()); }));
    } catch(final TestException e) { return;
    } catch(final Exception ignored) {}
    fail("should have thrown TestException");
  }

  @Test
  void testPropagate() {
    final TestException te = new TestException();
    try {
      throw Exceptions.propagate(te);
    } catch(final RuntimeException e) {
      assertSame(te, e.getCause());
    }
  }

  @Test
  void testUnwrap() {
    final TestException te = new TestException();
    final InvocationTargetException ite = new InvocationTargetException(te);
    assertSame(te, Exceptions.unwrap(ite));
  }

  private void consumer(final Consumer<String> consumer) throws Exception { consumer.accept("kitten"); }
  private void consumer(final BiConsumer<String, String> consumer) throws Exception { consumer.accept("kitten", "kitty"); }
  private void function(final Function<String, String> function) throws Exception { function.apply("kitten"); }
  private void function(final BiFunction<String, String, String> function) throws Exception { function.apply("kitten", "kitty"); }
  private void supplier(final Supplier<String> supplier) throws Exception { supplier.get(); }

  private final class TestException extends Exception {}
}
