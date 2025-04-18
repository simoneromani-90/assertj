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
package org.assertj.tests.core.api;

import static org.assertj.core.api.Assertions.catchThrowableOfType;
import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.tests.core.testkit.ThrowingCallableFactory.codeThrowing;
import static org.assertj.tests.core.util.AssertionsUtil.expectAssertionError;
import static org.mockito.Mockito.mock;

import java.io.IOException;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.Test;

class Assertions_catchThrowableOfType_Test {

  @Test
  void can_capture_exception_and_then_assert_following_AAA_or_BDD_style() {
    // GIVEN
    Exception exception = new Exception("boom!!");
    // WHEN
    Throwable boom = catchThrowableOfType(Exception.class, codeThrowing(exception));
    // THEN
    then(boom).isSameAs(exception);
  }

  @Test
  void catchThrowable_returns_null_when_no_exception_thrown() {
    // WHEN
    Throwable boom = catchThrowableOfType(RuntimeException.class, () -> {});
    // THEN
    then(boom).isNull();
  }

  @Test
  void catchThrowableOfType_should_fail_with_good_message_if_wrong_type() {
    // GIVEN
    ThrowingCallable code = () -> catchThrowableOfType(RuntimeException.class, raisingException("boom!!"));
    // WHEN
    AssertionError error = expectAssertionError(code);
    // THEN
    then(error).hasMessageContainingAll(RuntimeException.class.getName(), Exception.class.getName());
  }

  @Test
  void catchThrowableOfType_should_succeed_and_return_actual_instance_with_correct_class() {
    // GIVEN
    final Exception expected = new RuntimeException("boom!!");
    // WHEN
    Exception actual = catchThrowableOfType(Exception.class, codeThrowing(expected));
    // THEN
    then(actual).isSameAs(expected);
  }

  @Test
  void catchThrowableOfType_should_succeed_and_return_null_if_no_exception_thrown() {
    // WHEN
    IOException actual = catchThrowableOfType(IOException.class, () -> {});
    // THEN
    then(actual).isNull();
  }

  @Test
  void should_catch_mocked_throwable() {
    // GIVEN
    Throwable throwable = mock();
    // WHEN
    Throwable actual = catchThrowableOfType(Throwable.class, codeThrowing(throwable));
    // THEN
    then(actual).isSameAs(throwable);
  }

  static ThrowingCallable raisingException(final String reason) {
    return codeThrowing(new Exception(reason));
  }

}
