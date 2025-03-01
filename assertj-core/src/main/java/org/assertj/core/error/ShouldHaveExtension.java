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

import java.io.File;
import java.nio.file.Path;
import java.util.Objects;

/**
 * Creates an error message indicating that a {@code File} or a {@code Path} should have extension.
 * 
 * @author Jean-Christophe Gay
 */
public class ShouldHaveExtension extends BasicErrorMessageFactory {

  public static ShouldHaveExtension shouldHaveExtension(File actual, String actualExtension, String expectedExtension) {
    return actualExtension == null
        ? new ShouldHaveExtension(actual, expectedExtension)
        : new ShouldHaveExtension(actual, actualExtension, expectedExtension);
  }

  public static ShouldHaveExtension shouldHaveExtension(Path actual, String actualExtension, String expectedExtension) {
    Objects.requireNonNull(actualExtension);
    return new ShouldHaveExtension(actual, actualExtension, expectedExtension);
  }

  private ShouldHaveExtension(Object actual, String actualExtension, String expectedExtension) {
    super("%nExpecting%n  %s%nto have extension:%n  %s%nbut had:%n  %s.", actual, expectedExtension,
          actualExtension);
  }

  public static ShouldHaveExtension shouldHaveExtension(Path actual, String expectedExtension) {
    return new ShouldHaveExtension(actual, expectedExtension);
  }

  public static ShouldHaveExtension shouldHaveExtension(File actual, String expectedExtension) {
    return new ShouldHaveExtension(actual, expectedExtension);
  }

  private ShouldHaveExtension(Object actual, String expectedExtension) {
    super("%nExpecting%n  %s%nto have extension:%n  %s%nbut had no extension.", actual, expectedExtension);
  }

}
