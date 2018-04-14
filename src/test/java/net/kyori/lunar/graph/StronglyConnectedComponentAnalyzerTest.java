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

import static org.junit.jupiter.api.Assertions.assertEquals;

class StronglyConnectedComponentAnalyzerTest {
  @Test
  void testOneComponents() {
    final MutableGraph<Integer> graph = GraphBuilder.directed().build();
    graph.addNode(1);
    graph.addNode(2);
    graph.addNode(3);
    graph.putEdge(1, 2);
    graph.putEdge(2, 3);
    graph.putEdge(3, 1);

    final StronglyConnectedComponentAnalyzer<Integer> analyzer = new StronglyConnectedComponentAnalyzer<>(graph);
    analyzer.analyze();
    assertEquals(1, analyzer.components().size());
    // one strongly connected component {1, 2, 3}
  }

  @Test
  void testTwoComponents() {
    final MutableGraph<Integer> graph = GraphBuilder.directed().build();
    graph.addNode(1);
    graph.addNode(2);
    graph.addNode(4);
    graph.addNode(5);
    graph.addNode(6);
    graph.addNode(3);
    graph.putEdge(1, 2);
    graph.putEdge(2, 3);
    graph.putEdge(3, 1);
    graph.putEdge(4, 6);
    graph.putEdge(6, 5);
    graph.putEdge(5, 4);
    graph.putEdge(1, 4);

    final StronglyConnectedComponentAnalyzer<Integer> analyzer = new StronglyConnectedComponentAnalyzer<>(graph);
    analyzer.analyze();
    assertEquals(2, analyzer.components().size());
    // two strongly connected component {1, 2, 3} {4, 5, 6}
  }
}
