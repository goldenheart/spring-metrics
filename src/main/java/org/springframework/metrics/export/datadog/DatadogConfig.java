/**
 * Copyright 2017 Pivotal Software, Inc.
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
package org.springframework.metrics.export.datadog;

import com.netflix.spectator.api.RegistryConfig;

import java.time.Duration;

/**
 * Configuration for Datadog exporting.
 *
 * @author Jon Schneider
 */
public interface DatadogConfig extends RegistryConfig {

    default String apiKey() {
        String v = get("datadog.apiKey");
        if(v == null)
            throw new IllegalStateException("datadog.apiKey must be set to report metrics to Datadog");
        return v;
    }

    /**
     * Returns the step size (reporting frequency) to use. The default is 10 seconds, matching the default
     * for the Datadog agent.
     */
    default Duration step() {
        String v = get("datadog.step");
        return v == null ? Duration.ofSeconds(10) : Duration.parse(v);
    }

    /**
     * Returns true if publishing to Datadog is enabled. Default is {@code true}.
     */
    default boolean enabled() {
        String v = get("datadog.enabled");
        return v == null || Boolean.valueOf(v);
    }

    /**
     * Returns the number of threads to use with the scheduler. The default is
     * 2 threads.
     */
    default int numThreads() {
        String v = get("datadog.numThreads");
        return v == null ? 2 : Integer.parseInt(v);
    }

    /**
     * Returns the connection timeout for requests to the backend. The default is
     * 1 second.
     */
    default Duration connectTimeout() {
        String v = get("datadog.connectTimeout");
        return v == null ? Duration.ofSeconds(1) : Duration.parse(v);
    }

    /**
     * Returns the read timeout for requests to the backend. The default is
     * 10 seconds.
     */
    default Duration readTimeout() {
        String v = get("datadog.readTimeout");
        return v == null ? Duration.ofSeconds(10) : Duration.parse(v);
    }

    /**
     * Returns the number of measurements per request to use for the backend. If more
     * measurements are found, then multiple requests will be made. The default is
     * 10,000.
     */
    default int batchSize() {
        String v = get("datadog.batchSize");
        return v == null ? 10000 : Integer.parseInt(v);
    }

    /**
     * The tag that will be mapped to "host" when shipping metrics to datadog, or {@code null} if
     * host should be omitted on publishing.
     */
    default String hostTag() {
        String v = get("datadog.hostTag");
        return v == null ? "instance" : v;
    }
}
