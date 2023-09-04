
# EasyCaptchaBoot

![MavenCentral](https://img.shields.io/maven-central/v/io.github.eternalstone/captcha-spring-boot-starter?style=flat-square)
![Hex.pm](https://img.shields.io/hexpm/l/plug.svg?style=flat-square)


## 1.简介
&emsp;基于[`EasyCaptcha`](https://gitee.com/ele-admin/EasyCaptcha)实现的Java图形验证码，支持gif、中文、算术等类型。解决了该项目存在的一些issues和问题，扩展了springboot-starter包，支持SpringBoot2.x和SpringBoot3.x，支持jdk8、jdk11、jdk17。

---



## 2.效果展示(与原项目效果一致)

![验证码](https://s2.ax1x.com/2019/08/23/msFrE8.png) 
&emsp;&emsp;
![验证码](https://s2.ax1x.com/2019/08/23/msF0DP.png)
&emsp;&emsp;
![验证码](https://s2.ax1x.com/2019/08/23/msFwut.png)
<br/>
![验证码](https://s2.ax1x.com/2019/08/23/msFzVK.gif) 
&emsp;&emsp;
![验证码](https://s2.ax1x.com/2019/08/23/msFvb6.gif)
&emsp;&emsp;
![验证码](https://s2.ax1x.com/2019/08/23/msFXK1.gif)

**算术类型：**

![验证码](https://s2.ax1x.com/2019/08/23/mskKPg.png)
&emsp;&emsp;
![验证码](https://s2.ax1x.com/2019/08/23/msknIS.png)
&emsp;&emsp;
![验证码](https://s2.ax1x.com/2019/08/23/mskma8.png)

**中文类型：**

![验证码](https://s2.ax1x.com/2019/08/23/mskcdK.png)
&emsp;&emsp;
![验证码](https://s2.ax1x.com/2019/08/23/msk6Z6.png)
&emsp;&emsp;
![验证码](https://s2.ax1x.com/2019/08/23/msksqx.png)

**内置字体：**

![验证码](https://s2.ax1x.com/2019/08/23/msAVSJ.png)
&emsp;&emsp;
![验证码](https://s2.ax1x.com/2019/08/23/msAAW4.png)
&emsp;&emsp;
![验证码](https://s2.ax1x.com/2019/08/23/msAkYF.png)

---



## 3.版本说明

- **2023-09-03**

  - v2.0.0支持jdk1.8、jdk11, 支持SpringBoot2.x的版本

  - v3.0.0支持jdk17, 支持SpringBoot3.x的版本

  - 不再支持servlet项目的使用，servlet项目移步至 [`EasyCaptcha`](https://gitee.com/ele-admin/EasyCaptcha)

  - `captcha-core`包剥离了`servlet-api`，不再与web层耦合，支持JavaSE的项目使用

  - 扩展了starter包，内置集成`/captcha`端点输出验证码，您无需再编写controller实现请求

  - 扩展了自定义配置背景颜色

  - 扩展了算术验证码算子长度

  - 使用单例解决了原项目存在的内存占用或内存溢出的问题，验证码数据不在绑定到captcha实例身上

    

- **兼容性测试结果(2023-09-03)**

  - 测试了jdk1.8， jdk11,  jdk17环境下的使用情况，使用v2.0.0可支持jdk1.8，使用v3.0.0支持jdk17

  - 测试了springboot2.x和springboot3.x的使用情况，v2.0.0可支持springboot2.x，使用v3.0.0支持springboot3.x

  - 在springboot2.6.0以后的版本，需要开启配置 `spring.mvc.pathmatch.matching-strategy=ant-path-matcher`，否则会报错：`java.lang.IllegalArgumentException: Expected lookupPath in request attribute "org.springframework.web.util.UrlPathHelper.PATH".`

  - 在docker环境下，若基础镜像缺失部分字体，会导致内置字体在中文验证码、算术验证码中无法正常显示

  - 内置扩展的字体可能在中文验证码中无法正常显示，建议使用默认字体

  - 少部分内置字体在算数验证码中运算符号无法正常显示

---



## 4.使用方法

### 4.1.在SpringBoot中使用

#### 4.1.1 导包

```xml
<!--SpringBoot2.x-->
<dependency>
    <groupId>io.github.eternalstone</groupId>
    <artifactId>captcha-spring-boot-starter</artifactId>
    <version>2.0.0</version>
</dependency>

<!--SpringBoot3.x-->
<dependency>
    <groupId>io.github.eternalstone</groupId>
    <artifactId>captcha-spring-boot-starter</artifactId>
    <version>3.0.0</version>
</dependency>
```

> 导入EasyCaptchaBoot的starer包后，Spring自动导入`EasyCaptchaAutoConfiguration`类，会自动导入一个/captcha的http端点，您可以直接在前端调用这个端点请求验证码，在springboot配置文件中也可以对验证码自定义

#### 4.1.2 配置

springboot.yaml配置

```yaml
easy-captcha:
  endpoint:
    #配置http端点，默认/captcha
    path: /captcha
    #是否启用端点生成
    enabled: true
  #验证码类性
  captcha: chinese
  #验证码位数
  length: 3
  #验证码宽度
  width: 130
  #验证码高度
  height: 48
  #验证码字符类性
  char-type: 2
  #验证码背景颜色
  background: "#000000"
  #验证码输出格式
  format: "png"
  #验证码字体，只能配置默认字体,默认字体见Captcha.Font_1等等
  font: 1
```



#### 4.1.3 使用

1. 使用默认导入的http端点，无需编写controller代码，直接在前端请求验证码即可

   ```html
   <img src="/captcha" width="130px" height="48px" />
   ```

   >  不要忘了把`/captcha`路径排除登录拦截，比如shiro的拦截。

2. 不使用默认导入，建议直接导入`captcha-core`包，而无需引入starter包，当然也可以配置`easy-captcha.endpoint.enabled=false` ，编写您自己的controller即可(可见SpringMVC的使用方式)



#### 4.1.4 如何验证验证码

使用内置的http端点，默认存储的验证码的位置是`HttpServletRequest.getSession`,该方式只适用单机环境下的应用，如果您的应用是分布式环境，请见 4.6的说明。

```java
@Controller
public class LoginController {
    
    //注入一个EasyCaptchaListener用于校验验证码
    @Resource
    private EasyCaptchaListener easyCaptchaListener;
    
    @PostMapping("/login")
    public String login(HttpServletRequest request,String verCode){
        boolean verify = easyCaptchaListener.verify(request, verCode);
        return verify? "success" :"fail";
    }   
}
```


### 4.2.在SpringMVC中使用

#### 4.2.1 导入cpatcha-core包

```xml
<!--jdk1.8, jdk11-->
<dependency>
    <groupId>io.github.eternalstone</groupId>
    <artifactId>captcha-core</artifactId>
    <version>2.0.0</version>
</dependency>

<!--jdk17-->
<dependency>
    <groupId>io.github.eternalstone</groupId>
    <artifactId>captcha-core</artifactId>
    <version>3.0.0</version>
</dependency>
```

#### 4.2.2 编写controller

```java
@Controller
public class CaptchaController {
    
    @RequestMapping("/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //通过验证码类型枚举获取一个验证码实例
        Captcha captcha = CaptchaFactory.getCaptcha(CaptchaEnum.SPEC);
        //使用实例创建一个随机码对象TextEntry
        TextEntry text = captcha.createText();
        //自定义验证码存储逻辑，单机一般存储session, 分布式下存储redis
        
        // 设置请求头为输出图片类型
        response.setContentType("image/gif");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        //将随机码通过实体写出成图片
        captcha.out(response.getOutputStream(), text);
    }
}
```
前端html代码：
```html
<img src="/captcha" width="130px" height="48px" />
```

> 不要忘了把`/captcha`路径排除登录拦截，比如shiro的拦截。



### 4.3.设置宽高和位数
```java
@Controller
public class CaptchaController {
    
    @RequestMapping("/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Captcha captcha = CaptchaFactory.getCaptcha(CaptchaEnum.SPEC);
        // 设置宽、高、位数
        captcha.setWidth(130);
        captcha.setHeight(48);
       	//不建议直接从Captcha实例上设置字体，如果您需要设置字体，有如下情况
      	//1.SpringBoot中直接配置文件设置
        //2.SpingMVC中通过Bean一个Captcha实例进行设置，保证Captcha实例的单例，并且字体仅只设置1次
    }
}
```



### 4.5分布式环境下的验证码存储

分布式项目建议不要存储在session中，存储在redis中，redis存储需要一个key，key一同返回给前端用于验证输入，这个时候在SpringBoot中使用内置端点时，就需要自己实现redis存储验证码的逻辑了：

需要编写一个配置类实现`EasyCaptchaListener`接口，实现其中的一些关键方法即可

```java
@Configuration
public class CaptchaListener implements EasyCaptchaListener {

    @Resource
    private RedisUtil redisUtil;

    @Override
    public void output(HttpServletResponse response, Captcha captcha, TextEntry entry) throws IOException {
        System.out.println("生成的验证码字符" + entry.charsText());
        System.out.println("生成的验证码答案" + entry.getKey());

        String key = UUID.randomUUID().toString();
        CaptchaVO captchaVO = new CaptchaVO();
        captchaVO.setUuid(key);
        captchaVO.setImage(captcha.toBase64(entry));

        redisUtil.setEx(key, entry.getKey(), 30, TimeUnit.MINUTES);
        response.setHeader("content-type", "text/html;charset=UTF-8");
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(JSONObject.toJSONString(captchaVO).getBytes());
        outputStream.flush();
    }

    @Override
    public boolean verify(HttpServletRequest request, String code) {
        String key = request.getParameter("key");
        System.out.println("前端传入的key" + key);
        System.out.println("前端输入的验证码" + code);
        String redisCode = redisUtil.get(key);
        // 判断验证码
        return redisCode != null && redisCode.equals(code);
    }
}
```



在controller中验证，同4.1.4

```java
@Controller
public class LoginController {
    
    //注入一个EasyCaptchaListener用于校验验证码
    @Resource
    private EasyCaptchaListener easyCaptchaListener;
    
    @PostMapping("/login")
    public String login(HttpServletRequest request,String key, String verCode){
        boolean verify = easyCaptchaListener.verify(request, verCode);
        return verify? "success" :"fail";
    }   
}
```



前端使用ajax获取验证码：

```html
<img id="verImg" width="130px" height="48px"/>

<script>
    var verKey;
    // 获取验证码
    $.get('/captcha', function(res) {
        verKey = res.key;
        $('#verImg').attr('src', res.image);
    },'json');
    
    // 登录
    $.post('/login', {
        key: verKey,
        verCode: '8u6h',
        username: 'admin'，
        password: 'admin'
    }, function(res) {
        console.log(res);
    }, 'json');
</script>
```



## 5.更多设置

### 5.1.验证码类型

Captcha类型枚举包含几种类型如下：

| 枚举                    | 类型              |
| ----------------------- | ----------------- |
| CaptchaEnum.SPEC        | 普通字符验证码    |
| CaptchaEnum.GIF         | GIF动图验证码     |
| CaptchaEnum.CHINESE     | 中文验证码        |
| CaptchaEnum.CHINESE_GIF | 中文GIF动图验证码 |
| CaptchaEnum.ARITHMETIC  | 算术验证码        |



```java
public class Test {
    
    public static void main(String[] args) {
        //创建一个验证码配置类
        CaptchaProperty property = new CaptchaProperty();
        //配置验证码类性枚举
        property.setCaptcha(CaptchaEnum.SPEC);
        //配置验证码宽度
        property.setWidth(130);
        //配置验证码高度
        property.setHeight(48);
        //配置验证码几个字符
        property.setLength(4);
        //配置内置字体
        property.setFont(Captcha.FONT_2);
        //配置验证码字符类性
        property.setCharType(Captcha.TYPE_DEFAULT);
        //配置验证码背景颜色
        property.setBackground(Color.BLACK);
        //创建验证码实例
        Captcha captcha = CaptchaFactory.getCaptcha(property);
        //获取验证码内容
        TextEntry text = captcha.createText();
        //获取随机验证码内容
        String text = entry.charsText();
        //获取随机验证码校验值
        String key = entry.getKey();
    }
}
```

> 注意：<br/>
> &emsp;算术验证码的len表示是几位数运算，而其他验证码的len表示验证码的位数，算术验证码的TextEntry.charsText()表示的是公式字符，TextEntry.getKey()表示的是公式运算结果。
> 对于算术验证码，你应该把公式的结果存储session，而不是公式。

### 5.2.验证码字符类型

 类型 | 描述 
 :--- | :--- 
 TYPE_DEFAULT | 数字和字母混合 
 TYPE_ONLY_NUMBER | 纯数字
 TYPE_ONLY_CHAR | 纯字母 
 TYPE_ONLY_UPPER | 纯大写字母
 TYPE_ONLY_LOWER | 纯小写字母
 TYPE_NUM_AND_UPPER | 数字和大写字母

> 只有`SpecCaptcha`和`GifCaptcha`设置才有效果。

### 5.3.字体设置
内置字体：

 字体 | 效果 
 :--- | :--- 
 Captcha.FONT_1 |  ![](https://s2.ax1x.com/2019/08/23/msMe6U.png)
 Captcha.FONT_2 | ![](https://s2.ax1x.com/2019/08/23/msMAf0.png)
 Captcha.FONT_3 |  ![](https://s2.ax1x.com/2019/08/23/msMCwj.png)
 Captcha.FONT_4 | ![](https://s2.ax1x.com/2019/08/23/msM9mQ.png)
 Captcha.FONT_5 | ![](https://s2.ax1x.com/2019/08/23/msKz6S.png)
 Captcha.FONT_6 | ![](https://s2.ax1x.com/2019/08/23/msKxl8.png)
 Captcha.FONT_7 | ![](https://s2.ax1x.com/2019/08/23/msMPTs.png)
 Captcha.FONT_8 | ![](https://s2.ax1x.com/2019/08/23/msMmXF.png)
 Captcha.FONT_9 | ![](https://s2.ax1x.com/2019/08/23/msMVpV.png)
 Captcha.FONT_10 | ![](https://s2.ax1x.com/2019/08/23/msMZlT.png)

使用方法：
```
CaptchaProperty property = new CaptchaProperty();
//配置验证码类性枚举
property.setCaptcha(CaptchaEnum.SPEC);

// 设置内置字体
property.setFont(Captcha.FONT_1); 

// 设置系统字体
property.setFont(new Font("楷体", Font.PLAIN, 28));

//创建验证码实例对象
Captcha captcha = CaptchaFactory.getCaptcha(property);
```

### 5.4.输出base64编码
```
Captcha captcha = CaptchaFactory.getCaptcha(property);
TextEntry entry = captcha.createText();
String base64 = captcha.toBase64(entry);
```

### 5.5.输出到文件
```
FileOutputStream outputStream = new FileOutputStream(new File("C:/captcha.png"))
Captcha captcha = CaptchaFactory.getCaptcha(property);
TextEntry entry = captcha.createText();
captcha.out(outputStream);
```


---



## 6.自定义效果

&emsp;继承`Captcha`实现`out`， `createText`方等方法，中文验证码可继承`ChineseCaptchaAbstract`，算术验证码可继承`ArithmeticCaptchaAbstract`。

---



## 7.开发分支说明

- master为2.x.x版本的发行主分支，支持SpringBoot2.x的版本
- v3为3.x.x版本的发行主分支, 支持SpringBoot3.x的版本
- 欢迎大家提交issues和push

---

