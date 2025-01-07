package com.chainbuff.examples.subtrans;

import com.chainbuff.grpc.Base58;
import com.chainbuff.grpc.SubscribeDataCallback;
import com.chainbuff.grpc.SubscriptionClient;
import geyser.CommitmentLevel;
import geyser.SubscribeRequest;
import geyser.SubscribeRequestFilterTransactions;
import geyser.SubscribeUpdate;

public class SubTest {

    public static void main(String[] args) {
        SubscriptionClient client = new SubscriptionClient("test-grpc.chainbuff.com:443");
        try {
            SubscribeRequest.Builder builder = SubscribeRequest.newBuilder();
            builder.setCommitment(CommitmentLevel.CONFIRMED);
            //构建订阅请求
            builder.putTransactions("transactions_sub",
                    SubscribeRequestFilterTransactions.newBuilder()
                    .addAccountInclude("6EF8rrecthR5Dkzon8Nwu78hRvfCKubJ14M5uBEwF6P")// 监听pump交易
                    .build());
            SubscribeRequest subscribeRequest = builder.build();
            //发送订阅请求
            client.subscribe(subscribeRequest, new SubscribeDataCallback() {
                @Override
                public void onData(SubscribeUpdate data) {
                    if (data.hasTransaction()){
                        String signature = Base58.encode(data.getTransaction().getTransaction().getSignature().toByteArray());
                        System.out.println(signature);
                        System.out.println(data.getTransaction());
                    }

                }
            });

            Thread.sleep(Integer.MAX_VALUE); //阻塞线程，等待接收数据，如果不阻塞程序会立马结束，实际项目中建议单起一个线程管理。
        }catch (InterruptedException e)  {
           e.printStackTrace();
        }finally{
            client.shutdown();
        }
    }
}
