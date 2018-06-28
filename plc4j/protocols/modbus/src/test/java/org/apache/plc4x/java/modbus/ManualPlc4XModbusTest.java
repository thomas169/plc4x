/*
 Licensed to the Apache Software Foundation (ASF) under one
 or more contributor license agreements.  See the NOTICE file
 distributed with this work for additional information
 regarding copyright ownership.  The ASF licenses this file
 to you under the Apache License, Version 2.0 (the
 "License"); you may not use this file except in compliance
 with the License.  You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing,
 software distributed under the License is distributed on an
 "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 KIND, either express or implied.  See the License for the
 specific language governing permissions and limitations
 under the License.
 */
package org.apache.plc4x.java.modbus;

import org.apache.commons.io.HexDump;
import org.apache.plc4x.java.PlcDriverManager;
import org.apache.plc4x.java.api.connection.PlcConnection;
import org.apache.plc4x.java.api.connection.PlcReader;
import org.apache.plc4x.java.api.messages.items.ReadResponseItem;
import org.apache.plc4x.java.api.messages.specific.TypeSafePlcReadRequest;
import org.apache.plc4x.java.api.messages.specific.TypeSafePlcReadResponse;
import org.apache.plc4x.java.api.model.Address;

import java.io.ByteArrayOutputStream;
import java.util.concurrent.CompletableFuture;

public class ManualPlc4XModbusTest {

    public static void main(String... args) throws Exception {
        String connectionUrl;
        if (args.length > 0 && "serial".equalsIgnoreCase(args[0])) {
            System.out.println("Using serial");
            connectionUrl = "modbus:serial:///dev/ttys003";
        } else {
            System.out.println("Using tcp");
            connectionUrl = "modbus:tcp://10.10.64.10";
        }
        try (PlcConnection plcConnection = new PlcDriverManager().getConnection(connectionUrl)) {
            System.out.println("PlcConnection " + plcConnection);

            PlcReader reader = plcConnection.getReader().orElseThrow(() -> new RuntimeException("No Reader found"));

            Address address = plcConnection.parseAddress("readcoils:1/0");
            CompletableFuture<TypeSafePlcReadResponse<byte[]>> response = reader
                .read(new TypeSafePlcReadRequest<>(byte[].class, address));
            TypeSafePlcReadResponse<byte[]> readResponse = response.get();
            System.out.println("Response " + readResponse);
            ReadResponseItem<byte[]> responseItem = readResponse.getResponseItem().orElseThrow(() -> new RuntimeException("No Item found"));
            System.out.println("ResponseItem " + responseItem);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            HexDump.dump(responseItem.getValues().get(0), 0, byteArrayOutputStream, 0);
            responseItem.getValues().stream().map(integer -> "Value: " + byteArrayOutputStream).forEach(System.out::println);
        }
        System.exit(0);
    }
}
