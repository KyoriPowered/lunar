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

import com.google.common.util.concurrent.MoreExecutors;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @see Executors
 * @see MoreExecutors
 */
public final class EvenMoreExecutors {
  private EvenMoreExecutors() {
  }

  /**
   * Creates an {@link ExecutorService} whose {@code submit} and {@code invokeAll} methods submit
   * {@link RunnableCompletableFuture} instances to the given delegate executor.
   *
   * @param executorService the executor service
   * @return a completable executor service
   */
  public static CompletableExecutorService completableDecorator(final ExecutorService executorService) {
    if(executorService instanceof CompletableExecutorService) {
      return (CompletableExecutorService) executorService;
    }
    return new CompletableDecorator(executorService);
  }

  private static final class CompletableDecorator extends AbstractCompletableExecutorService implements ForwardingExecutorService {
    private final ExecutorService executorService;

    CompletableDecorator(final ExecutorService executorService) {
      this.executorService = executorService;
    }

    @NonNull
    @Override
    public ExecutorService executorService() {
      return this.executorService;
    }
  }
}
