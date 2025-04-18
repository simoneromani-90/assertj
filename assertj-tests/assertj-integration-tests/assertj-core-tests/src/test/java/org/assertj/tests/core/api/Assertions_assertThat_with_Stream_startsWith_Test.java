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
package org.assertj.tests.core.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.assertj.core.internal.ErrorMessages.valuesToLookForIsNull;
import static org.assertj.core.util.Arrays.array;
import static org.assertj.core.util.FailureMessages.actualIsNull;
import static org.assertj.tests.core.testkit.ObjectArrays.emptyArray;

import java.util.stream.Stream;

import org.assertj.tests.core.testkit.CaseInsensitiveStringComparator;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class Assertions_assertThat_with_Stream_startsWith_Test {

  Stream<String> infiniteStream = Stream.generate(() -> "");

  // TODO it is not possible for startsWith to support both infinite streams and assertion chaining
  // assertion chaining has been chosen over infinite streams support
  @Test
  @Disabled
  void startsWith_should_work_with_infinite_streams() {
    assertThat(infiniteStream).startsWith("", "");
  }

  @Test
  void should_reuse_stream_after_assertion() {
    Stream<String> names = Stream.of("Luke", "Leia");
    assertThat(names).startsWith(array("Luke", "Leia"))
                     .endsWith("Leia");
  }

  @Test
  void should_throw_error_if_sequence_is_null() {
    assertThatNullPointerException().isThrownBy(() -> assertThat(infiniteStream).startsWith((String[]) null))
                                    .withMessage(valuesToLookForIsNull());
  }

  @Test
  void should_pass_if_actual_and_sequence_are_empty() {
    Stream<Object> empty = Stream.of();
    assertThat(empty).startsWith(emptyArray());
  }

  @Test
  void should_fail_if_sequence_to_look_for_is_empty_and_actual_is_not() {
    assertThatExceptionOfType(AssertionError.class).isThrownBy(() -> {
      Stream<String> names = Stream.of("Luke", "Leia");
      assertThat(names).startsWith();
    });
  }

  @Test
  void should_fail_if_actual_is_null() {
    assertThatExceptionOfType(AssertionError.class).isThrownBy(() -> {
      Stream<Object> names = null;
      assertThat(names).startsWith(emptyArray());
    }).withMessage(actualIsNull());
  }

  @Test
  void should_fail_if_sequence_is_bigger_than_actual() {
    assertThatExceptionOfType(AssertionError.class).isThrownBy(() -> {
      String[] sequence = { "Luke", "Leia", "Obi-Wan", "Han", "C-3PO", "R2-D2", "Anakin" };
      Stream<String> names = Stream.of("Luke", "Leia");
      assertThat(names).startsWith(sequence);
    });
  }

  @Test
  void should_fail_if_actual_does_not_start_with_sequence() {
    assertThatExceptionOfType(AssertionError.class).isThrownBy(() -> {
      String[] sequence = { "Han", "C-3PO" };
      Stream<String> names = Stream.of("Luke", "Leia");
      assertThat(names).startsWith(sequence);
    });
  }

  @Test
  void should_fail_if_actual_starts_with_first_elements_of_sequence_only() {
    assertThatExceptionOfType(AssertionError.class).isThrownBy(() -> {
      String[] sequence = { "Luke", "Yoda" };
      Stream<String> names = Stream.of("Luke", "Leia");
      assertThat(names).startsWith(sequence);
    });
  }

  @Test
  void should_pass_if_actual_starts_with_sequence() {
    Stream<String> names = Stream.of("Luke", "Leia", "Yoda");
    assertThat(names).startsWith(array("Luke", "Leia"));
  }

  @Test
  void should_pass_if_actual_and_sequence_are_equal() {
    Stream<String> names = Stream.of("Luke", "Leia");
    assertThat(names).startsWith(array("Luke", "Leia"));
  }

  // ------------------------------------------------------------------------------------------------------------------
  // tests using a custom comparison strategy
  // ------------------------------------------------------------------------------------------------------------------

  @Test
  void should_fail_if_actual_does_not_start_with_sequence_according_to_custom_comparison_strategy() {
    assertThatExceptionOfType(AssertionError.class).isThrownBy(() -> {
      Stream<String> names = Stream.of("Luke", "Leia");
      String[] sequence = { "Han", "C-3PO" };
      assertThat(names).usingElementComparator(CaseInsensitiveStringComparator.INSTANCE).startsWith(sequence);
    });
  }

  @Test
  void should_fail_if_actual_starts_with_first_elements_of_sequence_only_according_to_custom_comparison_strategy() {
    assertThatExceptionOfType(AssertionError.class).isThrownBy(() -> {
      Stream<String> names = Stream.of("Luke", "Leia");
      String[] sequence = { "Luke", "Obi-Wan", "Han" };
      assertThat(names).usingElementComparator(CaseInsensitiveStringComparator.INSTANCE).startsWith(sequence);
    });
  }

  @Test
  void should_pass_if_actual_starts_with_sequence_according_to_custom_comparison_strategy() {
    Stream<String> names = Stream.of("Luke", "Leia");
    String[] sequence = { "LUKE" };
    assertThat(names).usingElementComparator(CaseInsensitiveStringComparator.INSTANCE).startsWith(sequence);
  }

  @Test
  void should_pass_if_actual_and_sequence_are_equal_according_to_custom_comparison_strategy() {
    Stream<String> names = Stream.of("Luke", "Leia");
    String[] sequence = { "LUKE", "lEIA" };
    assertThat(names).usingElementComparator(CaseInsensitiveStringComparator.INSTANCE).startsWith(sequence);
  }

  public static class Foo {
    private String id;
    private int bar;
    public String _f2;

    public String getId() {
      return id;
    }

    public int getBar() {
      return bar;
    }

    public Foo(String id, int bar) {
      super();
      this.id = id;
      this.bar = bar;
    }

    @Override
    public String toString() {
      return "Foo [id=" + id + ", bar=" + bar + "]";
    }
  }

}
