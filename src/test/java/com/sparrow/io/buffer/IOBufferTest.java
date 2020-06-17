/* 
 * Copyright 2020 KR ENDÜSTRİYEL BİLİŞİM LTD. ŞTİ..
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.sparrow.io.buffer;

import java.nio.ByteBuffer;
import java.util.BitSet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author ugurkara
 */
public class IOBufferTest {

    private final IOBuffer ioBuffer = new IOBuffer(128);
    private final ByteBuffer byteBuffer = ByteBuffer.allocate(128);
    private final BitSet bitSet = new BitSet(128 * 8);

    public IOBufferTest() {

        ioBuffer.addListener(new IOBufferChangeListener() {
            @Override
            public void changed(int i, Boolean oldValue, Boolean newValue) {
                bitSet.set(i, newValue);
            }

            @Override
            public void changed(int index, Byte oldValue, Byte newValue) {
                byteBuffer.put(index, newValue);
            }

            @Override
            public void changed(int index, Integer oldValue, Integer newValue) {
                byteBuffer.putInt(index * 4, newValue);
            }

            @Override
            public void changed(int index, Short oldValue, Short newValue) {
                byteBuffer.putShort(index * 2, newValue);
            }

            @Override
            public void changed(int index, Float oldValue, Float newValue) {
                byteBuffer.putFloat(index * 4, newValue);
            }

            @Override
            public void changed(int index, Double oldValue, Double newValue) {
                byteBuffer.putDouble(index * 8, newValue);
            }

            @Override
            public void changed(int index, Long oldValue, Long newValue) {
                byteBuffer.putLong(index * 8, newValue);
            }

            @Override
            public void changed(int index, char oldValue, char newValue) {
                byteBuffer.putChar(index * 2, newValue);
            }
        });

    }

    @org.junit.jupiter.api.BeforeAll
    public static void setUpClass() throws Exception {
    }

    @org.junit.jupiter.api.AfterAll
    public static void tearDownClass() throws Exception {
    }

    @org.junit.jupiter.api.BeforeEach
    public void setUp() throws Exception {

    }

    @org.junit.jupiter.api.AfterEach
    public void tearDown() throws Exception {
    }

    @Test
    public void testInt() {

        IntegerIOBuffer buf = ioBuffer.integerBuffer();

        for (int i = 0; i < buf.getSize(); i++) {
            buf.setValue(i, 0);
            byteBuffer.putInt(i * 4, 0);
        }

        for (int i = 0; i < buf.getSize(); i++) {
            Assertions.assertEquals(buf.getValue(i), 0);
            Assertions.assertEquals(byteBuffer.getInt(i * 4), 0);
        }

        for (int i = 0; i < buf.getSize(); i++) {
            buf.setValue(i, i);
        }

        for (int i = 0; i < buf.getSize(); i++) {
            Assertions.assertEquals(buf.getValue(i), i);
            Assertions.assertEquals(byteBuffer.getInt(i * 4), i);
        }

        for (int i = 0; i < buf.getSize(); i++) {
            byteBuffer.putInt(i * 4, i * 100);
        }

        ioBuffer.put(byteBuffer, 0);

        for (int i = 0; i < buf.getSize(); i++) {
            Assertions.assertEquals(buf.getValue(i), i * 100);
            Assertions.assertEquals(byteBuffer.getInt(i * 4), i * 100);
        }

    }

    @Test
    public void testFloat() {

        FloatIOBuffer buf = ioBuffer.floatBuffer();

        for (int i = 0; i < buf.getSize(); i++) {
            buf.setValue(i, 0f);
            byteBuffer.putFloat(i * 4, 0);
        }

        for (int i = 0; i < buf.getSize(); i++) {
            Assertions.assertEquals(buf.getValue(i), 0);
            Assertions.assertEquals(byteBuffer.getFloat(i * 4), 0);
        }

        for (int i = 0; i < buf.getSize(); i++) {
            buf.setValue(i, (float) i);
        }

        for (int i = 0; i < buf.getSize(); i++) {
            Assertions.assertEquals(buf.getValue(i), i);
            Assertions.assertEquals(byteBuffer.getFloat(i * 4), i);
        }

        for (int i = 0; i < buf.getSize(); i++) {
            byteBuffer.putFloat(i * 4, i * 100);
        }

        ioBuffer.put(byteBuffer, 0);

        for (int i = 0; i < buf.getSize(); i++) {
            Assertions.assertEquals(buf.getValue(i), i * 100);
            Assertions.assertEquals(byteBuffer.getFloat(i * 4), i * 100);
        }

    }

    @Test
    public void testShort() {

        ShortIOBuffer buf = ioBuffer.shortBuffer();

        for (int i = 0; i < buf.getSize(); i++) {
            buf.setValue(i, (short) 0);
            byteBuffer.putShort(i * 2, (short) 0);
        }

        for (int i = 0; i < buf.getSize(); i++) {
            Assertions.assertEquals(buf.getValue(i), (short) 0);
            Assertions.assertEquals(byteBuffer.getShort(i * 2), (short) 0);
        }

        for (int i = 0; i < buf.getSize(); i++) {
            buf.setValue(i, (short) i);
        }

        for (int i = 0; i < buf.getSize(); i++) {
            Assertions.assertEquals(buf.getValue(i), (short) i);
            Assertions.assertEquals(byteBuffer.getShort(i * 2), i);
        }

        for (int i = 0; i < buf.getSize(); i++) {
            byteBuffer.putShort(i * 2, (short) (i * 100));
        }

        ioBuffer.put(byteBuffer, 0);

        for (int i = 0; i < buf.getSize(); i++) {
            Assertions.assertEquals(buf.getValue(i), (short) (i * 100));
            Assertions.assertEquals(byteBuffer.getShort(i * 2), i * 100);
        }

    }

    @Test
    public void testChar() {

        UnsignedShortIOBuffer buf = ioBuffer.unsignedShortBuffer();

        for (int i = 0; i < buf.getSize(); i++) {
            buf.setValue(i, 0);
            byteBuffer.putChar(i * 2, (char) 0);
        }

        for (int i = 0; i < buf.getSize(); i++) {
            Assertions.assertEquals(buf.getValue(i), (char) 0);
            Assertions.assertEquals(byteBuffer.getChar(i * 2), (char) 0);
        }

        for (int i = 0; i < buf.getSize(); i++) {
            buf.setValue(i, i);
        }

        for (int i = 0; i < buf.getSize(); i++) {
            Assertions.assertEquals(buf.getValue(i), (char) i);
            Assertions.assertEquals(byteBuffer.getChar(i * 2), i);
        }

        for (int i = 0; i < buf.getSize(); i++) {
            byteBuffer.putChar(i * 2, (char) (i * 1100));
        }

        ioBuffer.put(byteBuffer, 0);

        for (int i = 0; i < buf.getSize(); i++) {
            Assertions.assertEquals(buf.getValue(i), byteBuffer.getChar(i * 2));
            Assertions.assertEquals(byteBuffer.getChar(i * 2), (char) (i * 1100));
        }

    }

    @Test
    public void testLong() {

        LongIOBuffer buf = ioBuffer.longBuffer();

        for (int i = 0; i < buf.getSize(); i++) {
            buf.setValue(i, (long) 0);
            byteBuffer.putLong(i * 8, (long) 0);
        }

        for (int i = 0; i < buf.getSize(); i++) {
            Assertions.assertEquals(buf.getValue(i), (long) 0);
            Assertions.assertEquals(byteBuffer.getLong(i * 8), (long) 0);
        }

        for (int i = 0; i < buf.getSize(); i++) {
            buf.setValue(i, (long) i);
        }

        for (int i = 0; i < buf.getSize(); i++) {
            Assertions.assertEquals(buf.getValue(i), (long) i);
            Assertions.assertEquals(byteBuffer.getLong(i * 8), i);
        }

        for (int i = 0; i < buf.getSize(); i++) {
            byteBuffer.putLong(i * 8, (long) (i * 1100));
        }

        ioBuffer.put(byteBuffer, 0);

        for (int i = 0; i < buf.getSize(); i++) {
            Assertions.assertEquals(buf.getValue(i), byteBuffer.getLong(i * 8));
            Assertions.assertEquals(byteBuffer.getLong(i * 8), (long) (i * 1100));
        }

    }

    @Test
    public void testDouble() {

        DoubleIOBuffer buf = ioBuffer.doubleBuffer();

        for (int i = 0; i < buf.getSize(); i++) {
            buf.setValue(i, (double) 0);
            byteBuffer.putLong(i * 8, (long) 0);
        }

        for (int i = 0; i < buf.getSize(); i++) {
            Assertions.assertEquals(buf.getValue(i), 0d);
            Assertions.assertEquals(byteBuffer.getDouble(i * 8), 0d);
        }

        for (int i = 0; i < buf.getSize(); i++) {
            buf.setValue(i, (double) i);
        }

        for (int i = 0; i < buf.getSize(); i++) {
            Assertions.assertEquals(buf.getValue(i), (double) i);
            Assertions.assertEquals(byteBuffer.getDouble(i * 8), i);
        }

        for (int i = 0; i < buf.getSize(); i++) {
            byteBuffer.putDouble(i * 8, (double) (i * 1100));
        }

        ioBuffer.put(byteBuffer, 0);

        for (int i = 0; i < buf.getSize(); i++) {
            Assertions.assertEquals(buf.getValue(i), byteBuffer.getDouble(i * 8));
            Assertions.assertEquals(byteBuffer.getDouble(i * 8), (double) (i * 1100));
        }

    }

    @Test
    public void testByte() {

        ByteIOBuffer buf = ioBuffer.byteBuffer();

        for (int i = 0; i < buf.getSize(); i++) {
            buf.setValue(i, (byte)0);
            byteBuffer.put(i, (byte) 0);
        }

        for (int i = 0; i < buf.getSize(); i++) {
            Assertions.assertEquals(buf.getValue(i), (byte)0);
            Assertions.assertEquals(byteBuffer.get(i), (byte)0);
        }

        for (int i = 0; i < buf.getSize(); i++) {
            buf.setValue(i, (byte)i);
        }

        for (int i = 0; i < buf.getSize(); i++) {
            Assertions.assertEquals(buf.getValue(i), (byte) i);
            Assertions.assertEquals(byteBuffer.get(i), i);
        }

        for (int i = 0; i < buf.getSize(); i++) {
            byteBuffer.put(i, (byte) (i * 1100));
        }

        ioBuffer.put(byteBuffer, 0);

        for (int i = 0; i < buf.getSize(); i++) {
            Assertions.assertEquals(buf.getValue(i), byteBuffer.get(i));
            Assertions.assertEquals(byteBuffer.get(i), (byte) (i * 1100));
        }

    }

    
    @Test
    public void testBooleans() {

        BooleanIOBuffer buf = ioBuffer.booleanBuffer();

        for (int i = 0; i < buf.getSize(); i++) {
            buf.setValue(i, false);
            bitSet.set(i,false);
        }

        for (int i = 0; i < buf.getSize(); i++) {
            Assertions.assertEquals(buf.getValue(i), false);
            Assertions.assertEquals(bitSet.get(i), false);
        }

        for (int i = 0; i < buf.getSize(); i++) {
            buf.setValue(i, i%2==0?true:false);
        }

        for (int i = 0; i < buf.getSize(); i++) {
            Assertions.assertEquals(buf.getValue(i), i%2==0?true:false);
            Assertions.assertEquals(bitSet.get(i), i%2==0?true:false);
        }

        for (int i = 0; i < buf.getSize(); i++) {
            bitSet.set(i, i%4==0?true:false);
        }

        byteBuffer.put(bitSet.toByteArray(), 0, byteBuffer.limit());
        ioBuffer.put(byteBuffer, 0);

        for (int i = 0; i < buf.getSize(); i++) {
            Assertions.assertEquals(buf.getValue(i), i%4==0?true:false);
        }

    }

}
