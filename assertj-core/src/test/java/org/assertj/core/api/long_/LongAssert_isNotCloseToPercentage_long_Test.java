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
package org.assertj.core.api.long_;

import static org.assertj.core.data.Percentage.withPercentage;
import static org.mockito.Mockito.verify;

import org.assertj.core.api.LongAssert;
import org.assertj.core.api.LongAssertBaseTest;
import org.assertj.core.data.Percentage;

/**
 * Tests for <code>{@link LongAssert#isNotCloseTo(Long, Percentage)}</code>.
 *
 * @author Chris Arnott
 */
class LongAssert_isNotCloseToPercentage_long_Test extends LongAssertBaseTest {

  private final Percentage percentage = withPercentage(5L);
  private final Long value = 10L;

  @Override
  protected LongAssert invoke_api_method() {
    return assertions.isNotCloseTo(value, percentage);
  }

  @Override
  protected void verify_internal_effects() {
    verify(longs).assertIsNotCloseToPercentage(getInfo(assertions), getActual(assertions), value, percentage);
  }
}
