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
package org.assertj.tests.core.internal.strings;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.error.ShouldStartWith.shouldStartWith;
import static org.assertj.tests.core.testkit.TestData.someInfo;
import static org.assertj.core.util.FailureMessages.actualIsNull;
import static org.mockito.Mockito.verify;

import org.assertj.core.api.AssertionInfo;
import org.assertj.core.internal.Strings;
import org.assertj.tests.core.internal.StringsBaseTest;
import org.junit.jupiter.api.Test;

/**
 * Tests for <code>{@link Strings#assertStartsWith(AssertionInfo, CharSequence, CharSequence)}</code>.
 * 
 * @author Alex Ruiz
 * @author Joel Costigliola
 */
class Strings_assertStartsWith_Test extends StringsBaseTest {

  @Test
  void should_fail_if_actual_does_not_start_with_prefix() {
    AssertionInfo info = someInfo();

    Throwable error = catchThrowable(() -> strings.assertStartsWith(info, "Yoda", "Luke"));

    assertThat(error).isInstanceOf(AssertionError.class);
    verify(failures).failure(info, shouldStartWith("Yoda", "Luke"));
  }

  @Test
  void should_throw_error_if_prefix_is_null() {
    assertThatNullPointerException().isThrownBy(() -> strings.assertStartsWith(someInfo(), "Yoda", null))
                                    .withMessage("The given prefix should not be null");
  }

  @Test
  void should_fail_if_actual_is_null() {
    assertThatExceptionOfType(AssertionError.class).isThrownBy(() -> strings.assertStartsWith(someInfo(), null, "Yoda"))
                                                   .withMessage(actualIsNull());
  }

  @Test
  void should_pass_if_actual_starts_with_prefix() {
    strings.assertStartsWith(someInfo(), "Yoda", "Yo");
  }

  @Test
  void should_pass_if_actual_starts_with_prefix_according_to_custom_comparison_strategy() {
    stringsWithCaseInsensitiveComparisonStrategy.assertStartsWith(someInfo(), "Yoda", "Y");
    stringsWithCaseInsensitiveComparisonStrategy.assertStartsWith(someInfo(), "Yoda", "Yo");
    stringsWithCaseInsensitiveComparisonStrategy.assertStartsWith(someInfo(), "Yoda", "Yod");
    stringsWithCaseInsensitiveComparisonStrategy.assertStartsWith(someInfo(), "Yoda", "Yoda");
    stringsWithCaseInsensitiveComparisonStrategy.assertStartsWith(someInfo(), "Yoda", "yoda");
    stringsWithCaseInsensitiveComparisonStrategy.assertStartsWith(someInfo(), "Yoda", "YODA");
  }

  @Test
  void should_fail_if_actual_does_not_start_with_prefix_according_to_custom_comparison_strategy() {
    AssertionInfo info = someInfo();

    Throwable error = catchThrowable(() -> stringsWithCaseInsensitiveComparisonStrategy.assertStartsWith(info, "Yoda", "Luke"));

    assertThat(error).isInstanceOf(AssertionError.class);
    verify(failures).failure(info, shouldStartWith("Yoda", "Luke", comparisonStrategy));
  }

}
