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

public class ShouldBeEqualIgnoringNewLines extends BasicErrorMessageFactory {

  public static ErrorMessageFactory shouldBeEqualIgnoringNewLines(CharSequence actual, CharSequence expected) {
    return new ShouldBeEqualIgnoringNewLines(actual, expected);
  }

  private ShouldBeEqualIgnoringNewLines(CharSequence actual, CharSequence expected) {
    super("%n" +
          "Expecting actual:%n" +
          "  %s%n" +
          "to be equal to:%n" +
          "  %s%n" +
          "when ignoring newlines (\\n, \\r\\n).",
          actual, expected);
  }

}
