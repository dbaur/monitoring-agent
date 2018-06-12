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

package de.uniulm.omi.cloudiator.visor.reporting;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import de.uniulm.omi.cloudiator.visor.monitoring.Intervals;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by daniel on 12.12.14.
 */
public class QueueWorkerFactory<T> implements QueueWorkerFactoryInterface<T> {

  private final int reportingInterval;

  @Inject
  public QueueWorkerFactory(@Named("reportingInterval") int reportingInterval) {
    this.reportingInterval = reportingInterval;
  }

  @Override
  public QueueWorker<T> create(ReportingInterface<T> reportingInterface, BlockingQueue<T> queue) {
    return new QueueWorker<T>(queue, reportingInterface,
        Intervals.of(reportingInterval, TimeUnit.SECONDS));
  }
}
