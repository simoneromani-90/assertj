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
package org.assertj.guava.error;

import org.assertj.core.error.BasicErrorMessageFactory;
import org.assertj.core.error.ErrorMessageFactory;

/**
 * Creates an error message indicating that an assertion that verifies that a value have certain size failed.
 * 
 * @author Joel Costigliola
 */
public class ShouldHaveSize extends BasicErrorMessageFactory {

  /**
   * Creates a new <code>{@link org.assertj.guava.error.ShouldHaveSize}</code>.
   * @param actual the actual value in the failed assertion.
   * @param actualSize the size of {@code actual}.
   * @param expectedSize the expected size.
   * @return the created {@code ErrorMessageFactory}.
   */
  public static ErrorMessageFactory shouldHaveSize(Object actual, long actualSize, long expectedSize) {
    return new ShouldHaveSize(actual, actualSize, expectedSize);
  }

  private ShouldHaveSize(Object actual, long actualSize, long expectedSize) {
    // format the sizes in a standard way, otherwise if we use (for ex) an Hexadecimal representation
    // it will format sizes in hexadecimal while we only want actual to be formatted in hexadecimal
    super("%nExpected size: %s but was: %s in:%n%s".formatted(expectedSize, actualSize, "%s"), actual);
  }
}
