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
import static org.assertj.core.error.ShouldContainsOnlyOnce.shouldContainsOnlyOnce;
import static org.assertj.core.presentation.StandardRepresentation.STANDARD_REPRESENTATION;
import static org.assertj.core.util.Lists.list;
import static org.assertj.core.util.Sets.newLinkedHashSet;

import org.assertj.core.description.TextDescription;
import org.assertj.core.api.comparisonstrategy.ComparatorBasedComparisonStrategy;
import org.assertj.core.testkit.CaseInsensitiveStringComparator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for <code>{@link ShouldContainsOnlyOnce#create(org.assertj.core.description.Description, org.assertj.core.presentation.Representation)}</code>.
 *
 * @author William Delanoue
 */
class ShouldContainsOnlyOnce_create_Test {

  private ErrorMessageFactory factory;

  @BeforeEach
  public void setUp() {
    factory = shouldContainsOnlyOnce(list("Yoda", "Han", "Han"), list("Luke", "Yoda"),
                                     newLinkedHashSet("Luke"), newLinkedHashSet("Han"));
  }

  @Test
  void should_create_error_message() {
    // WHEN
    String message = factory.create(new TextDescription("Test"), STANDARD_REPRESENTATION);
    // THEN
    then(message).isEqualTo(format("[Test] %nExpecting actual:%n" +
                                   "  [\"Yoda\", \"Han\", \"Han\"]%n" +
                                   "to contain only once:%n" +
                                   "  [\"Luke\", \"Yoda\"]%n" +
                                   "but some elements were not found:%n" +
                                   "  [\"Luke\"]%n" +
                                   "and others were found more than once:%n" +
                                   "  [\"Han\"]%n"));
  }

  @Test
  void should_create_error_message_with_custom_comparison_strategy() {
    // GIVEN
    factory = shouldContainsOnlyOnce(list("Yoda", "Han"), list("Luke", "Yoda"),
                                     newLinkedHashSet("Luke"), newLinkedHashSet("Han"),
                                     new ComparatorBasedComparisonStrategy(CaseInsensitiveStringComparator.INSTANCE));
    // WHEN
    String message = factory.create(new TextDescription("Test"), STANDARD_REPRESENTATION);
    // THEN
    then(message).isEqualTo(format("[Test] %n" +
                                   "Expecting actual:%n" +
                                   "  [\"Yoda\", \"Han\"]%n" +
                                   "to contain only once:%n" +
                                   "  [\"Luke\", \"Yoda\"]%n" +
                                   "but some elements were not found:%n" +
                                   "  [\"Luke\"]%n" +
                                   "and others were found more than once:%n" +
                                   "  [\"Han\"]%n" +
                                   "when comparing values using CaseInsensitiveStringComparator"));
  }

  @Test
  void should_create_error_message_without_not_found_elements() {
    // GIVEN
    factory = shouldContainsOnlyOnce(list("Yoda", "Han", "Han"), list("Yoda"), newLinkedHashSet(), newLinkedHashSet("Han"));
    // WHEN
    String message = factory.create(new TextDescription("Test"), STANDARD_REPRESENTATION);
    // THEN
    then(message).isEqualTo(format("[Test] %nExpecting actual:%n" +
                                   "  [\"Yoda\", \"Han\", \"Han\"]%n" +
                                   "to contain only once:%n" +
                                   "  [\"Yoda\"]%n" +
                                   "but some elements were found more than once:%n" +
                                   "  [\"Han\"]%n"));
  }

  @Test
  void should_create_error_message_without_elements_found_many_times() {
    // GIVEN
    factory = shouldContainsOnlyOnce(list("Yoda", "Han"), list("Luke"), newLinkedHashSet("Luke"), newLinkedHashSet());
    // WHEN
    String message = factory.create(new TextDescription("Test"), STANDARD_REPRESENTATION);
    // THEN
    then(message).isEqualTo(format("[Test] %nExpecting actual:%n" +
                                   "  [\"Yoda\", \"Han\"]%n" +
                                   "to contain only once:%n" +
                                   "  [\"Luke\"]%n" +
                                   "but some elements were not found:%n" +
                                   "  [\"Luke\"]%n"));
  }

}
