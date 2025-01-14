/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.camel.test.infra.couchdb.common;

public final class CouchDbProperties {
    public static final String SERVICE_ADDRESS = "couchdb.service.address";
    public static final String HOST = "couchdb.host";
    public static final String PORT = "couchdb.port";
    public static final int DEFAULT_PORT = 5984;
    public static final String COUCHDB_CONTAINER = "couchdb.container";
    public static final String COUCHDB_CONTAINER_PPC64LE = "couchdb.container.ppc64le";

    private CouchDbProperties() {

    }
}
