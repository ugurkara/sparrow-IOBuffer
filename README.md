# Sparrow-IOBuffer

Sparrow-IOBuffer provides a mechanism simplify use of the ByteBuffer. 

``` java


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
        
        for (int i = 0; i < integerIOBuffer.getSize(); i++) {
            integerIOBuffer.setValue(i, random.nextInt());
        }
        
//        ioBuffer.shortBuffer();
//        ioBuffer.unsignedShortBuffer();
//        ioBuffer.longBuffer();
//        ioBuffer.booleanBuffer();
//        ioBuffer.byteBuffer();

        
```
