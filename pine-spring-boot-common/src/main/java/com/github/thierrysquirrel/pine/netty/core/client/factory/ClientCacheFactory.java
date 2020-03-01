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

package com.github.thierrysquirrel.pine.netty.core.client.factory;

import com.github.thierrysquirrel.pine.netty.core.client.ClientInit;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ClassName: ClientCacheFactory
 * Description:
 * date: 2019/10/17 19:40
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class ClientCacheFactory {
    private ClientCacheFactory() {
    }

    private static Map<String, ClientInit> clientCache = new ConcurrentHashMap<> ();

    public static ClientInit getClientInit(String url) {
        ClientInit clientInit = clientCache.get (url);
        if (clientInit == null) {
            clientInit = new ClientInit ();
            clientCache.put (url, clientInit);
        } else {
            return clientInit;
        }
        return getClientInit (url);
    }
}
