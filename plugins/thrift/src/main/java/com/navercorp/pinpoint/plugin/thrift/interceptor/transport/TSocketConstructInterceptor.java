/*
 * Copyright 2015 NAVER Corp.
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
 */

package com.navercorp.pinpoint.plugin.thrift.interceptor.transport;

import java.net.Socket;

import org.apache.thrift.transport.TSocket;

import com.navercorp.pinpoint.bootstrap.MetadataAccessor;
import com.navercorp.pinpoint.bootstrap.interceptor.SimpleAroundInterceptor;
import com.navercorp.pinpoint.bootstrap.logging.PLogger;
import com.navercorp.pinpoint.bootstrap.logging.PLoggerFactory;
import com.navercorp.pinpoint.bootstrap.plugin.annotation.Name;
import com.navercorp.pinpoint.plugin.thrift.ThriftConstants;

/**
 * @author HyunGil Jeong
 */
public class TSocketConstructInterceptor implements SimpleAroundInterceptor, ThriftConstants {

    private final PLogger logger = PLoggerFactory.getLogger(this.getClass());
    private final boolean isDebug = logger.isDebugEnabled();
    
    private final MetadataAccessor socketAccessor;
    
    public TSocketConstructInterceptor(
            @Name(METADATA_SOCKET) MetadataAccessor socketAccessor) {
        this.socketAccessor = socketAccessor;
    }

    @Override
    public void before(Object target, Object[] args) {
        // Do nothing
    }

    @Override
    public void after(Object target, Object[] args, Object result, Throwable throwable) {
        if (isDebug) {
            logger.afterInterceptor(target, args, result, throwable);
        }
        if (validate(target)) {
            Socket socket = ((TSocket)target).getSocket();
            this.socketAccessor.set(target, socket);
        }
    }

    private boolean validate(Object target) {
        if (!(target instanceof TSocket)) {
            return false;
        }
        if (!this.socketAccessor.isApplicable(target)) {
            if (isDebug) {
                logger.debug("Invalid target object. Need metadata accessor ({})", METADATA_SOCKET);
            }
            return false;
        }
        return true;
    }

}
