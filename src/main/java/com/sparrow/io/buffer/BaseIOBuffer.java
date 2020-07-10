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
import java.util.ArrayList;
import java.util.function.Consumer;

/**
 *
 * @author ugurkara
 * @param <T>
 */
public abstract class BaseIOBuffer<T> {
    
    private final byte[] buffer;
    
    private final ArrayList<IOBufferValueChangeListener> valueListeners = new ArrayList();
    
    
    
    private final IOBuffer parent;
    
    
    
    protected BaseIOBuffer(IOBuffer parent) {
        buffer = new byte[parent.getSize()];
        this.parent = parent;
    }
    
    public abstract int getSize();
    
    public int getByteSize() {
        return buffer.length;
    }
    
    public abstract T getValue(int index);
    
    public abstract void setValue(int index, T value);
    
    protected abstract void put(ByteBuffer src, int offset);
    
    public void addListener(IOBufferValueChangeListener<T> listener) {
        valueListeners.add(listener);
    }
    
    public void removeListener(IOBufferValueChangeListener<T> listener) {
        valueListeners.remove(listener);
    }
    
    protected void fireListeners(int index, T oldValue, T newValue) {
        
        valueListeners.forEach(new Consumer<IOBufferValueChangeListener>() {
            @Override
            public void accept(IOBufferValueChangeListener t) {
                t.changed(index, oldValue, newValue);
            }
        });
        
    }
    
    protected byte byteValue(int index) {
        return buffer[index];
    }
    
    protected void byteValue(int index, byte value) {
        buffer[index] = value;
    }
    
    public IOBuffer getIOBuffer() {
        return parent;
    }
    
   
    
}
