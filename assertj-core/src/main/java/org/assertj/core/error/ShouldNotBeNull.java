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

/**
 * Creates an error message that indicates an assertion that verifies that an object is not {@code null} failed.
 *
 * @author Alex Ruiz
 */
public class ShouldNotBeNull extends BasicErrorMessageFactory {

  private static final ShouldNotBeNull INSTANCE = new ShouldNotBeNull("%nExpecting actual not to be null");

  /**
   * Returns the default instance of this class.
   * @return the default instance of this class.
   */
  public static ErrorMessageFactory shouldNotBeNull() {
    return INSTANCE;
  }

  /**
   * Create a instance specifying a label
   * @param label of what should not be null
   * @return the new instance
   */
  public static ShouldNotBeNull shouldNotBeNull(String label) {
    return new ShouldNotBeNull("%nExpecting %s not to be null".formatted(label));
  }

  private ShouldNotBeNull(String label) {
    super(label);
  }

}
