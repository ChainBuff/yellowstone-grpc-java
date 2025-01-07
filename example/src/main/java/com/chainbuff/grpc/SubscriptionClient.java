package com.chainbuff.grpc;

import geyser.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class SubscriptionClient {
    private final ManagedChannel channel;
    private final GeyserGrpc.GeyserStub stub;
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private final AtomicInteger retryCount = new AtomicInteger(0);

    public SubscriptionClient(String target) {
        this(ManagedChannelBuilder.forTarget(target).build());
    }

    SubscriptionClient(ManagedChannel channel) {
        this.channel = channel;
        this.stub = GeyserGrpc.newStub(channel);
    }

    public void shutdown() {
        if (channel != null) {
            channel.shutdown();
            try {
                if (!channel.awaitTermination(5, TimeUnit.SECONDS)) {
                    channel.shutdownNow();
                }
            } catch (InterruptedException e) {
                channel.shutdownNow();
            }
        }
        scheduler.shutdown();
        System.out.println("Shutting down");
    }

    public void subscribe(SubscribeRequest request,SubscribeDataCallback callback) {
         StreamObserver<SubscribeUpdate>  responseObserver = new StreamObserver<>() {
            @Override
            public void onNext(SubscribeUpdate data) {
                if (retryCount.get()> 0){
                    retryCount.set(0);
                }
                if (callback != null){
                    callback.onData(data);
                }
            }

            @Override
            public void onError(Throwable t) {
                System.err.println("Subscription error: " + t.getMessage());
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                //重试5次
                if (retryCount.get() < 5){
                    retryCount.incrementAndGet();
                    subscribe(request,callback);
                }else {
                    shutdown();
                }
            }

            @Override
            public void onCompleted() {
                System.out.println("Subscription completed");
            }
        };

        StreamObserver<SubscribeRequest> subscribe = this.stub.subscribe(responseObserver);
        subscribe.onNext(request);

        //定时发送ping保命
        this.scheduler.scheduleAtFixedRate(() -> {
            SubscribeRequest.Builder builder = SubscribeRequest.newBuilder();
            builder.setCommitment(CommitmentLevel.CONFIRMED);
            builder.setPing(SubscribeRequestPing.newBuilder().setId(1).build());
            subscribe.onNext(builder.build());
        }, 5, 5, TimeUnit.SECONDS);
    }
}