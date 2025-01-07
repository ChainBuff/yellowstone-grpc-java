# Solana Yellowstone gRPC java 示例项目

## 项目概述

本项目提供了使用 java 接入 Solana Yellowstone gRPC 服务的示例代码。其中[solana-grpc-java](solana-grpc-java)中的代码是使用protoc根据[proto](proto)中的.proto文件生成，生成的具体过程本项目不再讨论，直接下载本项目打包引用到自己项目即可。
## 环境要求
- java 17+
- maven
- Git

## 快速开始

### 1. 克隆项目&编译代码

```bash
git clone https://github.com/ChainBuff/yellowstone-grpc-java.git
cd yellowstone-grpc-java
mvn clean install
```

### 2. 安装依赖包
通过第一步 mvn clean install命令执行成功之后，solana-grpc-java 会打包安装到本地仓库，如需在自己项目中使用引入以下依赖即可。

```xml
<dependency>
   <groupId>com.chainbuff</groupId>
   <artifactId>solana-grpc-java</artifactId>
   <version>1.0.0</version>
</dependency>
```

## 项目结构
 yellowstone-grpc-java
   - example //示例代码在这里
     - [SubscriptionClient.java](example%2Fsrc%2Fmain%2Fjava%2Fcom%2Fchainbuff%2Fgrpc%2FSubscriptionClient.java)此代码是一个订阅客户端示例，可以参考此代码实现自己的订阅客户端。
   - proto   //yellowstone 的 proto文件，本项目不依赖此文件，只做备份使用
   - solana-grpc-java //yellowstone grpc java代码，使用protoc和java相关插件生成，本项目不做详细解释；编译打包直接使用即可。

### [00-订阅Transaction数据并解析](example%2Fsrc%2Fmain%2Fjava%2Fcom%2Fchainbuff%2Fexamples%2Fsubtrans%2FREADME.md)
- 交易数据订阅的基础实现
- 演示如何建立 gRPC 连接
- 解析交易数据

### 01-PUMP交易价格解析示例(待完善)(example%2Fsrc%2Fmain%2Fjava%2Fcom%2Fchainbuff%2Fexamples%2Fpumpprice%2FREADME.md)

## 关键特性

- 使用配置文件管理连接参数
- 支持多种订阅过滤器
- 实时数据推送
- 心跳保活机制
- 异步处理


## 参考资源

- [Solana 开发文档](https://docs.solana.com/)
- [Yellowstone-grpc 文档](https://docs.helius.dev/yellowstone-grpc/getting-started)
- [gRPC 官方文档](https://grpc.io/docs/)