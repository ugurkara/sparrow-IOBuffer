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

/**
 *
 * @author ugurkara
 */
public class BooleanIOBuffer extends BaseIOBuffer<Boolean> {

    public BooleanIOBuffer(int size) {
        super(size);
    }

    protected Byte byteValueOfBit(int index) {
        return byteValue(index / 8);
    }

    protected void byteValueOfBit(int index, Byte value) {
        byteValue(index / 8, value);
    }

    @Override
    public Boolean getValue(int index) {
        return getBit(index % 8, byteValueOfBit(index));
    }

    @Override
    public void setValue(int index, Boolean value) {

        Boolean oldValue = getValue(index);

        if (value.booleanValue() != oldValue.booleanValue()) {
            int bitIndex = bitIndexOf(index);
            byte b = value ? setBitOn(bitIndex, BooleanIOBuffer.this.byteValueOfBit(index)) : setBitOff(bitIndex, BooleanIOBuffer.this.byteValueOfBit(index));
            byteValueOfBit(index, b);
            fireListeners(index, oldValue, value);

        }

    }

    @Override
    public int getSize() {
        return getByteSize() * 8;
    }

    public static byte setBitOff(int index, byte b) {
        b &= ~(1 << index);
        return b;
    }

    public static byte setBitOn(int index, byte b) {
        b |= 1 << index;
        return b;
    }

    public static byte toggleBit(int index, byte b) {
        b ^= 1 << index;
        return b;
    }

    private static int bitIndexOf(int index) {
        return index % 8;
    }

    private static int byteIndexOf(int index) {
        return index / 8;
    }

    public static boolean getBit(int index, byte b) {
        boolean boolvalue = (b & (0x01 << index)) != 0;
        return boolvalue;
    }

    public static byte setBit(int index, byte b, boolean value) {
        if (value) {
            return setBitOn(index, b);
        }
        return setBitOff(index, b);
    }

    @Override
    protected void put(ByteBuffer src, int offset) {

        for (int i = 0; i < getByteSize(); i++) {
            byte oldValue = byteValue(i);
            byte value = src.get(i + offset);

            if (oldValue != value) {
                for (int bit = 0; bit < 8; bit++) {
                    setValue(i * 8 + bit, getBit(bit, value));
                }
            }
        }
    }

}
