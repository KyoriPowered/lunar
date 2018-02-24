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
package net.kyori.lunar.collection;

import com.google.common.collect.Iterables;

import java.util.Collection;
import java.util.Random;

/**
 * A collection of utilities for working with iterables.
 */
public final class MoreIterables {
    private static final Random RANDOM = new Random();

    private MoreIterables() {
    }

    /**
     * Gets a random element from an iterable.
     *
     * @param iterable the iterable
     * @param <T> the type
     * @return a random element
     */
    public static <T> T random(final Iterable<T> iterable) {
        return random(RANDOM, iterable);
    }

    /**
     * Gets a random element from an iterable.
     *
     * @param iterable the iterable
     * @param allowed a collection of allowed values
     * @param <T> the type
     * @return a random element
     */
    public static <T> T random(final Iterable<T> iterable, final Collection<T> allowed) {
        return random(Iterables.filter(iterable, allowed::contains));
    }

    /**
     * Gets a random element from an iterable.
     *
     * @param random the random
     * @param iterable the iterable
     * @param <T> the type
     * @return a random element
     */
    public static <T> T random(final Random random, final Iterable<T> iterable) {
        final int index = random.nextInt(Iterables.size(iterable));
        return Iterables.get(iterable, index);
    }

    /**
     * Gets a random element from an iterable.
     *
     * @param random the random
     * @param iterable the iterable
     * @param allowed a collection of allowed values
     * @param <T> the type
     * @return a random element
     */
    public static <T> T random(final Random random, final Iterable<T> iterable, final Collection<T> allowed) {
        return random(random, Iterables.filter(iterable, allowed::contains));
    }
}
