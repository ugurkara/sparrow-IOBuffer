# Sparrow-IOBuffer

Sparrow-IOBuffer provides a mechanism simplify use of the ByteBuffer. 

``` java

        IOBuffer ioBuffer = IOBuffer.allocate(128);
        ioBuffer.addListener(new IOBufferChangeAdapter() {
            @Override
            public void changed(int index, Double oldValue, Double newValue) {
                System.out.println(index + " .Double value changed from +" + oldValue + " to " + newValue);
            }
        });

        DoubleIOBuffer doubleIOBuffer = ioBuffer.doubleBuffer();
        
        for (int i = 0; i < doubleIOBuffer.getSize(); i++) {
            doubleIOBuffer.setValue(i, Math.random()*1000);
        }
        
```
