# 项目说明 #

打包: mvn package 打包成功后在target目录下会生成wechat-api-1.0.jar包

运行：java -jar ${jarPath} -Dspring.profiles.active=${env}


- jarPath: jar包的位置

- env：可选值如下：dev,stg,product 默认为dev


    
# 初始化配置 #

   将工程中db目录下的db.sql导入到对应的mysql数据，注意这里需要导入到数据库

# 测试说明 #

- http://localhost:8080/users/projection/1 测试对id为1的用户数据筛选
- http://localhost:8080/users/1 测试读取id为1的用户信息
- http://localhost:8080/testPage 测试页面

