package com.chainbuff.grpc;

import geyser.SubscribeUpdate;

public interface SubscribeDataCallback {
    void onData(SubscribeUpdate data);
}
