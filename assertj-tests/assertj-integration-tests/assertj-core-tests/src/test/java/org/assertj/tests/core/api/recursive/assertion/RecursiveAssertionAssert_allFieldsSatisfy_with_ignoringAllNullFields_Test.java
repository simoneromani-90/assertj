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
package org.assertj.tests.core.api.recursive.assertion;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.tests.core.util.AssertionsUtil.expectAssertionError;

import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

class RecursiveAssertionAssert_allFieldsSatisfy_with_ignoringAllNullFields_Test {

  @Test
  void should_pass_when_asserting_only_string() {
    // GIVEN
    Person sherlock = new Person("Sherlock", "Detective");
    sherlock.address = null;
    Predicate<Object> isString = field -> field instanceof String;
    // WHEN/THEN
    then(sherlock).usingRecursiveAssertion()
                  .ignoringAllNullFields()
                  .allFieldsSatisfy(isString);
  }

  @Test
  void should_fail_when_asserting_only_string() {
    // GIVEN
    Person sherlock = new Person("Sherlock", "Detective");
    sherlock.address = new Address(221, null);
    Predicate<Object> isString = field -> field instanceof String;
    // WHEN
    AssertionError error = expectAssertionError(() -> assertThat(sherlock).usingRecursiveAssertion()
                                                                          .allFieldsSatisfy(isString));
    // THEN
    then(error).hasMessageContaining("[address, address.number, address.street]");
  }

  static class Person {
    String name;
    String occupation;
    Address address;

    public Person(String name, String occupation) {
      this.name = name;
      this.occupation = occupation;
    }
  }

  static class Address {
    int number;
    String street;

    public Address(int number, String street) {
      this.number = number;
      this.street = street;
    }
  }

}
