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

<<<<<<< HEAD
import java.lang.reflect.Method;
import java.lang.reflect.Constructor;
=======
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
>>>>>>> 711ad91e7099317dd9bee10ea06b60cacf0678f6

import org.junit.Test;
import org.junit.runner.RunWith;

<<<<<<< HEAD
import com.navercorp.pinpoint.bootstrap.plugin.test.PluginTestVerifier;
import com.navercorp.pinpoint.bootstrap.plugin.test.PluginTestVerifier.BlockType;
import com.navercorp.pinpoint.bootstrap.plugin.test.PluginTestVerifier.ExpectedAnnotation;
import com.navercorp.pinpoint.bootstrap.plugin.test.PluginTestVerifierHolder;
import com.navercorp.pinpoint.plugin.jackson.JacksonPlugin;
import com.navercorp.pinpoint.test.plugin.Dependency;
import com.navercorp.pinpoint.test.plugin.PinpointAgent;
import com.navercorp.pinpoint.test.plugin.PinpointPluginTestSuite;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ser.DefaultSerializerProvider;
import com.fasterxml.jackson.databind.deser.DefaultDeserializationContext;
=======
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.deser.DefaultDeserializationContext;
import com.fasterxml.jackson.databind.ser.DefaultSerializerProvider;
import com.navercorp.pinpoint.bootstrap.plugin.test.PluginTestVerifier;
import com.navercorp.pinpoint.bootstrap.plugin.test.PluginTestVerifier.BlockType;
import com.navercorp.pinpoint.bootstrap.plugin.test.PluginTestVerifierHolder;
import com.navercorp.pinpoint.test.plugin.Dependency;
import com.navercorp.pinpoint.test.plugin.PinpointPluginTestSuite;
>>>>>>> 711ad91e7099317dd9bee10ea06b60cacf0678f6

/**
 * @see JacksonPlugin#intercept_ObjectMapper(com.navercorp.pinpoint.bootstrap.plugin.ProfilerPluginContext)
 * @author Sungkook Kim
 */
@RunWith(PinpointPluginTestSuite.class)
<<<<<<< HEAD
@PinpointAgent("target/pinpoint-agent-1.5.0-SNAPSHOT")
@Dependency({"com.fasterxml.jackson.core:jackson-databind:2.5.0", "log4j:log4j:1.2.17"})
public class ObjectMapperIT {
=======
@Dependency({"com.fasterxml.jackson.core:jackson-databind:[2.0.6],[2.1.5],[2.2.4],[2.3.4],[2.4.6.1],[2.5.4,)"})
public class ObjectMapperIT {
    
    private final ObjectMapper mapper = new ObjectMapper();
>>>>>>> 711ad91e7099317dd9bee10ea06b60cacf0678f6

    @Test
    public void testConstructor() throws Exception {
        ObjectMapper mapper1 = new ObjectMapper();
        ObjectMapper mapper2 = new ObjectMapper(new JsonFactory());

        PluginTestVerifier verifier = PluginTestVerifierHolder.getInstance();
        verifier.printCache(System.out);
        verifier.printBlocks(System.out);

        Constructor<?> omConstructor = ObjectMapper.class.getConstructor(JsonFactory.class, DefaultSerializerProvider.class, DefaultDeserializationContext.class);
        Constructor<?> omConstructor1 = ObjectMapper.class.getConstructor();
        Constructor<?> omConstructor2 = ObjectMapper.class.getConstructor(JsonFactory.class);
        verifier.verifyApi("JACKSON", omConstructor);
        verifier.verifyApi("JACKSON", omConstructor1);
        verifier.verifyApi("JACKSON", omConstructor);
        verifier.verifyApi("JACKSON", omConstructor2);

        verifier.verifyTraceBlockCount(0);
    }

    @Test
    public void testWriteValue() throws Exception {
<<<<<<< HEAD

        ObjectMapper mapper = new ObjectMapper();

=======
>>>>>>> 711ad91e7099317dd9bee10ea06b60cacf0678f6
        __POJO pojo = new __POJO();
        pojo.setName("Jackson");

        String jsonStr = mapper.writeValueAsString(pojo);
        byte[] jsonByte = mapper.writeValueAsBytes(pojo);
<<<<<<< HEAD
=======
        
        ObjectWriter writer = mapper.writer();

        writer.writeValueAsString(pojo);
        writer.writeValueAsBytes(pojo);

>>>>>>> 711ad91e7099317dd9bee10ea06b60cacf0678f6

        PluginTestVerifier verifier = PluginTestVerifierHolder.getInstance();
        verifier.printCache(System.out);
        verifier.printBlocks(System.out);

<<<<<<< HEAD
        Constructor<?> omConstructor = ObjectMapper.class.getConstructor(JsonFactory.class, DefaultSerializerProvider.class, DefaultDeserializationContext.class);
        Constructor<?> omConstructor1 = ObjectMapper.class.getConstructor();
        Method writeval1 = ObjectMapper.class.getMethod("writeValueAsString", Object.class);
        Method writeval2 = ObjectMapper.class.getMethod("writeValueAsBytes", Object.class);
        ExpectedAnnotation length = annotation("jackson.json.length", 18);

        verifier.verifyApi("JACKSON", omConstructor);
        verifier.verifyApi("JACKSON", omConstructor1);
        verifier.verifyTraceBlock(BlockType.EVENT, "JACKSON", writeval1, null, null, null, null, length);
        verifier.verifyTraceBlock(BlockType.EVENT, "JACKSON", writeval2, null, null, null, null, length);
=======
        Method mapperWriteValueAsString = ObjectMapper.class.getMethod("writeValueAsString", Object.class);
        Method mapperWriteValueAsBytes = ObjectMapper.class.getMethod("writeValueAsBytes", Object.class);
        Method writerWriteValueAsString = ObjectWriter.class.getMethod("writeValueAsString", Object.class);
        Method writerWriteValueAsBytes = ObjectWriter.class.getMethod("writeValueAsBytes", Object.class);


        verifier.verifyTraceBlock(BlockType.EVENT, "JACKSON", mapperWriteValueAsString, null, null, null, null, annotation("jackson.json.length", jsonStr.length()));
        verifier.verifyTraceBlock(BlockType.EVENT, "JACKSON", mapperWriteValueAsBytes, null, null, null, null, annotation("jackson.json.length", jsonByte.length));

        verifier.verifyTraceBlock(BlockType.EVENT, "JACKSON", writerWriteValueAsString, null, null, null, null, annotation("jackson.json.length", jsonStr.length()));
        verifier.verifyTraceBlock(BlockType.EVENT, "JACKSON", writerWriteValueAsBytes, null, null, null, null, annotation("jackson.json.length", jsonByte.length));
>>>>>>> 711ad91e7099317dd9bee10ea06b60cacf0678f6

        verifier.verifyTraceBlockCount(0);
    }

    @Test
    public void testReadValue() throws Exception {
<<<<<<< HEAD
        ObjectMapper mapper = new ObjectMapper();

        String json_str = "{\"name\" : \"Jackson\"}";
        byte[] json_b = json_str.getBytes("UTF-8");
        __POJO pojo = mapper.readValue(json_str, __POJO.class);
        pojo = mapper.readValue(json_b, __POJO.class);

=======
        String json_str = "{\"name\" : \"Jackson\"}";
        byte[] json_b = json_str.getBytes("UTF-8");
    
        mapper.readValue(json_str, __POJO.class);
        mapper.readValue(json_b, __POJO.class);
        
        ObjectReader reader = mapper.reader(__POJO.class);
        
        reader.readValue(json_str);
        reader.readValue(json_b);

        
>>>>>>> 711ad91e7099317dd9bee10ea06b60cacf0678f6
        PluginTestVerifier verifier = PluginTestVerifierHolder.getInstance();
        verifier.printCache(System.out);
        verifier.printBlocks(System.out);

<<<<<<< HEAD
        Constructor<?> omConstructor = ObjectMapper.class.getConstructor(JsonFactory.class, DefaultSerializerProvider.class, DefaultDeserializationContext.class);
        Constructor<?> omConstructor1 = ObjectMapper.class.getConstructor();
        Method readval1 = ObjectMapper.class.getMethod("readValue", String.class, Class.class);
        Method readval2 = ObjectMapper.class.getMethod("readValue", byte[].class, Class.class);
        ExpectedAnnotation length = annotation("jackson.json.length", 20);

        verifier.verifyApi("JACKSON", omConstructor);
        verifier.verifyApi("JACKSON", omConstructor1);
        verifier.verifyTraceBlock(BlockType.EVENT, "JACKSON", readval1, null, null, null, null, length);
        verifier.verifyTraceBlock(BlockType.EVENT, "JACKSON", readval2, null, null, null, null, length);

        verifier.verifyTraceBlockCount(0);
    }
}

class __POJO {
    public String name;

    public String getName() { return name; }
    public void setName(String str) { name = str; }
}
=======
        Method mapperReadValueString = ObjectMapper.class.getMethod("readValue", String.class, Class.class);
        Method mapperReadValueBytes = ObjectMapper.class.getMethod("readValue", byte[].class, Class.class);
        Method readerReadValueString = ObjectReader.class.getMethod("readValue", String.class);
        Method readerReadValueBytes = ObjectReader.class.getMethod("readValue", byte[].class);

        verifier.verifyTraceBlock(BlockType.EVENT, "JACKSON", mapperReadValueString, null, null, null, null, annotation("jackson.json.length", json_str.length()));
        verifier.verifyTraceBlock(BlockType.EVENT, "JACKSON", mapperReadValueBytes, null, null, null, null, annotation("jackson.json.length", json_b.length));
        verifier.verifyTraceBlock(BlockType.EVENT, "JACKSON", readerReadValueString, null, null, null, null, annotation("jackson.json.length", json_str.length()));
        verifier.verifyTraceBlock(BlockType.EVENT, "JACKSON", readerReadValueBytes, null, null, null, null, annotation("jackson.json.length", json_b.length));
        
        verifier.verifyTraceBlockCount(0);
    }
    
    private static class __POJO {
        public String name;

        public String getName() { return name; }
        public void setName(String str) { name = str; }
    }
}


>>>>>>> 711ad91e7099317dd9bee10ea06b60cacf0678f6
