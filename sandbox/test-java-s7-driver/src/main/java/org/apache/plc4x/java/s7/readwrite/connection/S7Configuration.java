/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.plc4x.java.s7.readwrite.connection;

import org.apache.plc4x.java.spi.parser.ConfigurationParameter;
import org.apache.plc4x.java.spi.parser.Required;

public class S7Configuration {

    @ConfigurationParameter("rackId")
    public short rack = 1;
    @ConfigurationParameter("slotId")
    public short slot = 1;
    @ConfigurationParameter
    public short pduSize = 1024;
    @ConfigurationParameter
    public short maxAmqCaller = 8;
    @ConfigurationParameter
    public short maxAmqCallee = 8;
    @ConfigurationParameter
    public String controllerType;

    @Override public String toString() {
        return "Configuration{" +
            "rack=" + rack +
            ", slot=" + slot +
            ", pduSize=" + pduSize +
            ", maxAmqCaller=" + maxAmqCaller +
            ", maxAmqCallee=" + maxAmqCallee +
            ", controllerType='" + controllerType + '\'' +
            '}';
    }
}