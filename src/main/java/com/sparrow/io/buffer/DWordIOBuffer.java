
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

/**
 *
 * @author ugurkara
 */
public abstract class DWordIOBuffer<T extends Number> extends NumberIOBuffer<T> {

    public static final int BYTE_SIZE = 4;

    public DWordIOBuffer(int size) {
        super(size);
    }

    @Override
    public int getSize() {
        return getByteSize() / BYTE_SIZE;
    }

    public int intValue(int index) {
        int idx = ixInt(index);
        return makeInt(
                byteValue(idx + intByteOrderIndex(0)),
                byteValue(idx + intByteOrderIndex(1)),
                byteValue(idx + intByteOrderIndex(2)),
                byteValue(idx + intByteOrderIndex(3)));
    }

    public void intValue(int index, int value) {
        int idx = ixInt(index);
        byteValue(idx + intByteOrderIndex(0), int3(value));
        byteValue(idx + intByteOrderIndex(1), int2(value));
        byteValue(idx + intByteOrderIndex(2), int1(value));
        byteValue(idx + intByteOrderIndex(3), int0(value));
    }

    protected static byte int3(int x) {
        return (byte) (x >> 24);
    }

    protected static byte int2(int x) {
        return (byte) (x >> 16);
    }

    protected static byte int1(int x) {
        return (byte) (x >> 8);
    }

    protected static byte int0(int x) {
        return (byte) (x);
    }

    protected int intByteOrderIndex(int index) {

        return intOrders[byteOrder() % BYTE_SIZE][index];
    }

    static int ixInt(int index) {
        return index * BYTE_SIZE;
    }

    private final static int[][] intOrders = new int[][]{
        {0, 1, 2, 3},
        {1, 0, 3, 2},
        {3, 2, 1, 0},
        {2, 3, 0, 1}
    };

    static protected int makeInt(byte b3, byte b2, byte b1, byte b0) {
        return (((b3) << 24)
                | ((b2 & 0xff) << 16)
                | ((b1 & 0xff) << 8)
                | ((b0 & 0xff)));
    }

}
