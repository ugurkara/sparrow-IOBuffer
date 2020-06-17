# Sparrow-IOBuffer

Sparrow-IOBuffer provides a mechanism simplify use of the ByteBuffer. 

``` java


        Random random = new Random();

        IOBuffer ioBuffer = IOBuffer.allocate(128);
        ioBuffer.addListener(new IOBufferChangeAdapter() {
            @Override
            public void changed(int index, Double oldValue, Double newValue) {
                System.out.println(index + " .Double value changed from +" + oldValue + " to " + newValue);
            }

            @Override
            public void changed(int index, Float oldValue, Float newValue) {
                System.out.println(index + " .Float value changed from +" + oldValue + " to " + newValue);
            }

            @Override
            public void changed(int index, Integer oldValue, Integer newValue) {
                System.out.println(index + " .Integer value changed from +" + oldValue + " to " + newValue);
            }

        });

        DoubleIOBuffer doubleIOBuffer = ioBuffer.doubleBuffer();

        for (int i = 0; i < doubleIOBuffer.getSize(); i++) {
            doubleIOBuffer.setValue(i, random.nextDouble());
        }

        FloatIOBuffer floatIOBuffer = ioBuffer.floatBuffer();

        for (int i = 0; i < floatIOBuffer.getSize(); i++) {
            floatIOBuffer.setValue(i, random.nextFloat());
        }

        IntegerIOBuffer integerIOBuffer = ioBuffer.integerBuffer();
        integerIOBuffer.addListener(new IOBufferValueChangeListener<Integer>() {
            @Override
            public void changed(int index, Integer oldValue, Integer newValue) {
                System.out.println(index + " .Integer value changed from +" + oldValue + " to " + newValue);
            }
        });

        for (int i = 0; i < integerIOBuffer.getSize(); i++) {
            integerIOBuffer.setValue(i, random.nextInt());
        }

        //Copy data to IOBuffer from another source.
        ByteBuffer source = ByteBuffer.allocate(128);

        for (int i = 0; i < source.limit() / 4; i++) {
            source.putInt(i * 4, random.nextInt());
        }
        
        ioBuffer.put(source, 0);

//        ioBuffer.shortBuffer();
//        ioBuffer.unsignedShortBuffer();
//        ioBuffer.longBuffer();
//        ioBuffer.booleanBuffer();
//        ioBuffer.byteBuffer();
        
```
