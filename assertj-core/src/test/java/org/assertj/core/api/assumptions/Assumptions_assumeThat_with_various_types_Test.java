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
package org.assertj.core.api.assumptions;

import static java.lang.Boolean.TRUE;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatObject;
import static org.assertj.core.api.Assumptions.assumeThat;
import static org.assertj.core.api.Assumptions.assumeThatComparable;
import static org.assertj.core.api.Assumptions.assumeThatObject;
import static org.assertj.core.api.Assumptions.assumeThatThrownBy;
import static org.assertj.core.util.Arrays.array;
import static org.assertj.core.util.AssertionsUtil.expectAssumptionNotMetException;
import static org.assertj.core.util.Lists.list;
import static org.assertj.core.util.Maps.newHashMap;
import static org.mockito.Mockito.mock;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Path;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Spliterator;
import java.util.concurrent.Future;
import java.util.stream.Stream;

import org.assertj.core.api.Assumptions;
import org.assertj.core.api.Condition;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.assertj.core.api.test.ComparableExample;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class Assumptions_assumeThat_with_various_types_Test {

  static Stream<AssumptionRunner<?>> provideAssumptionsRunners() {
    // @format:off
    return Stream.of(
        new AssumptionRunner<String>("test") {
          @Override
          public void runFailingAssumption() {
            assumeThat(actual).isNotBlank().isEqualTo("other");
          }

          @Override
          public void runPassingAssumption() {
            assumeThat(actual).isNotBlank().isEqualTo("test");
          }
        },
        new AssumptionRunner<CharSequence>("test") {
          @Override
          public void runFailingAssumption() {
            assumeThat(actual).isEqualTo("other");
          }

          @Override
          public void runPassingAssumption() {
            assumeThat(actual).isEqualTo("test");
          }
        },
        new AssumptionRunner<Boolean>() {
          @Override
          public void runFailingAssumption() {
            assumeThat(true).isFalse();
          }

          @Override
          public void runPassingAssumption() {
            assumeThat(true).isTrue();
          }
        },
        new AssumptionRunner<Boolean>(TRUE) {
          @Override
          public void runFailingAssumption() {
            assumeThat(actual).isFalse();
          }

          @Override
          public void runPassingAssumption() {
            assumeThat(actual).isTrue();
          }
        },
        new AssumptionRunner<boolean[]>(new boolean[] { true, false, true }) {
          @Override
          public void runFailingAssumption() {
            assumeThat(actual).containsOnlyOnce(true);
          }

          @Override
          public void runPassingAssumption() {
            assumeThat(actual).containsOnlyOnce(false);
          }
        },
        new AssumptionRunner<Character>() {
          @Override
          public void runFailingAssumption() {
            assumeThat('a').isEqualTo('b');
          }

          @Override
          public void runPassingAssumption() {
            assumeThat('a').isEqualTo('a');
          }
        },
        new AssumptionRunner<Character>('a') {
          @Override
          public void runFailingAssumption() {
            assumeThat(actual).isEqualTo('b');
          }

          @Override
          public void runPassingAssumption() {
            assumeThat(actual).isEqualTo('a');
          }
        },
        new AssumptionRunner<char[]>(new char[] { '2', '4', '2' }) {
          @Override
          public void runFailingAssumption() {
            assumeThat(actual).containsOnlyOnce('2');
          }

          @Override
          public void runPassingAssumption() {
            assumeThat(actual).containsOnlyOnce('4');
          }
        },
        new AssumptionRunner<Class<?>>(Comparable.class) {
          @Override
          public void runFailingAssumption() {
            assumeThat(actual).isNotInterface();
          }

          @Override
          public void runPassingAssumption() {
            assumeThat(actual).isInterface();
          }
        },
        new AssumptionRunner<ClassLoader>(Assumptions.class.getClassLoader()) {
          @Override
          public void runFailingAssumption() {
            // FIXME replace with class loader specific assertion
            assumeThat(actual).isNotSameAs(Assumptions.class.getClassLoader());
          }

          @Override
          public void runPassingAssumption() {
            // FIXME replace with class loader specific assertion
            assumeThat(actual).isSameAs(Assumptions.class.getClassLoader());
          }
        },
        new AssumptionRunner<Date>(new Date()) {
          @Override
          public void runFailingAssumption() {
            assumeThat(actual).as("isBefore(\"2011-01-01\")").isBefore("2011-01-01");
          }

          @Override
          public void runPassingAssumption() {
            assumeThat(actual).isAfter("2011-01-01");
          }
        },
        new AssumptionRunner<File>(new File("test")) {
          @Override
          public void runFailingAssumption() {
            assumeThat(actual).hasName("other");
          }

          @Override
          public void runPassingAssumption() {
            assumeThat(actual).hasName("test");
          }
        },
        new AssumptionRunner<Path>(new File("test").toPath()) {
          @Override
          public void runFailingAssumption() {
            assumeThat(actual).isNull();
          }

          @Override
          public void runPassingAssumption() {
            assumeThat(actual).isNotNull();
          }
        },
        new AssumptionRunner<InputStream>(new ByteArrayInputStream("test".getBytes())) {
          @Override
          public void runFailingAssumption() {
            assumeThat(actual).isInstanceOf(String.class);
          }

          @Override
          public void runPassingAssumption() {
            assumeThat(actual).isInstanceOf(ByteArrayInputStream.class);
          }
        },
        new AssumptionRunner<Integer[]>(array(2, 4, 2)) {
          @Override
          public void runFailingAssumption() {
            assumeThat(actual).containsOnlyOnce(2);
          }

          @Override
          public void runPassingAssumption() {
            assumeThat(actual).containsOnlyOnce(4);
          }
        },
        new AssumptionRunner<Throwable>(new IllegalArgumentException()) {
          @Override
          public void runFailingAssumption() {
            assumeThat(actual).isInstanceOf(NullPointerException.class);
          }

          @Override
          public void runPassingAssumption() {
            assumeThat(actual).isInstanceOf(IllegalArgumentException.class);
          }
        },
        new AssumptionRunner<ThrowingCallable>(new ThrowingCallable() {
          @Override
          public void call() {
            throw new IllegalArgumentException();
          }
        }) {
          @Override
          public void runFailingAssumption() {
            assumeThatThrownBy(actual).isInstanceOf(NullPointerException.class);
          }

          @Override
          public void runPassingAssumption() {
            assumeThatThrownBy(actual).isInstanceOf(IllegalArgumentException.class);
          }
        },
        new AssumptionRunner<URL>(createUrl()) {
          @Override
          public void runFailingAssumption() {
            assumeThat(actual).hasParameter("test");
          }

          @Override
          public void runPassingAssumption() {
            assumeThat(actual).hasNoParameters();
          }
        },
        new AssumptionRunner<URI>(URI.create("example.com/pages/")) {
          @Override
          public void runFailingAssumption() {
            assumeThat(actual).hasPort(9090);
          }

          @Override
          public void runPassingAssumption() {
            assumeThat(actual).hasNoPort();
          }
        },
        new AssumptionRunner<Future<?>>(mock(Future.class)) {
          @Override
          public void runFailingAssumption() {
            assumeThat(actual).isDone();
          }

          @Override
          public void runPassingAssumption() {
            assumeThat(actual).isNotDone();
          }
        },
        new AssumptionRunner<Iterable<Integer>>(asList(2, 4, 2)) {
          @Override
          public void runFailingAssumption() {
            assumeThat(actual).containsOnlyOnce(2);
          }

          @Override
          public void runPassingAssumption() {
            assumeThat(actual).containsOnlyOnce(4);
          }
        },
        new AssumptionRunner<Iterator<Integer>>(asList(2, 4, 2).iterator()) {
          @Override
          public void runFailingAssumption() {
            assumeThat(actual).isExhausted();
          }

          @Override
          public void runPassingAssumption() {
            assumeThat(actual).hasNext();
          }
        },
        new AssumptionRunner<List<Integer>>(asList(2, 4, 2)) {
          @Override
          public void runFailingAssumption() {
            assumeThat(actual).containsOnlyOnce(2);
          }

          @Override
          public void runPassingAssumption() {
            assumeThat(actual).containsOnlyOnce(4);
          }
        },
        new AssumptionRunner<List<Integer>>(asList(2, 4, 2)) {
          @Override
          public void runFailingAssumption() {
            assumeThat(actual).containsOnlyOnce(4).toAssert(2, "test 2 isNull").isNull();
          }

          @Override
          public void runPassingAssumption() {
            assumeThat(actual).containsOnlyOnce(4).toAssert(2, "").isEqualTo(2);
          }
        },
        new AssumptionRunner<Map<Integer, Integer>>(newHashMap(2, 4)) {
          @Override
          public void runFailingAssumption() {
            assumeThat(actual).containsKeys(4);
          }

          @Override
          public void runPassingAssumption() {
            assumeThat(actual).containsKeys(2);
          }
        },
        new AssumptionRunner<ComparableExample>(new ComparableExample(4)) {
          @Override
          public void runFailingAssumption() {
            assumeThat(actual).isLessThan(new ComparableExample(2));
          }

          @Override
          public void runPassingAssumption() {
            assumeThat(actual).isGreaterThan(new ComparableExample(2));
          }
        },
        new AssumptionRunner<Comparable<ComparableExample>>(new ComparableExample(4)) {
          @Override
          public void runFailingAssumption() {
            assumeThatComparable(actual).isLessThan(new ComparableExample(2));
          }
          
          @Override
          public void runPassingAssumption() {
            assumeThatComparable(actual).isGreaterThan(new ComparableExample(2));
          }
        },
        new AssumptionRunner<ComparableExample>(new ComparableExample(4)) {
          @Override
          public void runFailingAssumption() {
            assumeThatComparable(actual).isLessThan(new ComparableExample(2));
          }
          
          @Override
          public void runPassingAssumption() {
            assumeThatComparable(actual).isGreaterThan(new ComparableExample(2));
          }
        },
        new AssumptionRunner<List<String>>(asList("a", "b", "c")) {
          @Override
          public void runFailingAssumption() {
            assumeThat(actual).zipSatisfy(asList("A", "B", "C"), (e1, e2) -> assertThat(e1).isEqualTo(e2));
          }

          @Override
          public void runPassingAssumption() {
            assumeThat(actual).zipSatisfy(asList("A", "B", "C"), (e1, e2) -> assertThat(e1).isEqualToIgnoringCase(e2));
          }
        },
        new AssumptionRunner<String>("abc") {
          @Override
          public void runFailingAssumption() {
            assumeThat(actual).satisfiesAnyOf(s -> assertThat(s).isEmpty(), s -> assertThat(s).isBlank());
          }

          @Override
          public void runPassingAssumption() {
            assumeThat(actual).satisfiesAnyOf(s -> assertThat(s).isLowerCase(), s -> assertThat(s).isBlank());
          }
        },
        new AssumptionRunner<LinkedList<String>>(new LinkedList<>(list("abc"))) {
          @Override
          public void runFailingAssumption() {
            assumeThatObject(actual).satisfies(l -> assertThat(l).isEmpty());
          }

          @Override
          public void runPassingAssumption() {
            assumeThatObject(actual).satisfies(l -> assertThatObject(l).has(
              new Condition<>(list -> list.getFirst().equals("abc"), "First element is 'abc'")));
          }
        },
        new AssumptionRunner<Spliterator<Integer>>(Stream.of(1, 2).spliterator()) {
          @Override
          public void runFailingAssumption() {
            assumeThat(actual).hasCharacteristics(Spliterator.DISTINCT);
          }

          @Override
          public void runPassingAssumption() {
            assumeThat(actual).hasCharacteristics(Spliterator.SIZED);
          }
      });
    // @format:on
  }

  private static URL createUrl() {
    try {
      return new URL("http://example.com/pages/");
    } catch (MalformedURLException e) {
      throw new RuntimeException(e);
    }
  }

  @ParameterizedTest
  @MethodSource("provideAssumptionsRunners")
  void should_ignore_test_when_assumption_fails(AssumptionRunner<?> assumptionRunner) {
    expectAssumptionNotMetException(assumptionRunner::runFailingAssumption);
  }

  @ParameterizedTest
  @MethodSource("provideAssumptionsRunners")
  void should_run_test_when_assumption_passes(AssumptionRunner<?> assumptionRunner) {
    assertThatCode(assumptionRunner::runPassingAssumption).doesNotThrowAnyException();
  }
}
