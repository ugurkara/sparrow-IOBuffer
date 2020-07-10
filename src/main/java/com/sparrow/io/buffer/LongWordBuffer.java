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
public abstract class LongWordBuffer<T extends Number> extends NumberIOBuffer<T> {

    public static final int BYTE_SIZE = 8;

    protected LongWordBuffer(IOBuffer buffer) {
        super(buffer);
    }

    protected Long longValue(int index) {
        int idx = ixLong(index);
        return makeLong(
                byteValue(idx + longByteOrderIndex(0)),
                byteValue(idx + longByteOrderIndex(1)),
                byteValue(idx + longByteOrderIndex(2)),
                byteValue(idx + longByteOrderIndex(3)),
                byteValue(idx + longByteOrderIndex(4)),
                byteValue(idx + longByteOrderIndex(5)),
                byteValue(idx + longByteOrderIndex(6)),
                byteValue(idx + longByteOrderIndex(7)));
    }

    protected void longValue(int index, Long value) {

        int idx = ixLong(index);
        byteValue(idx + longByteOrderIndex(0), long7(value));
        byteValue(idx + longByteOrderIndex(1), long6(value));
        byteValue(idx + longByteOrderIndex(2), long5(value));
        byteValue(idx + longByteOrderIndex(3), long4(value));
        byteValue(idx + longByteOrderIndex(4), long3(value));
        byteValue(idx + longByteOrderIndex(5), long2(value));
        byteValue(idx + longByteOrderIndex(6), long1(value));
        byteValue(idx + longByteOrderIndex(7), long0(value));

    }

    @Override
    public int getSize() {
        return getByteSize() / BYTE_SIZE;
    }

    private static byte long7(long x) {
        return (byte) (x >> 56);
    }

    private static byte long6(long x) {
        return (byte) (x >> 48);
    }

    private static byte long5(long x) {
        return (byte) (x >> 40);
    }

    private static byte long4(long x) {
        return (byte) (x >> 32);
    }

    private static byte long3(long x) {
        return (byte) (x >> 24);
    }

    private static byte long2(long x) {
        return (byte) (x >> 16);
    }

    private static byte long1(long x) {
        return (byte) (x >> 8);
    }

    private static byte long0(long x) {
        return (byte) (x);
    }

    static int ixLong(int index) {
        return index * BYTE_SIZE;
    }

    private final static int[][] longOrders = new int[][]{
        {0, 1, 2, 3, 4, 5, 6, 7},
        {1, 0, 3, 2, 5, 4, 7, 6},
        {3, 2, 1, 0, 7, 6, 5, 4},
        {2, 3, 0, 1, 6, 7, 4, 5},
        {7, 6, 5, 4, 3, 2, 1, 0},
        {6, 7, 4, 5, 2, 3, 0, 1},
        {4, 5, 6, 7, 0, 1, 2, 3},
        {5, 4, 7, 6, 1, 0, 3, 2}
    };

    private int longByteOrderIndex(int index) {
        return longOrders[byteOrder() % BYTE_SIZE][index];
    }

    static private long makeLong(byte b7, byte b6, byte b5, byte b4,
            byte b3, byte b2, byte b1, byte b0) {
        return ((((long) b7) << 56)
                | (((long) b6 & 0xff) << 48)
                | (((long) b5 & 0xff) << 40)
                | (((long) b4 & 0xff) << 32)
                | (((long) b3 & 0xff) << 24)
                | (((long) b2 & 0xff) << 16)
                | (((long) b1 & 0xff) << 8)
                | (((long) b0 & 0xff)));
    }

}
