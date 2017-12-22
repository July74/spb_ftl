# spb_ftl
### 技术框架
- 后台框架：springboot + mybatis
- 缓存：redis
- js模板引擎：freemarker
- 前端：element ui + vue

### 系统介绍
- 防XSS,CSRF
- 登录验证

### 运行
##### 本地运行

- 修改配置文件，数据库，redis
- 运行主类Application

##### linux服务端运行
- 本地打包，传到服务器
- bin/deploy.sh 可直接运行，有配置相关日志路径

### 测试
```
http://localhost:8088/user/login
```
用到redis，未登录不可访问系统
```
http://localhost:8088/hello
```
没用数据库，直接在controller返回

```
http://localhost:8088/user/list
```
用到数据库，查询用户列表




