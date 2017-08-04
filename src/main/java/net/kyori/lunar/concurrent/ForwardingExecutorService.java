/*
 * This file is part of lunar, licensed under the MIT License.
 *
 * Copyright (c) 2017 KyoriPowered
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
package net.kyori.lunar.concurrent;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.annotation.Nonnull;

/**
 * An executor service which forwards all its method calls to another executor service.
 */
public interface ForwardingExecutorService extends ExecutorService {

  /**
   * Gets the forwarded executor service that methods are forwarded to.
   *
   * @return the forwarded executor service
   */
  @Nonnull
  ExecutorService executorService();

  @Override
  default void shutdown() {
    this.executorService().shutdown();
  }

  @Nonnull
  @Override
  default List<Runnable> shutdownNow() {
    return this.executorService().shutdownNow();
  }

  @Override
  default boolean isShutdown() {
    return this.executorService().isShutdown();
  }

  @Override
  default boolean isTerminated() {
    return this.executorService().isTerminated();
  }

  @Override
  default boolean awaitTermination(final long timeout, @Nonnull final TimeUnit unit) throws InterruptedException {
    return this.executorService().awaitTermination(timeout, unit);
  }

  @Nonnull
  @Override
  default <T> Future<T> submit(@Nonnull final Callable<T> task) {
    return this.executorService().submit(task);
  }

  @Nonnull
  @Override
  default <T> Future<T> submit(@Nonnull final Runnable task, final T result) {
    return this.executorService().submit(task, result);
  }

  @Nonnull
  @Override
  default Future<?> submit(@Nonnull final Runnable task) {
    return this.executorService().submit(task);
  }

  @Nonnull
  @Override
  default <T> List<Future<T>> invokeAll(@Nonnull final Collection<? extends Callable<T>> tasks) throws InterruptedException {
    return this.executorService().invokeAll(tasks);
  }

  @Nonnull
  @Override
  default <T> List<Future<T>> invokeAll(@Nonnull final Collection<? extends Callable<T>> tasks, final long timeout, @Nonnull final TimeUnit unit) throws InterruptedException {
    return this.executorService().invokeAll(tasks, timeout, unit);
  }

  @Nonnull
  @Override
  default <T> T invokeAny(@Nonnull final Collection<? extends Callable<T>> tasks) throws ExecutionException, InterruptedException {
    return this.executorService().invokeAny(tasks);
  }

  @Override
  default <T> T invokeAny(@Nonnull final Collection<? extends Callable<T>> tasks, final long timeout, @Nonnull final TimeUnit unit) throws ExecutionException, InterruptedException, TimeoutException {
    return this.executorService().invokeAny(tasks, timeout, unit);
  }

  @Override
  default void execute(@Nonnull final Runnable command) {
    this.executorService().execute(command);
  }
}
