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

import com.google.common.graph.Graph;
import net.kyori.blizzard.NonNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * A collection of a few graph algorithms.
 */
public final class MoreGraphs {
  private MoreGraphs() {
  }

  /**
   * Sorts a directed acyclic graph into a list.
   *
   * <p>The particular order of elements without prerequisites is not guaranteed.</p>
   *
   * @param graph the graph to be sorted
   * @param <T> the node type
   * @return the sorted list
   * @throws IllegalArgumentException if the graph is not directed, allows self loops, or has cycles
   */
  @NonNull
  public static <T> List<T> topologicalSort(@NonNull final Graph<T> graph) {
    return topologicalSort(graph, SortType.random());
  }

  /**
   * Sorts a directed acyclic graph into a list.
   *
   * <p>The particular order of elements without prerequisites is determined by the comparator.</p>
   *
   * @param graph the graph to be sorted
   * @param comparator the comparator
   * @param <T> the node type
   * @return the sorted list
   * @throws IllegalArgumentException if the graph is not directed, allows self loops, or has cycles
   */
  @NonNull
  public static <T> List<T> orderedTopologicalSort(@NonNull final Graph<T> graph, @NonNull final Comparator<T> comparator) {
    return topologicalSort(graph, new ComparatorSortType<>(comparator));
  }

  /**
   * Sorts a directed acyclic graph into a list.
   *
   * <p>The particular order of elements without prerequisites is determined by the natural order.</p>
   *
   * @param graph the graph to be sorted
   * @param <T> the node type, implementing {@link Comparable}
   * @return the sorted list
   * @throws IllegalArgumentException if the graph is not directed, allows self loops, or has cycles
   */
  @NonNull
  public static <T extends Comparable<? super T>> List<T> orderedTopologicalSort(@NonNull final Graph<T> graph) {
    return topologicalSort(graph, SortType.comparable());
  }

  /**
   * Actual content of the topological sort. This is a breadth-first search based approach.
   *
   * @param graph the graph to be sorted
   * @param type the sort type
   * @param <T> the node type
   * @return the sorted list
   * @throws IllegalArgumentException if the graph is not directed, allows self loops, or has cycles
   */
  @NonNull
  private static <T> List<T> topologicalSort(@NonNull final Graph<T> graph, @NonNull final SortType<T> type) {
    checkArgument(graph.isDirected(), "the graph must be directed");
    checkArgument(!graph.allowsSelfLoops(), "the graph cannot allow self loops");

    final Map<T, Integer> requiredCounts = new HashMap<>();

    for(final T node : graph.nodes()) {
      for(final T successor : graph.successors(node)) {
        requiredCounts.merge(successor, 1, (a, b) -> a + b);
      }
    }

    final Queue<T> processing = type.createQueue();
    final List<T> results = new ArrayList<>();

    for(final T node : graph.nodes()) {
      if(!requiredCounts.containsKey(node)) {
        processing.add(node);
      }
    }

    while(!processing.isEmpty()) {
      final T now = processing.poll();
      for(final T successor : graph.successors(now)) {
        final int newCount = requiredCounts.get(successor) - 1;
        if(newCount == 0) {
          processing.add(successor);
          requiredCounts.remove(successor);
        } else {
          requiredCounts.put(successor, newCount);
        }
      }
      results.add(now);
    }

    if(!requiredCounts.isEmpty()) {
      final StronglyConnectedComponentAnalyzer<T> analyzer = new StronglyConnectedComponentAnalyzer<>(graph);
      analyzer.analyze();
      throw new IllegalArgumentException("Graph (" + graph + ") has cycle(s): " + analyzer.renderCycles());
    }
    return results;
  }
}
