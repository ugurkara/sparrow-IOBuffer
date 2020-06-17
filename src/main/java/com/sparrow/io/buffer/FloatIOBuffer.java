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
public class FloatIOBuffer extends IONumberBuffer<Float> {
    
    private static final int BYTE_SIZE = 4;

    public FloatIOBuffer(int size) {
        super(size);
    }

    @Override
    public Float getValue(int index) {
        return getBuffer().getFloat(index * BYTE_SIZE);
    }

    @Override
    public void setValue(int index, Float value) {
        Float oldValue = getValue(index);
        if (oldValue.floatValue() != value.floatValue()) {
            getBuffer().putFloat(index * BYTE_SIZE,value);
            fireListeners(index, oldValue, value);
        }

    }

    @Override
    public int getSize() {
        return getBuffer().capacity() / BYTE_SIZE;
    }
    
    
    
    @Override
    protected void put(ByteBuffer src, int offset) {
        for (int i = 0; i < getSize(); i++) {
            setValue(i, src.getFloat(i * BYTE_SIZE + offset));
        }
    }

}
