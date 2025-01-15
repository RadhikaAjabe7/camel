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
package org.apache.camel.dsl.jbang.core.commands.infra;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.camel.dsl.jbang.core.commands.CamelCommand;
import org.apache.camel.dsl.jbang.core.commands.CamelJBangMain;
import picocli.CommandLine;

public abstract class InfraBaseCommand extends CamelCommand {

    protected final ObjectMapper jsonMapper = new ObjectMapper();

    @CommandLine.Option(names = { "--json" },
                        description = "Output in JSON Format")
    boolean jsonOutput;

    public InfraBaseCommand(CamelJBangMain main) {
        super(main);

        jsonMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        jsonMapper.registerModule(new JavaTimeModule());
        jsonMapper.registerModule(new Jdk8Module());
        jsonMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    protected List<TestInfraService> getMetadata() throws IOException {
        List<TestInfraService> metadata;

        try (InputStream is
                = this.getClass().getClassLoader().getResourceAsStream("META-INF/test-infra-metadata.json")) {
            String json = new String(is.readAllBytes(), StandardCharsets.UTF_8);

            metadata = jsonMapper.readValue(json, new TypeReference<List<TestInfraService>>() {
            });
        }

        return metadata;
    }

    record TestInfraService(
            String service,
            String implementation,
            String description,
            List<String> alias,
            List<String> aliasImplementation) {
    }
}
