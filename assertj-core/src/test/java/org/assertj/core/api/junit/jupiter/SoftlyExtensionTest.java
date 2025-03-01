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
package org.assertj.core.api.junit.jupiter;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@SuppressWarnings("deprecation")
@ExtendWith(SoftlyExtension.class)
@DisplayName("SoftlyExtension")
class SoftlyExtensionTest {

  private SoftAssertions softly;

  @Test
  void should_pass_if_not_null() {
    assertThat(softly).isNotNull();
  }

  @Nested
  @ExtendWith(SoftlyExtension.class)
  @DisplayName("nested test class without SoftAssertions field")
  class NestedMethodLifecycle {

    @Test
    void should_use_parent_SoftAssertions_initialized_field() {
      assertThat(softly).isNotNull();
    }
  }

  @Nested
  @ExtendWith(SoftlyExtension.class)
  @DisplayName("nested test class with SoftAssertions field")
  class SoftlyNestedMethodLifecycle {

    private SoftAssertions nestedSoftly;

    @Test
    void should_use_own_SoftAssertions_initialized_field() {
      assertThat(nestedSoftly).isNotNull();
    }

  }
}
