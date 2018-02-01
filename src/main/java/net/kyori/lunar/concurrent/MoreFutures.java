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

import com.google.common.util.concurrent.Futures;
import net.kyori.blizzard.NonNull;

import java.util.concurrent.CompletableFuture;

/**
 * @see Futures
 */
public final class MoreFutures {
  private MoreFutures() {
  }

  /**
   * Returns a completable future that is exceptionally completed with the provided exception.
   *
   * @param ex the throwable
   * @param <T> the type
   * @return an exceptionally completed completable future
   */
  @NonNull
  public static <T> CompletableFuture<T> immediateFailedFuture(@NonNull final Throwable ex) {
    final CompletableFuture<T> future = new CompletableFuture<>();
    future.completeExceptionally(ex);
    return future;
  }
}
