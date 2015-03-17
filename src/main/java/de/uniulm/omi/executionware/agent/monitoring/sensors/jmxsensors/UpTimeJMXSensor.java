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

package de.uniulm.omi.executionware.agent.monitoring.sensors.jmxsensors;

import de.uniulm.omi.executionware.agent.monitoring.api.Measurement;
import de.uniulm.omi.executionware.agent.monitoring.api.MeasurementNotAvailableException;
import de.uniulm.omi.executionware.agent.monitoring.api.SensorInitializationException;
import de.uniulm.omi.executionware.agent.monitoring.impl.MeasurementImpl;
import de.uniulm.omi.executionware.agent.monitoring.impl.MonitorContext;
import de.uniulm.omi.executionware.agent.monitoring.sensors.AbstractSensor;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

/**
 * 
 * @author zarioha
 * A probe for measuring the upTime of the Java virtual machine
 */
public class UpTimeJMXSensor extends AbstractSensor {
	
	private  RuntimeMXBean runtimeBean;
    @Override
    protected Measurement getMeasurement(MonitorContext monitorContext) throws MeasurementNotAvailableException {
        long upTime = runtimeBean.getUptime();
        return new MeasurementImpl(System.currentTimeMillis(), upTime);
    }
    
    @Override
    protected void initialize() throws SensorInitializationException 
    {
    	super.initialize();
    	runtimeBean = ManagementFactory.getRuntimeMXBean();
    }
}
