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
package net.kyori.lunar.concurrent;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.RunnableFuture;

public abstract class AbstractCompletableExecutorService extends AbstractExecutorService implements CompletableExecutorService {
  @Override
  public @NonNull CompletableFuture<?> submit(final @NonNull Runnable task) {
    return (CompletableFuture<?>) super.submit(task);
  }

  @Override
  public <T> @NonNull CompletableFuture<T> submit(final @NonNull Runnable task, final @Nullable T result) {
    return (CompletableFuture<T>) super.submit(task, result);
  }

  @Override
  public <T> @NonNull CompletableFuture<T> submit(final @NonNull Callable<T> task) {
    return (CompletableFuture<T>) super.submit(task);
  }

  @Override
  protected <T> RunnableFuture<T> newTaskFor(final @NonNull Runnable runnable, final @Nullable T value) {
    return new RunnableCompletableFuture<>(Executors.callable(runnable, value));
  }

  @Override
  protected <T> RunnableFuture<T> newTaskFor(final @NonNull Callable<T> callable) {
    return new RunnableCompletableFuture<>(callable);
  }
}
