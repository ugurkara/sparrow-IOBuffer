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
public abstract class WordIOBuffer<T extends Number> extends NumberIOBuffer<T> {

    public static final int BYTE_SIZE = 2;

    public WordIOBuffer(int size) {
        super(size);
    }

    protected int charValue(int index) {
        int idx = ixWord(index);
        return makeChar(
                byteValue(idx + wordByteOrderIndex(0)),
                byteValue(idx + wordByteOrderIndex(1)));
    }

    protected void charValue(int index, int value) {
        int idx = ixWord(index);
        byteValue(idx + wordByteOrderIndex(0), char1((char) value));
        byteValue(idx + wordByteOrderIndex(1), char0((char) value));
    }

    @Override
    public int getSize() {
        return getByteSize() / BYTE_SIZE;
    }

    private static byte char1(char x) {
        return (byte) (x >> 8);
    }

    private static byte char0(char x) {
        return (byte) (x);
    }

    private int wordByteOrderIndex(int index) {
        return (index + byteOrder()) % BYTE_SIZE;
    }

    static int ixWord(int index) {
        return index * BYTE_SIZE;
    }

    static public char makeChar(byte b1, byte b0) {
        return (char) ((b1 << 8) | (b0 & 0xff));
    }

}
