/*
 * Copyright (c) 2014-2015 University of Ulm
 *
 * See the NOTICE file distributed with this work for additional information
 * regarding copyright ownership.  Licensed under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package de.uniulm.omi.cloudiator.visor.rest.entities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import de.uniulm.omi.cloudiator.visor.exceptions.MonitorException;
import de.uniulm.omi.cloudiator.visor.monitoring.*;

import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.Map;

/**
 * Created by daniel on 11.11.15.
 */
public class SensorMonitorDto extends MonitorDto {

    @NotNull private String sensorClassName;
    @NotNull @JsonSerialize(as = DefaultInterval.class) @JsonDeserialize(as = DefaultInterval.class)
    private Interval interval;
    private Map<String, String> sensorConfiguration;

    public SensorMonitorDto(String uuid, String metricName, String componentId,
        Map<String, String> sensorConfiguration, Map<String, String> monitorContext,
        String sensorClassName, Interval interval) {
        super(uuid, metricName, componentId, monitorContext);
        this.sensorClassName = sensorClassName;
        this.interval = interval;
        this.sensorConfiguration = sensorConfiguration;
    }

    protected SensorMonitorDto() {

    }

    @Override public Monitor start(String uuid, MonitoringService monitoringService)
        throws MonitorException {
        return monitoringService
            .startMonitor(uuid, getComponentId(), getMetricName(), getSensorClassName(),
                getInterval(), getMonitorContext(),
                SensorConfigurationBuilder.newBuilder().addValues(getSensorConfiguration())
                    .build());
    }


    public String getSensorClassName() {
        return sensorClassName;
    }

    public void setSensorClassName(String sensorClassName) {
        this.sensorClassName = sensorClassName;
    }

    public Interval getInterval() {
        return interval;
    }

    public void setInterval(Interval interval) {
        this.interval = interval;
    }

    public Map<String, String> getSensorConfiguration() {
        if (sensorConfiguration == null) {
            return Collections.emptyMap();
        }
        return sensorConfiguration;
    }
}
