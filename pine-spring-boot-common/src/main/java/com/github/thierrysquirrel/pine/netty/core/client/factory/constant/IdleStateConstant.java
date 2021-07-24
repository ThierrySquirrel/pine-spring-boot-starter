/**
 * Copyright 2019 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.thierrysquirrel.pine.netty.core.client.factory.constant;

/**
 * ClassName: IdleStateConstant
 * Description:
 * date: 2019/10/17 19:35
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public enum IdleStateConstant {
    /**
     * ReadTimeout
     */
    READ_TIMEOUT (1000),
    /**
     * WriteTimeout
     */
    WRITE_TIMEOUT (1000),
    /**
     * OtherTimeout
     */
    OTHER_TIMEOUT (0);
    private int value;

    IdleStateConstant(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
