/* Copyright 1995-2014 Esri
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * For additional information, contact:
 * Environmental Systems Research Institute, Inc.
 * Attn: Contracts Dept
 * 380 New York Street
 * Redlands, California, USA 92373
 *
 * email: contracts@esri.com
 *
 */

package zero.ucamaps.util;

/**
 * Helper class for string operations.
 */
public class StringUtils {

  /**
   * Returns whether or not the string is not empty.
   * 
   * @param str The String to check for emptiness
   * @return whether or not the string is not empty
   */
  public static boolean isNotEmpty(String str) {
    return !isEmpty(str);
  }

  /**
   * Returns whether or not the string is empty. Note: returns true if the string has a value of "null".
   * 
   * @param str the String to check for emptiness
   * @return whether or not the string is empty
   */
  public static boolean isEmpty(String str) {
    return str == null || str.isEmpty();
  }
}
