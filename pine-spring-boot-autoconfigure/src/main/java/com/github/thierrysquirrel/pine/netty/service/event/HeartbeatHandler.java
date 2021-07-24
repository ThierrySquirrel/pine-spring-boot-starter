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

package com.github.thierrysquirrel.pine.netty.service.event;

import com.github.thierrysquirrel.pine.annotation.PineServiceEvent;
import com.github.thierrysquirrel.pine.annotation.PineServiceHandler;
import com.github.thierrysquirrel.pine.autoconfigure.PineServiceProperties;
import com.github.thierrysquirrel.pine.netty.core.client.factory.PineRequestContextFactory;
import com.github.thierrysquirrel.pine.netty.domain.PineRequestContext;
import com.github.thierrysquirrel.pine.netty.domain.constant.Command;
import com.github.thierrysquirrel.pine.netty.domain.constant.Modular;
import com.github.thierrysquirrel.pine.netty.service.core.factory.HeartbeatFactory;
import com.github.thierrysquirrel.pine.netty.service.core.factory.execution.SynchronousProducersFactoryExecution;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * ClassName: HeartbeatHandler
 * Description:
 * date: 2019/10/18 14:49
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
@PineServiceHandler
public class HeartbeatHandler {
    @Resource
    private PineServiceProperties pineServiceProperties;

    @PineServiceEvent(modular = Modular.HEARTBEAT, command = Command.PING)
    public void ping(ChannelHandlerContext ctx, PineRequestContext msg, String clientServiceName, String clientServiceUrl) {
        HeartbeatFactory.getClientServicePing (clientServiceName, clientServiceUrl, pineServiceProperties.getMaxNumberHeartbeatTimeouts ());
        PineRequestContext pangPineRequestContext = PineRequestContextFactory.createPangPineRequestContext ();
        ctx.channel ().writeAndFlush (pangPineRequestContext);
        String clusterServiceUrl = pineServiceProperties.getClusterServiceUrl ();

        PineRequestContext synchronousProducers = PineRequestContextFactory.createSynchronousProducers (clientServiceName, clientServiceUrl);

        if (!StringUtils.isEmpty (clusterServiceUrl)) {
            SynchronousProducersFactoryExecution.execution (clusterServiceUrl, pineServiceProperties.getServiceUrl (), synchronousProducers);
        }
    }


}
