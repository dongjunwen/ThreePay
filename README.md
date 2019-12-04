# ThreePay 三人行支付系统
```
支付模块
https://travis-ci.org/
https://coveralls.io/
```

## 代码结构	
 模块代码 | 模块名称 | 备注
---|---|---
payment-common |	公共依赖包|                                   
payment-jdbc 	|数据库连接包  |                                
payment-channel	|和第三方通道打交道的依赖包    |                  
payment-service|	核心业务依赖包    |                            
payment-api		|核心业务对外暴露api公共包，为拆分为rpc项目做准备  |
payment-rest 	|对外暴露统一http接口可运行项目jar包      |     
payment-web		|主要是演示页面   |                                    
   
   
## 二、打包命令
```
 1.进入 payment-rest 目录
 2.执行 mvn clean install -Dmaven.test.skip=true
 3.在target目录下能看到 payment-rest-1.0-SNAPSHOT.jar
 4.运行 nohup java -jar payment-rest-1.0-SNAPSHOT.jar >>nohup-rest.out &
 5.打开 http://127.0.0.1:9005//swagger-ui.html 就能看到调试页面
```

