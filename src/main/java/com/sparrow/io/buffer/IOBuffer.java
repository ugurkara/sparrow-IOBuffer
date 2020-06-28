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
 */
public class IOBuffer {

    

    private final IOBufferValueListeners listeners ;

    private final ArrayList<BaseIOBuffer> buffers = new ArrayList<>();
    
    private final long timeMillis=System.currentTimeMillis();

    protected IOBuffer(int size) {
        this.listeners =  new IOBufferValueListeners(size);

    }

    public int getSize() {
        return listeners.getBuffer().capacity();
    }

    public void put(ByteBuffer src, int offset) {

        buffers.forEach(new Consumer<BaseIOBuffer>() {
            @Override
            public void accept(BaseIOBuffer baseIOBuffer) {
                baseIOBuffer.put(src, offset);
            }
        });

    }

    public void get(ByteBuffer dst, int offset) {

        for (int i = 0; i < getSize(); i++) {
            byte get = listeners.getBuffer().get(i);
            dst.put( get);
        }

    }

    protected ByteBuffer getBuffer() {
        return listeners.getBuffer();
    }

    public static IOBuffer allocate(int size) {
        return new IOBuffer(size);
    }

    private ByteIOBuffer bytes = null;
    private ShortIOBuffer shorts = null;
    private UShortIOBuffer unsignedShorts = null;
    private IntegerIOBuffer integers = null;
    private FloatIOBuffer floats = null;
    private LongIOBuffer longs = null;
    private DoubleIOBuffer doubles = null;
    private BooleanIOBuffer bools = null;

    public BooleanIOBuffer booleanBuffer() {
        if (bools == null) {
            bools = new BooleanIOBuffer(listeners.getBuffer().capacity());
            bools.addListener(listeners.getBooleanListener());
            buffers.add(bools);
        }
        return bools;
    }

    public ByteIOBuffer byteBuffer() {
        if (bytes == null) {
            bytes = new ByteIOBuffer(listeners.getBuffer().capacity());
            bytes.addListener(listeners.getByteListener());
            buffers.add(bytes);
        }
        return bytes;
    }

    public UShortIOBuffer unsignedShortBuffer() {
        if (unsignedShorts == null) {
            unsignedShorts = new UShortIOBuffer(listeners.getBuffer().capacity());
            unsignedShorts.addListener(listeners.getUnsignedShortListener());
            buffers.add(unsignedShorts);
        }
        return unsignedShorts;
    }

    public ShortIOBuffer shortBuffer() {
        if (shorts == null) {
            shorts = new ShortIOBuffer(listeners.getBuffer().capacity());
            shorts.addListener(listeners.getShortListener());
            buffers.add(shorts);
        }
        return shorts;
    }

    public IntegerIOBuffer integerBuffer() {
        if (integers == null) {
            integers = new IntegerIOBuffer(listeners.getBuffer().capacity());
            integers.addListener(listeners.getIntegerListener());
            buffers.add(integers);
        }
        return integers;
    }

    public FloatIOBuffer floatBuffer() {
        if (floats == null) {
            floats = new FloatIOBuffer(listeners.getBuffer().capacity());
            floats.addListener(listeners.getFloatListener());
            buffers.add(floats);
        }
        return floats;
    }

    public LongIOBuffer longBuffer() {
        if (longs == null) {
            longs = new LongIOBuffer(listeners.getBuffer().capacity());
            longs.addListener(listeners.getLongListener());
            buffers.add(longs);
        }
        return longs;
    }

    public DoubleIOBuffer doubleBuffer() {
        if (doubles == null) {
            doubles = new DoubleIOBuffer(listeners.getBuffer().capacity());
            doubles.addListener(listeners.getDoubleListener());
            buffers.add(doubles);
        }
        return doubles;
    }

    public void addListener(IOBufferChangeListener listener) {
        listeners.addListener(listener);
    }

    public void removeListener(IOBufferChangeListener listener) {
        listeners.removeListener(listener);
    }

    public long getTimeMillis() {
        return timeMillis;
    }
    
    

}
