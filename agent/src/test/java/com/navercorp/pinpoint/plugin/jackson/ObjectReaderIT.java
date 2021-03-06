/**
 * Copyright 2014 NAVER Corp.
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
package com.navercorp.pinpoint.plugin.jackson;

import static com.navercorp.pinpoint.bootstrap.plugin.test.PluginTestVerifier.ExpectedAnnotation.*;

import java.lang.reflect.Method;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.navercorp.pinpoint.bootstrap.plugin.test.PluginTestVerifier;
import com.navercorp.pinpoint.bootstrap.plugin.test.PluginTestVerifier.BlockType;
import com.navercorp.pinpoint.bootstrap.plugin.test.PluginTestVerifierHolder;
import com.navercorp.pinpoint.test.plugin.Dependency;
import com.navercorp.pinpoint.test.plugin.PinpointPluginTestSuite;

/**
 * @see JacksonPlugin#intercept_ObjectMapper(com.navercorp.pinpoint.bootstrap.plugin.ProfilerPluginContext)
 * @author Sungkook Kim
 */
@RunWith(PinpointPluginTestSuite.class)
@Dependency({"com.fasterxml.jackson.core:jackson-databind:[2.0.6],[2.1.5],[2.2.4],[2.3.4],[2.4.6.1],[2.5.4,)"})
public class ObjectReaderIT {
    
    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testWriteValue() throws Exception {
        __POJO pojo = new __POJO();
        pojo.setName("Jackson");
        
        ObjectWriter writer = mapper.writer();

        String jsonStr = writer.writeValueAsString(pojo);
        byte[] jsonByte = writer.writeValueAsBytes(pojo);

        PluginTestVerifier verifier = PluginTestVerifierHolder.getInstance();
        verifier.printCache(System.out);
        verifier.printBlocks(System.out);

        Method writeval1 = ObjectWriter.class.getMethod("writeValueAsString", Object.class);
        Method writeval2 = ObjectWriter.class.getMethod("writeValueAsBytes", Object.class);

        verifier.verifyTraceBlock(BlockType.EVENT, "JACKSON", writeval1, null, null, null, null, annotation("jackson.json.length", jsonStr.length()));
        verifier.verifyTraceBlock(BlockType.EVENT, "JACKSON", writeval2, null, null, null, null, annotation("jackson.json.length", jsonByte.length));

        verifier.verifyTraceBlockCount(0);
    }

    @Test
    public void testReadValue() throws Exception {
        String json_str = "{\"name\" : \"Jackson\"}";
        byte[] json_b = json_str.getBytes("UTF-8");
        
        ObjectReader reader = mapper.reader(__POJO.class);
        
        __POJO pojo = reader.readValue(json_str);
        pojo = reader.readValue(json_b);

        PluginTestVerifier verifier = PluginTestVerifierHolder.getInstance();
        verifier.printCache(System.out);
        verifier.printBlocks(System.out);

        Method readval1 = ObjectReader.class.getMethod("readValue", String.class);
        Method readval2 = ObjectReader.class.getMethod("readValue", byte[].class);

        verifier.verifyTraceBlock(BlockType.EVENT, "JACKSON", readval1, null, null, null, null, annotation("jackson.json.length", json_str.length()));
        verifier.verifyTraceBlock(BlockType.EVENT, "JACKSON", readval2, null, null, null, null, annotation("jackson.json.length", json_b.length));

        verifier.verifyTraceBlockCount(0);
    }
    
    private static class __POJO {
        public String name;

        public String getName() { return name; }
        public void setName(String str) { name = str; }
    }
}


