# Sparrow-IOBuffer
``` java
        Scheduler.schedulePerXSecond(new Runnable() {
            @Override
            public void run() {
                System.out.println("per 10 sec. Initial wait is 20 sec!");
            }
        }, 10, 20);
```
