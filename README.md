J2Cache —— 基于 Ehcache 和 Redis 实现的两级 Java 缓存框架
===============

拷贝的红薯的框架(我是fork 了一份,但是好像抄错人了,上本家重新抄了一份)
* 本家地址:https://git.oschina.net/ld/J2Cache

## 修改点如下:
修改成标准 mvn 结构
去掉了hibernate
增加 null 的缓存
* 因公司情况特殊,有些数据请求过来得到的真实值就是null 如果不支持null缓存,相当于缓存击穿
增加 基于ConcurrentHashMap的 缓存 
* 一级缓存增加基于map的cache
* 优点,map吞吐性能优于ehcache
* 因为个人公司业务需要,服务器平均访问200次/s 每次最多需要查询1500+次缓存 但是缓存的数据量只有2W-
* 所以没哟考虑缓存淘汰策略

