/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2012-2025 the original author or authors.
 */
package org.assertj.core.api.iterable;

import java.util.function.Function;

import org.assertj.core.api.AbstractIterableAssert;
import org.assertj.core.api.AbstractObjectArrayAssert;
import org.assertj.core.api.AtomicReferenceArrayAssert;

/**
 * Function converting an element to another element. Used in {@link AbstractIterableAssert#extracting(Function)},
 * {@link AbstractObjectArrayAssert#extracting(Function)} and {@link AtomicReferenceArrayAssert#extracting(Function)}.
 * 
 * @author Mateusz Haligowski
 *
 * @param <F> type of element from which the conversion happens
 * @param <T> target element type
 * @deprecated use {@link Function} instead
 */
@Deprecated(since = "3", forRemoval = true)
@FunctionalInterface
public interface Extractor<F, T> extends Function<F, T> {

  @Override
  default T apply(F f) {
    return extract(f);
  }

  T extract(F input);
}
