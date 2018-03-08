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

import com.google.common.graph.GraphBuilder;
import com.google.common.graph.MutableGraph;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MoreGraphsTest {
  @Test
  void testTopologicalSort() {
    final MutableGraph<Integer> graph = GraphBuilder.directed().build();
    graph.addNode(1);
    graph.addNode(2);
    graph.addNode(3);
    graph.putEdge(1, 2);
    graph.putEdge(2, 3);
    graph.putEdge(1, 3);

    final List<Integer> results = MoreGraphs.topologicalSort(graph);
    assertEquals(1, results.get(0).intValue());
    assertEquals(2, results.get(1).intValue());
    assertEquals(3, results.get(2).intValue());
  }

  @Test
  void testTopologicalSortWithCircle() {
    assertThrows(CyclePresentException.class, () -> {
      final MutableGraph<Integer> graph = GraphBuilder.directed().build();
      graph.addNode(1);
      graph.addNode(2);
      graph.addNode(3);
      graph.putEdge(1, 2);
      graph.putEdge(2, 3);
      graph.putEdge(3, 1);

      final List<Integer> results = MoreGraphs.topologicalSort(graph);
      assertEquals(1, results.get(0).intValue());
      assertEquals(2, results.get(1).intValue());
      assertEquals(3, results.get(2).intValue());
    });
  }

  @Test
  void testTopologicalSortNaturalOrder() {
    final MutableGraph<Integer> graph = GraphBuilder.directed().build();
    graph.addNode(1);
    graph.addNode(2);
    graph.addNode(3);
    graph.addNode(5);
    graph.addNode(4);
    graph.putEdge(1, 3);
    graph.putEdge(1, 5);
    graph.putEdge(1, 4);
    graph.putEdge(1, 2);

    final List<Integer> results = MoreGraphs.orderedTopologicalSort(graph);
    assertEquals(1, results.get(0).intValue());
    assertEquals(2, results.get(1).intValue());
    assertEquals(3, results.get(2).intValue());
    assertEquals(4, results.get(3).intValue());
    assertEquals(5, results.get(4).intValue());
  }

  @Test
  void testTopologicalSortComparator() {
    final MutableGraph<Integer> graph = GraphBuilder.directed().build();
    graph.addNode(1);
    graph.addNode(2);
    graph.addNode(3);
    graph.addNode(5);
    graph.addNode(4);
    graph.putEdge(1, 3);
    graph.putEdge(1, 5);
    graph.putEdge(1, 4);
    graph.putEdge(1, 2);

    final List<Integer> results = MoreGraphs.orderedTopologicalSort(graph, Comparator.reverseOrder());
    assertEquals(1, results.get(0).intValue());
    assertEquals(5, results.get(1).intValue());
    assertEquals(4, results.get(2).intValue());
    assertEquals(3, results.get(3).intValue());
    assertEquals(2, results.get(4).intValue());
  }
}
