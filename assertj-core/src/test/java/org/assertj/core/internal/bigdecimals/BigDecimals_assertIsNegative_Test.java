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
package org.assertj.core.internal.bigdecimals;

import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.core.error.ShouldBeLess.shouldBeLess;
import static org.assertj.core.testkit.TestData.someInfo;
import static org.assertj.core.util.AssertionsUtil.expectAssertionError;

import java.math.BigDecimal;

import org.assertj.core.api.AssertionInfo;
import org.assertj.core.internal.BigDecimals;
import org.assertj.core.internal.BigDecimalsBaseTest;
import org.junit.jupiter.api.Test;

/**
 * Tests for <code>{@link BigDecimals#assertIsNegative(AssertionInfo, BigDecimal)}</code>.
 *
 * @author Yvonne Wang
 * @author Joel Costigliola
 */
class BigDecimals_assertIsNegative_Test extends BigDecimalsBaseTest {

  @Test
  void should_succeed_since_actual_is_negative() {
    numbers.assertIsNegative(someInfo(), new BigDecimal("-1.0"));
  }

  @Test
  void should_succeed_since_actual_is_negative_according_to_custom_comparison_strategy() {
    numbersWithComparatorComparisonStrategy.assertIsNegative(someInfo(), new BigDecimal("-1.0"));
  }

  @Test
  void should_fail_since_actual_is_zero() {
    // WHEN
    AssertionError assertionError = expectAssertionError(() -> numbers.assertIsNegative(someInfo(), BigDecimal.ZERO));
    // THEN
    then(assertionError).hasMessage(shouldBeLess(BigDecimal.ZERO, BigDecimal.ZERO).create());
  }

  @Test
  void should_fail_since_actual_is_not_negative() {
    // WHEN
    AssertionError assertionError = expectAssertionError(() -> numbers.assertIsNegative(someInfo(), BigDecimal.ONE));
    // THEN
    then(assertionError).hasMessage(shouldBeLess(BigDecimal.ONE, BigDecimal.ZERO).create());
  }

  @Test
  void should_fail_since_actual_is_not_negative_according_to_custom_comparison_strategy() {
    // WHEN
    AssertionError error = expectAssertionError(() -> numbersWithAbsValueComparisonStrategy.assertIsNegative(someInfo(),
                                                                                                             new BigDecimal(-1)));
    // THEN
    then(error).hasMessage(shouldBeLess(new BigDecimal(-1), BigDecimal.ZERO, absValueComparisonStrategy).create());
  }

}
