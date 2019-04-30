## 当前版本：v0.0.1
## 框架
Spring Boot,Mybatis,Maven,Shiro
##运行方式（服务器）
- 使用Maven中的package命令打包成war包，导入服务器

- 服务器中导入doc中的sql文件

- （前台运行）执行命令 

  ```
   java -jar XXXX.war
  ```

- （后台运行）执行命令 

  ```
  java -jar XXXX.war &
  ```

- （切换为守护进程持续运行）执行命令

  ```
  nohup java -jar XXXX.war &
  ```

  