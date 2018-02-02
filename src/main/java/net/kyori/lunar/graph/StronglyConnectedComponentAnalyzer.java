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
package net.kyori.lunar.graph;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.graph.Graph;
import net.kyori.blizzard.NonNegative;
import net.kyori.blizzard.NonNull;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * An analyzer finding out all the strongly connected components in a graph.
 *
 * <p>This is based on Tarjan's Strongly Connected Component algorithm.</p>
 *
 * @param <T> the graph's node type
 */
@VisibleForTesting
final class StronglyConnectedComponentAnalyzer<T> {
  @NonNull private final Map<T, Integer> ids = new HashMap<>();
  @NonNull private final Deque<T> stack = new ArrayDeque<>();
  @NonNull private final Collection<Collection<T>> components = new ArrayList<>();
  @NonNegative private int currentId = 0;
  @NonNull private final Graph<T> graph;
  @NonNull private final int[] low;

  @VisibleForTesting
  StronglyConnectedComponentAnalyzer(@NonNull final Graph<T> graph) {
    this.graph = graph;
    this.low = new int[graph.nodes().size()];
  }

  @VisibleForTesting
  void analyze() {
    for(final T node : this.graph.nodes()) {
      if(!this.ids.containsKey(node)) {
        this.dfs(node);
      }
    }
  }

  @NonNull
  @VisibleForTesting
  Collection<Collection<T>> getComponents() {
    return this.components;
  }

  @NonNull
  String renderCycles() {
    final StringBuilder reporter = new StringBuilder();
    for(final Collection<T> component : this.components) {
      if(component.size() > 1) {
        reporter.append('{');
        boolean separate = false;
        for(final T each : component) {
          if(separate) {
            reporter.append(',');
            reporter.append(' ');
          } else {
            separate = true;
          }
          reporter.append(Objects.toString(each));
        }
        reporter.append('}').append(';');
      }
    }
    return reporter.toString();
  }

  private void dfs(final T now) {
    this.ids.put(now, this.currentId);
    final int self = this.currentId;
    this.low[self] = self;
    this.stack.push(now);
    this.currentId++;

    for(final T node : this.graph.successors(now)) {
      if(this.ids.containsKey(node)) {
        this.low[self] = Math.min(this.ids.get(node), this.low[self]);
      } else {
        this.dfs(node);
        this.low[self] = Math.min(this.low[this.ids.get(node)], this.low[self]);
      }
    }

    if(this.low[self] == self) {
      final Collection<T> component = new ArrayList<>();
      T removed;
      do {
        removed = this.stack.pop();
        component.add(removed);
      } while(removed != now);
      this.components.add(component);
    }
  }
}
