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

import java.util.ArrayList;
import java.util.function.Consumer;

/**
 *
 * @author ugurkara
 */
public class IOBufferValueListeners {

    private ArrayList<IOBufferChangeListener> listeners = new ArrayList<>();

    public void addListener(IOBufferChangeListener listener) {
        listeners.add(listener);
    }

    public void removeListener(IOBufferChangeListener listener) {
        listeners.remove(listener);
    }

    public IOBufferValueChangeListener<Byte> getByteListener() {
        return byteListener;
    }

    public IOBufferValueChangeListener<Boolean> getBooleanListener() {
        return booleanListener;
    }

    public IOBufferValueChangeListener<Double> getDoubleListener() {
        return doubleListener;
    }

    public IOBufferValueChangeListener<Float> getFloatListener() {
        return floatListener;
    }

    public IOBufferValueChangeListener<Integer> getIntegerListener() {
        return integerListener;
    }

    public IOBufferValueChangeListener<Long> getLongListener() {
        return longListener;
    }

    public IOBufferValueChangeListener<Short> getShortListener() {
        return shortListener;
    }

    public IOBufferValueChangeListener<Integer> getUnsignedShortListener() {
        return unsignedShortListener;
    }
    

    private final IOBufferValueChangeListener<Byte> byteListener = new IOBufferValueChangeListener<Byte>() {

        @Override
        public void changed(int index, Byte oldValue, Byte newValue) {

            listeners.forEach(new Consumer<IOBufferChangeListener>() {
                @Override
                public void accept(IOBufferChangeListener l) {
                    l.changed(index, oldValue, newValue);
                }
            });

        }
    };

    private final IOBufferValueChangeListener<Short> shortListener = new IOBufferValueChangeListener<Short>() {

        @Override
        public void changed(int index, Short oldValue, Short newValue) {

            listeners.forEach(new Consumer<IOBufferChangeListener>() {
                @Override
                public void accept(IOBufferChangeListener l) {
                    l.changed(index, oldValue, newValue);
                }
            });

        }
    };

    private final IOBufferValueChangeListener<Integer> unsignedShortListener = new IOBufferValueChangeListener<Integer>() {

        @Override
        public void changed(int index, Integer oldValue, Integer newValue) {

            listeners.forEach(new Consumer<IOBufferChangeListener>() {
                @Override
                public void accept(IOBufferChangeListener l) {
                    l.changed(index, (char) oldValue.intValue(), (char) newValue.intValue());
                }
            });

        }
    };

    private final IOBufferValueChangeListener<Integer> integerListener = new IOBufferValueChangeListener<Integer>() {
        @Override
        public void changed(int index, Integer oldValue, Integer newValue) {
            listeners.forEach(new Consumer<IOBufferChangeListener>() {
                @Override
                public void accept(IOBufferChangeListener l) {
                    l.changed(index, oldValue, newValue);
                }
            });

        }
    };

    private final IOBufferValueChangeListener<Float> floatListener = new IOBufferValueChangeListener<Float>() {
        @Override
        public void changed(int index, Float oldValue, Float newValue) {
            listeners.forEach(new Consumer<IOBufferChangeListener>() {
                @Override
                public void accept(IOBufferChangeListener l) {
                    l.changed(index, oldValue, newValue);
                }
            });

        }
    };
    
    
    private final IOBufferValueChangeListener<Double> doubleListener = new IOBufferValueChangeListener<Double>() {
        @Override
        public void changed(int index, Double oldValue, Double newValue) {
            listeners.forEach(new Consumer<IOBufferChangeListener>() {
                @Override
                public void accept(IOBufferChangeListener l) {
                    l.changed(index, oldValue, newValue);
                }
            });

        }
    };
    
    private final IOBufferValueChangeListener<Long> longListener = new IOBufferValueChangeListener<Long>() {
        @Override
        public void changed(int index, Long oldValue, Long newValue) {
            listeners.forEach(new Consumer<IOBufferChangeListener>() {
                @Override
                public void accept(IOBufferChangeListener l) {
                    l.changed(index, oldValue, newValue);
                }
            });

        }
    };
    
    private final IOBufferValueChangeListener<Boolean> booleanListener = new IOBufferValueChangeListener<Boolean>() {
        @Override
        public void changed(int index, Boolean oldValue, Boolean newValue) {
            
            
            listeners.forEach(new Consumer<IOBufferChangeListener>() {
                @Override
                public void accept(IOBufferChangeListener l) {
                    l.changed(index, oldValue, newValue);
                }
            });

        }
    };

}
