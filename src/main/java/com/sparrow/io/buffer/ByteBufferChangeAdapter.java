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
public class ByteBufferChangeAdapter extends IOBufferChangeAdapter {

    private final ByteBuffer buffer;

    public ByteBufferChangeAdapter(int capacity) {
        this.buffer = ByteBuffer.allocate(capacity);
    }

    @Override
    public void changed(int i, Boolean oldValue, Boolean newValue) {

    }

    @Override
    public void changed(int index, Byte oldValue, Byte newValue) {
        buffer.put(index, newValue);
    }

    @Override
    public void changed(int index, Integer oldValue, Integer newValue) {
        buffer.putInt(index * 4, newValue);
    }

    @Override
    public void changed(int index, Short oldValue, Short newValue) {
        buffer.putShort(index * 2, newValue);
    }

    @Override
    public void changed(int index, Float oldValue, Float newValue) {
        buffer.putFloat(index * 4, newValue);
    }

    @Override
    public void changed(int index, Double oldValue, Double newValue) {
        buffer.putDouble(index * 8, newValue);
    }

    @Override
    public void changed(int index, Long oldValue, Long newValue) {
        buffer.putLong(index * 8, newValue);
    }

    @Override
    public void changed(int index, char oldValue, char newValue) {
        buffer.putChar(index * 2, newValue);
    }

}
