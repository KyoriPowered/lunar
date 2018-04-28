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
package net.kyori.lunar.feature;

import net.kyori.lunar.proxy.MethodHandleInvocationHandler;
import net.kyori.lunar.proxy.Proxied;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

public class FeatureContextEntryImpl<F> implements FeatureContextEntry<F> {
  /**
   * The feature type.
   */
  private final @NonNull Class<F> type;
  /**
   * The id used for referencing this feature.
   */
  private final @NonNull String id;
  /**
   * The feature.
   */
  @SuppressWarnings("WeakerAccess")
  protected @MonotonicNonNull F feature;
  /**
   * The proxied feature.
   */
  private @MonotonicNonNull F proxiedFeature;

  @SuppressWarnings("WeakerAccess")
  protected FeatureContextEntryImpl(final @NonNull Class<F> type, final @NonNull String id) {
    this.type = type;
    this.id = id;
  }

  @Override
  public @NonNull Class<F> type() {
    return this.type;
  }

  @Override
  public @NonNull String id() {
    return this.id;
  }

  @Override
  public @NonNull F get() {
    if(this.feature != null) {
      return this.feature;
    }
    return this.proxy();
  }

  private F proxy() {
    if(this.proxiedFeature == null) {
      class ProxiedFeatureImpl extends MethodHandleInvocationHandler {
        @Override
        protected @NonNull Object object(final @NonNull Method method) {
          if(this.selfForMethod(method)) {
            return this;
          }
          return FeatureContextEntryImpl.this.feature();
        }

        private boolean selfForMethod(final Method thatMethod) {
          try {
            final Method thisMethod = this.getClass().getDeclaredMethod(thatMethod.getName(), thatMethod.getParameterTypes());
            return thisMethod != null;
          } catch(final NoSuchMethodException e) {
            return false;
          }
        }

        @Override
        public String toString() {
          final StringBuilder sb = new StringBuilder();
          sb.append("Proxied ").append(FeatureContextEntryImpl.this.type.getName()).append(" with id '").append(FeatureContextEntryImpl.this.id).append('\'');
          if(FeatureContextEntryImpl.this.defined()) {
            sb.append(": ").append(FeatureContextEntryImpl.this.feature);
          }
          return sb.toString();
        }
      }
      this.proxiedFeature = (F) Proxy.newProxyInstance(this.type.getClassLoader(), this.proxyClasses(), new ProxiedFeatureImpl());
    }
    return this.proxiedFeature;
  }

  private F feature() {
    if(!this.defined()) {
      throw new IllegalStateException("feature of type " + this.toString() + " has not been defined");
    }
    return this.feature;
  }

  @Override
  public boolean defined() {
    return this.feature != null;
  }

  @Override
  public void define(final F feature) {
    if(this.defined() && this.feature != feature) {
      throw new IllegalStateException(this.toString() + " already defined as " + this.feature + ", cannot redefine as " + feature);
    }
    this.feature = feature;
  }

  private Class<?>[] proxyClasses() {
    final List<Class<?>> classes = new ArrayList<>(2);
    this.proxyClasses(classes);
    return classes.toArray(new Class<?>[classes.size()]);
  }

  protected void proxyClasses(final List<Class<?>> classes) {
    classes.add(this.type);
    classes.add(Proxied.class);
  }

  @Override
  public String toString() {
    return this.type.getName() + " with id '" + this.id + '\'';
  }
}
