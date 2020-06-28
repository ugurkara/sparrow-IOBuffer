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
public abstract class NumberIOBuffer<T extends Number> extends BaseIOBuffer<T> {

    private int byteOrder = 0;

    public NumberIOBuffer(int size) {
        super(size);
    }

    public int byteOrder() {
        return byteOrder;
    }

    public void byteOrder(int byteOrder) {
        this.byteOrder = byteOrder;
    }

    

}
