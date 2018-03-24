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

import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * An executor service that returns {@link CompletableFuture} instances.
 */
public interface CompletableExecutorService extends ExecutorService {
  /**
   * {@inheritDoc}
   *
   * @param task the task to submit
   * @param <T> the type of the task's result
   * @return a {@code CompletableFuture} representing pending completion of the task
   * @throws NullPointerException if the task is null
   * @throws RejectedExecutionException if the task cannot be scheduled for execution
   */
  @NonNull
  @Override
  <T> CompletableFuture<T> submit(@NonNull final Callable<T> task);

  /**
   * {@inheritDoc}
   *
   * @param task the task to submit
   * @param result the result to return
   * @param <T> the type of the result
   * @return a {@code CompletableFuture} representing pending completion of the task
   * @throws NullPointerException if the task is null
   * @throws RejectedExecutionException if the task cannot be scheduled for execution
   */
  @NonNull
  @Override
  <T> CompletableFuture<T> submit(@NonNull final Runnable task, @Nullable final T result);

  /**
   * {@inheritDoc}
   *
   * @param task the task to submit
   * @return a {@code CompletableFuture} representing pending completion of the task
   * @throws NullPointerException if the task is null
   * @throws RejectedExecutionException {@inheritDoc}
   */
  @NonNull
  @Override
  CompletableFuture<?> submit(@NonNull final Runnable task);

  /**
   * {@inheritDoc}
   *
   * @param tasks the collection of tasks
   * @param <T> the type of the values returned from the tasks
   * @return a list of {@code CompletableFuture}s representing the tasks, in the same sequential
   *     order as produced by the iterator for the given task list, each of which has completed
   * @throws InterruptedException if interrupted while waiting, in which case unfinished tasks are cancelled
   */
  @NonNull
  @Override
  <T> List<Future<T>> invokeAll(@NonNull final Collection<? extends Callable<T>> tasks) throws InterruptedException;

  /**
   * {@inheritDoc}
   *
   * @param tasks the collection of tasks
   * @param timeout the maximum time to wait
   * @param unit the time unit of the timeout argument
   * @param <T> the type of the values returned from the tasks
   * @return a list of {@code CompletableFuture}s representing the tasks, in the same sequential
   *     order as produced by the iterator for the given task list. If the operation did not time
   *     out, each task will have completed. If it did time out, some of these tasks will not have
   *     completed
   * @throws InterruptedException if interrupted while waiting, in which case unfinished tasks are cancelled
   */
  @NonNull
  @Override
  <T> List<Future<T>> invokeAll(@NonNull final Collection<? extends Callable<T>> tasks, final long timeout, @NonNull final TimeUnit unit) throws InterruptedException;
}
