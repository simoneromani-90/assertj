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
package org.assertj.core.presentation;

import static org.assertj.core.util.Strings.concat;

/**
 * Hexadecimal object representation instead of standard java representation.
 * 
 * @author Mariusz Smykula
 */
public class HexadecimalRepresentation extends StandardRepresentation {

  public static final HexadecimalRepresentation HEXA_REPRESENTATION = new HexadecimalRepresentation();

  public static final String PREFIX = "0x";
  public static final int NIBBLE_SIZE = 4;

  /**
   * Returns hexadecimal the {@code toString} representation of the given object. It may or not the object's own
   * implementation of {@code toString}.
   * 
   * @param object the given object.
   * @return the {@code toString} representation of the given object.
   */
  @Override
  public String toStringOf(Object object) {
    if (hasCustomFormatterFor(object)) return customFormat(object);
    if (object instanceof Number number) return toStringOf(number);
    else if (object instanceof String string) return toStringOf(this, string);
    else if (object instanceof Character character) return toStringOf(character);
    return super.toStringOf(object);
  }

  @Override
  protected String toStringOf(Number number) {
    if (number instanceof Byte b) return toStringOf(b);
    else if (number instanceof Short s) return toStringOf(s);
    else if (number instanceof Integer i) return toStringOf(i);
    else if (number instanceof Long l) return toStringOf(l);
    else if (number instanceof Float f) return toStringOf(f);
    else if (number instanceof Double d) return toStringOf(d);
    else return number.toString();
  }

  protected String toStringOf(Byte b) {
    return toGroupedHex(b, 8);
  }

  protected String toStringOf(Short s) {
    return toGroupedHex(s, 16);
  }

  protected String toStringOf(Integer i) {
    return toGroupedHex(i, 32);
  }

  @Override
  protected String toStringOf(Long l) {
    return toGroupedHex(l, 64);
  }

  @Override
  protected String toStringOf(Float f) {
    return toGroupedHex(Float.floatToIntBits(f), 32);
  }

  protected String toStringOf(Double d) {
    return toGroupedHex(Double.doubleToRawLongBits(d), 64);
  }

  @Override
  protected String toStringOf(Character character) {
    return concat("'", toStringOf((short) (int) character), "'");
  }

  protected String toStringOf(Representation representation, String s) {
    return concat("\"", representation.toStringOf(s.toCharArray()), "\"");
  }

  private static String toGroupedHex(Number value, int size) {
    return PREFIX + NumberGrouping.toHexLiteral(toHex(value, size));
  }

  private static String toHex(Number value, int sizeInBits) {
    return String.format("%0" + sizeInBits / NIBBLE_SIZE + "X", value);
  }

}
