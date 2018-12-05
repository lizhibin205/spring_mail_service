# spring_mail_service
使用JavaMail实现邮件的发送

# quick start
在开始之前，需要配置好JDK1.8 + maven环境

## 1. 配置项
app.properties

|参数名称|说明|
|:-|:-|
|mail.default.host|smtp.qq.com|
|mail.default.port|587|
|mail.default.username|xxxxxxxxx@qq.com|
|mail.default.password|xxxxxxxxxxxxxxxx|
|mail.default.sendFrom|xxxxxxxxx@qq.com|


## 2. build
执行命令mvn clean package，编译成功后，会在target目录下生成mail_service.war文件，上传到tomcat服务后即可运行。

## 3. API
### 1) sendMail
URL:/mail

Method:POST

|参数名称|参数类型|说明|
|:-|:-|:-|
|sendTo|String[]|邮件接收者列表|
|subject|String|邮件标题|
|text|String|邮件正文|
