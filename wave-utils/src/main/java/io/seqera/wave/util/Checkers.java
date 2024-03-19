/*
 * Copyright 2023, Seqera Labs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package io.seqera.wave.util;

import java.util.List;
/**
 *
 * @author Munish Chouhan <munish.chouhan@seqera.io>
 */
public class Checkers {
    static public boolean isEmpty(String value) {
        return value==null || "".equals(value.trim());
    }

    static public boolean isEmpty(List list) {
        return list==null || list.size()==0;
    }
}
