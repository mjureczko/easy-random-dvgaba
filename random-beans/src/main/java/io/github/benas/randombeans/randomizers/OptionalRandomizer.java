/*
 * The MIT License
 *
 *   Copyright (c) 2016, Mahmoud Ben Hassine (mahmoud.benhassine@icloud.com)
 *
 *   Permission is hereby granted, free of charge, to any person obtaining a copy
 *   of this software and associated documentation files (the "Software"), to deal
 *   in the Software without restriction, including without limitation the rights
 *   to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *   copies of the Software, and to permit persons to whom the Software is
 *   furnished to do so, subject to the following conditions:
 *
 *   The above copyright notice and this permission notice shall be included in
 *   all copies or substantial portions of the Software.
 *
 *   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *   IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *   FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *   AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *   LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *   THE SOFTWARE.
 */

package io.github.benas.randombeans.randomizers;

import io.github.benas.randombeans.api.Randomizer;

/**
 * A {@link Randomizer} which, according to the optional percent, returns the random value from a delegate.
 *
 * @param <T> the type generated by this randomizer
 * @author Eric Taix (eric.taix@gmail.com)
 */
public class OptionalRandomizer<T> extends AbstractRandomizer<T> {

    private static final int MAX_PERCENT = 100;
    private Randomizer<T> delegate;
    private int optionalPercent;

    /**
     * Create a new {@link OptionalRandomizer} with a delegate randomizer and an optional percent threshold.
     *
     * @param delegate        The delegate to use to retrieve a random value
     * @param optionalPercent The percent of randomized value to return (between 0 and 100)
     */
    public OptionalRandomizer(final Randomizer<T> delegate, final int optionalPercent) {
        this.delegate = delegate;
        if (optionalPercent > MAX_PERCENT) {
            this.optionalPercent = MAX_PERCENT;
        } else if (optionalPercent < 0) {
            this.optionalPercent = 0;
        } else {
            this.optionalPercent = optionalPercent;
        }
    }

    /**
     * Create a new {@link OptionalRandomizer}.
     *
     * @param delegate        the delegate randomizer to use
     * @param optionalPercent the optional percent threshold
     * @param <T>             the type generated by this randomizer
     * @return a new {@link OptionalRandomizer}
     */
    public static <T> Randomizer<T> aNewOptionalRandomizer(final Randomizer<T> delegate, final int optionalPercent) {
        return new OptionalRandomizer<>(delegate, optionalPercent);
    }

    @Override
    public T getRandomValue() {
        Integer randomPercent = random.nextInt(MAX_PERCENT);
        if (randomPercent <= optionalPercent) {
            return delegate.getRandomValue();
        }
        return null;
    }

}
