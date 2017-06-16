/*
 * Copyright 2017 Huawei Technologies Co., Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.servicecomb.springboot.starter.discovery;

import java.util.List;

import io.servicecomb.loadbalance.ServerListCache;
import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.ServerList;

public class CseServerListWrapper implements ServerList<Server> {
	private final ServerListCache serverListCache;

	public CseServerListWrapper(String appId, String microserviceName, String microserviceVersionRule,
			String transportName) {
		serverListCache = new CseServerListCacheWrapper(appId, microserviceName, microserviceVersionRule,
				transportName);
	}

	@Override
	public List<Server> getInitialListOfServers() {
		return serverListCache.getLatestEndpoints();
	}

	@Override
	public List<Server> getUpdatedListOfServers() {
		return getInitialListOfServers();
	}
}