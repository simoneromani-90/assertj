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
package org.assertj.core.error;

import static java.lang.String.format;
import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.core.error.ShouldHaveOnlyElementsOfType.shouldHaveOnlyElementsOfType;

import java.util.List;

import org.assertj.core.description.TextDescription;
import org.assertj.core.presentation.StandardRepresentation;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;

/**
 * Tests for <code>{@link ShouldHaveAtLeastOneElementOfType#shouldHaveAtLeastOneElementOfType(Object, Class)}</code>.
 */
class ShouldHaveOnlyElementsOfType_create_Test {

  @Test
  void should_create_error_message_for_iterable() {
    // GIVEN
    List<Object> list = Lists.list("Yoda", 5L);
    ErrorMessageFactory factory = shouldHaveOnlyElementsOfType(list, String.class, Long.class);
    // WHEN
    String message = factory.create(new TextDescription("Test"), new StandardRepresentation());
    // THEN
    then(message).isEqualTo(format("[Test] %n"
                                   + "Expecting actual:%n"
                                   + "  [\"Yoda\", 5L]%n"
                                   + "to only have elements of type:%n"
                                   + "  java.lang.String%n"
                                   + "but found:%n"
                                   + "  java.lang.Long"));
  }

  @Test
  void should_create_error_message_for_array() {
    // GIVEN
    Object[] array = new Object[] { "Yoda", 5L };
    ErrorMessageFactory factory = shouldHaveOnlyElementsOfType(array, String.class, Long.class);
    // WHEN
    String message = factory.create(new TextDescription("Test"), new StandardRepresentation());
    // THEN
    then(message).isEqualTo(format("[Test] %n"
                                   + "Expecting actual:%n"
                                   + "  [\"Yoda\", 5L]%n"
                                   + "to only have elements of type:%n"
                                   + "  java.lang.String%n"
                                   + "but found:%n"
                                   + "  java.lang.Long"));
  }

}
