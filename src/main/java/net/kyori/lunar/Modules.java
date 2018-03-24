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
package net.kyori.lunar;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.lang.module.Configuration;
import java.lang.module.ModuleFinder;
import java.nio.file.Path;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * A collection of utilities for working with the module system.
 */
public final class Modules {
  private Modules() {
  }

  /**
   * Creates a new module layer from a parent layer and a set of paths.
   *
   * @param paths the paths
   * @return a new layer
   */
  @NonNull
  public static ModuleLayer createLayer(@NonNull final Set<Path> paths) {
    return createLayer(ModuleLayer.boot(), paths);
  }

  /**
   * Creates a new module layer from a parent layer and a set of paths.
   *
   * @param parent the parent layer
   * @param paths the paths
   * @return a new layer
   */
  @NonNull
  public static ModuleLayer createLayer(@NonNull final ModuleLayer parent, @NonNull final Set<Path> paths) {
    final ModuleFinder finder = ModuleFinder.of(paths.toArray(new Path[paths.size()]));
    final Set<String> modules = finder.findAll().stream().map(reference -> reference.descriptor().name()).collect(Collectors.toSet());
    final Configuration configuration = parent.configuration().resolve(finder, ModuleFinder.of(), modules);
    final ClassLoader loader = ClassLoader.getSystemClassLoader();
    return parent.defineModulesWithOneLoader(configuration, loader);
  }
}
