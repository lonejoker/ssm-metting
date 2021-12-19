# 创建工程

- 创建一个普通的maven工程
    - ![](\image-20211217153836127.png)
- 修改打包方式为war包
- 配置web
    - ![](\image-20211217154049318.png)

# 整合ss

- 只需要添加一个依赖`webmvc`和`servlet`即可

    - ```xml
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-webmvc</artifactId>
            <version>5.0.5.RELEASE</version>
        </dependency>
        ```

- 创建对应的包

    - ![](\image-20211217154418606.png)

- 创建spring配置文件`applicationContext`

    - ```xml
        <!-- 1、配置扫描包 -->
        <context:component-scan base-package="com.xiaobai" use-default-filters="true">
        	<context:exclude-filter type="annotation" expression="org.springframework.stereotype.Controller"/>
        </context:component-scan>
        ```

- 创建springmvc配置文件`spring-servlet`

    - ```xml
        <!-- 1、配置扫描包 -->
        <context:component-scan base-package="com.xiaobai" use-default-filters="false">
            <context:include-filter type="annotation" expression="org.springframework.stereotype.Controller"/>
        	</context:component-scan>
        <mvc:annotation-driven/>
        ```

- web配置文件加载spring和springmvc

    - ```xml
        	<!-- 加载配置文件 -->
        	<context-param>
        		<param-name>contextConfigLocation</param-name>
        		<param-value>classpath:applicationContext.xml</param-value>
        	</context-param>
        	<!-- 监听器 -->
        	<listener>
        		<listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
        	</listener>
        	<!-- 前端控制器 -->
        	<servlet>
        		<servlet-name>springmvc</servlet-name>
        		<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        		<init-param>
        			<param-name>contextConfigLocation</param-name>
        			<param-value>classpath:spring-servlet.xml</param-value>
        		</init-param>
        	</servlet>
        	<!-- 映射路径 -->
        	<servlet-mapping>
        		<servlet-name>springmvc</servlet-name>
        		<url-pattern>/</url-pattern>
        	</servlet-mapping>
        	<!-- 配置springMVC的编码过滤器 -->
        	<filter>
        		<filter-name>CharacterEncodingFilter</filter-name>
        		<filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
        		<init-param>
        			<param-name>encoding</param-name>
        			<param-value>UTF-8</param-value>
        		</init-param>
        		<init-param>
        			<param-name>forceRequestEncoding</param-name>
        			<param-value>true</param-value>
        		</init-param>
        		<init-param>
        			<param-name>forceResponseEncoding</param-name>
        			<param-value>true</param-value>
        		</init-param>
        	</filter>
        	<filter-mapping>
        		<filter-name>CharacterEncodingFilter</filter-name>
        		<url-pattern>/*</url-pattern>
        	</filter-mapping>
        ```

- 测试ss整合

    - ```java
        @RestController
        public class HelloController {
        
            @GetMapping("/hello")
            public String hello(){
                return "hello";
            }
        
        }
        ```


# 整合mybatis

- 添加依赖

    - ```xml
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-jdbc</artifactId>
            <version>5.3.9</version>
        </dependency>
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid</artifactId>
            <version>1.1.10</version>
        </dependency>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.16</version>
        </dependency>
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
            <version>3.5.5</version>
        </dependency>
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis-spring</artifactId>
            <version>2.0.5</version>
        </dependency>
        ```

- 配置数据文件`jdbc.properties`

    - ```properties
        jdbc.driverClassName=com.mysql.jdbc.Driver
        jdbc.url=jdbc:mysql://localhost:3306/meeting?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=UTC
        jdbc.username=root
        jdbc.password=root
        ```

- 在spring里配置jdbc

    - ```xml
        <!-- 2、配置jdbc -->
        <context:property-placeholder location="classpath:jdbc.properties"></context:property-placeholder>
        <bean class="com.alibaba.druid.pool.DruidDataSource" id="dataSource">
            <property name="driverClassName" value="${jdbc.driverClassName}"></property>
            <property name="url" value="${jdbc.url}"></property>
            <property name="username" value="${jdbc.username}"></property>
            <property name="password" value="${jdbc.password}"></property>
        </bean>
        ```

- 配置mybatis

    - ```xml
        <bean class="org.mybatis.spring.SqlSessionFactoryBean" id="sqlSessionFactoryBean">
            <property name="dataSource" ref="dataSource"/>
            <property name="typeAliasesPackage" value="com.xiaobai.entity"></property>
            <property name="mapperLocations">
                <value>
                    classpath*:com/xiaobai/mapper/*.xml
                </value>
            </property>
        </bean>
        <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer" id="mapperScannerConfigurer">
            <property name="sqlSessionFactoryBeanName" value="sqlSessionFactoryBean"></property>
            <property name="basePackage" value="com.xiaobai.mapper"></property>
        </bean>
        ```

- 编译时会将mapper下的xml文件忽略，保留resources下的文件，需要在pom添加以下内容

    - ```xml
        <build>
            <resources>
                <resource>
                    <directory>src/main/java</directory>
                    <includes>
                        <include>**/*.xml</include>
                    </includes>
                </resource>
                <resource>
                    <directory>src/main/resources</directory>
                </resource>
            </resources>
        </build>
        ```

- 测试

    - ```java
        DepartmentController.java
        
        @RestController
        public class DepartmentController {
        
            @Autowired
            private DepartmentService departmentService;
        
            @GetMapping("/dept")
            public void getDepartById(Integer id) {
                Department department = departmentService.getDeptById(id);
                System.out.println(department);
            }
        
        }
        ```

    - ```java
        DepartmentService.java
        
        @Service
        public class DepartmentService {
        
            @Autowired
            private DepartmentMapper departmentMapper;
        
            public Department getDeptById(Integer id) {
                return departmentMapper.getDeptById(id);
            }
        }
        ```

    - ```java
        DepartmentMapper.java
        
        public interface DepartmentMapper {
            Department getDeptById(Integer id);
        }
        ```

    - ```xml
        DepartmentMapper.xml
        
        <?xml version="1.0" encoding="UTF-8" ?>
        <!DOCTYPE mapper
        		PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        		"http://mybatis.org/dtd/mybatis-3-mapper.dtd">
        <mapper namespace="com.xiaobai.mapper.DepartmentMapper">
        
        <!-- 查询 -->
        	<select id="getDeptById" resultType="com.xiaobai.entity.Department">
        		select * from department where departmentid = #{id};
        	</select>
        
        </mapper>
        ```

- 配置事务

    - ```xml
        	<!-- 事务配置 -->
        	<bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        		<property name="dataSource" ref="dataSource"/>
        	</bean>
        	<tx:advice id="txAdvice" transaction-manager="transactionManager">
        		<tx:attributes>
        			<tx:method name="add*"/>
        			<tx:method name="update*"/>
        			<tx:method name="delete*"/>
        			<tx:method name="insert*"/>
        		</tx:attributes>
        	</tx:advice>
        	<!-- 配置切面切点 -->
        	<!--配置事务的aop织入-->
        	<aop:config>
        		<aop:pointcut id="txPointcut" expression="execution(* com.xiaobai.service.*.*(..))"/>
        		<aop:advisor advice-ref="txAdvice" pointcut-ref="txPointcut"/>
        	</aop:config>
        ```

    - 添加依赖

        - ```xml
            <dependency>
                <groupId>org.aspectj</groupId>
                <artifactId>aspectjweaver</artifactId>
                <version>1.9.6</version>
                <scope>runtime</scope>
            </dependency>
            <dependency>
                <groupId>org.aspectj</groupId>
                <artifactId>aspectjrt</artifactId>
                <version>1.9.6</version>
            </dependency>
            ```

# 整合freemarker

- 添加依赖

    - ```xml
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context-support</artifactId>
            <version>5.2.7.RELEASE</version>
        </dependency>
        <dependency>
            <groupId>org.freemarker</groupId>
            <artifactId>freemarker</artifactId>
            <version>2.3.28</version>
        </dependency>
        ```

- mvc文件配置freemarker

    - ```xml
        <!-- 配置模板基本属性 -->
        <bean class="org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer" id="freeMarkerConfigurer">
            <!-- 模板文件位置 -->
            <property name="templateLoaderPath" value="/WEB-INF/ftl/"/>
            <!-- 默认编码格式 -->
            <property name="defaultEncoding" value="UTF-8" />
            <!-- 全局变量设置 -->
            <property name="freemarkerVariables">
                <map>
                    <entry key="root" value="${root}" />
                </map>
            </property>
            <!-- 基本格式配置：时间格式、数字格式 -->
            <property name="freemarkerSettings">
                <props>
                    <!-- 模版的缓存时间，单位是s，超过缓存时间则从磁盘加载最新的模版 -->
                    <prop key="template_update_delay">10</prop>
                    <!-- 设置默认地区，主要影响数字、日期输出格式，request中没有指定地区时模板查找的值 -->
                    <prop key="locale">zh_CN</prop>
                    <!-- 设置日期时间的输出格式 -->
                    <prop key="datetime_format">yyyy-MM-dd HH:mm:ss </prop>
                    <!-- 设置日期的输出格式 -->
                    <prop key="date_format">yyyy-MM-dd</prop>
                    <!-- 设置时间的输出格式 -->
                    <prop key="time_format">HH:mm:ss</prop>
                    <!-- 设置数字的输出格式 -->
                    <prop key="number_format">#.####</prop>
                </props>
            </property>
        </bean>
        
        <!-- freemarker视图解析器配置 -->
        <bean class="org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver">
            <!-- 视图解析器类 -->
            <property name="viewClass" value="org.springframework.web.servlet.view.freemarker.FreeMarkerView" />
            <!-- 后缀配置 -->
            <property name="suffix" value=".ftl"/>
            <!-- 设置各种属性覆盖 -->
            <!-- 允许重写Request属性 -->
            <property name="allowRequestOverride" value="true"/>
            <!-- 允许重写Session属性 -->
            <property name="allowSessionOverride" value="true"/>
            <!-- 设置request Attribute添加到模型 -->
            <property name="exposeRequestAttributes" value="true"/>
            <!-- 设置session Attribute添加到模型 -->
            <property name="exposeSessionAttributes" value="true"/>
            <!-- 页面内容类型 -->
            <property name="contentType" value="text/html;charset=utf-8"/>
        </bean>
        ```

# 登录

- 导入资源文件并在mvc配置文件中放行`<mvc:resources mapping="/**" location="/"/>`

- 编写javabean

    - ```java
        public class Employee {
        
            private Integer employeeid;
            private String employeename;
            private String username;
            private String phone;
            private String email;
            private Integer status;
            private Integer departmentid;
            private String password;
            private Integer role;
            
        }
        ```

- 然后分别创建`EmployeeMapper.java`、`EmployeeMapper.xml`、`EmployeeService.java`

    - ```java
        public interface EmployeeMapper {
            Employee loadEmpByUsername(String username);
        }
        ```

    - ```xml
        <?xml version="1.0" encoding="UTF-8" ?>
        <!DOCTYPE mapper
        		PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        		"http://mybatis.org/dtd/mybatis-3-mapper.dtd">
        
        <mapper namespace="com.xiaobai.mapper.EmployeeMapper">
        
        	<select id="loadEmpByUsername" resultType="com.xiaobai.entity.Employee">
        		select * from employee where username = #{username};
        	</select>
        
        </mapper>
        ```

    - ```java
        @Service
        public class EmployeeService {
        
            @Autowired
            private EmployeeMapper employeeMapper;
        
           public Employee doLogin(String username, String password){
                Employee employee = employeeMapper.loadEmpByUsername(username);
                if (employee==null|| !employee.getPassword().equals(password)){
                    return null;
                }
                return employee;
           }
        }
        ```

    - ```java
        @Controller
        public class LoginController {
        
            @Autowired
            private EmployeeService employeeService;
        
            @RequestMapping("/")
            public String login() {
                return "login";
            }
        
            @PostMapping("/doLogin")
            public String doLogin(String username, String password, Model model) {
                Employee employee = employeeService.doLogin(username, password);
                if (employee == null) {
                    model.addAttribute("error", "用户名或者密码错误，登录失败");
                    return "forward:/";
                } else {
                    if (employee.getStatus() == 0) {
                        model.addAttribute("error", "用户待审批");
                        return "forward:/";
                    } else if (employee.getStatus() == 2) {
                        model.addAttribute("error", "用户审批未通过");
                        return "forward:/";
                    } else {
                        return "redirect:/notifications";
                    }
                }
            }
        }
        ```

- 修改登录页面

    - ```html
                        <form action="/doLogin" method="post">
                            <fieldset>
                                <legend>登录信息</legend>
                                <table class="formtable" style="width:50%">
                                    <tr>
                                        <td>账号名:</td>
                                        <td>
                                            <input name="username" id="accountname" type="text" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>密码:</td>
                                        <td>
                                            <input name="password" id="new" type="password" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2" class="command">
                                            <input type="submit" value="登录" class="clickbutton" onclick="window.location.href='notifiactions.html';"/>
                                            <input type="button" value="返回" class="clickbutton" onclick="window.history.back();"/>
                                        </td>
                                    </tr>
                                </table>
                                <div style="color: red">${error!''}</div>
                            </fieldset>
                        </form>
        ```

- 存放session

    - ```java
            @PostMapping("/doLogin")
            public String doLogin(....., HttpSession session) {
                .......
                    } else {
                        session.setAttribute("usersession",employee);
                        return "redirect:/notifications";
                    }
                }
            }
        ```

- 创建NotificationsController

    - ```java
        @Controller
        public class NotificationsController {
        
            @RequestMapping("/notifications")
            public String getNotifications(){
                return "notifications";
            }
        
        }
        ```

# 提取头部

```html
top.ftl

		<div class="page-header">
            <div class="header-banner">
                <img src="images/header.png" alt="CoolMeeting"/>
            </div>
            <div class="header-title">
                欢迎访问Cool-Meeting会议管理系统
            </div>
            <div class="header-quicklink">
                欢迎您
                <#if usersession??>
                    <strong>，${usersession.employeename!''}</strong>
                </#if>
                <a href="changepassword.html">[修改密码]</a>
            </div>
        </div>

只需要在用到的地方引入：<#include './top.ftl'>
```

# 提取左侧菜单

```html
<div class="page-sidebar">
    <div class="sidebar-menugroup">
        <div class="sidebar-grouptitle">个人中心</div>
        <ul class="sidebar-menu">
            <li class="sidebar-menuitem"><a href="/notifications">最新通知</a></li>
            <li class="sidebar-menuitem active"><a href="/mybookings">我的预定</a></li>
        </ul>
    </div>
    <div class="sidebar-menugroup">
        <div class="sidebar-grouptitle">人员管理</div>
        <ul class="sidebar-menu">
            <li class="sidebar-menuitem"><a href="/register">员工注册</a></li>
            <#if usersession?? && (usersession.role == 1)>
                <li class="sidebar-menuitem"><a href="/admin/departments">部门管理</a></li>
                <li class="sidebar-menuitem"><a href="/admin/approveaccount">注册审批</a></li>
                <li class="sidebar-menuitem"><a href="/admin/searchemployees?status=1">搜索员工</a></li>
            </#if>
        </ul>
    </div>
    <div class="sidebar-menugroup">
        <div class="sidebar-grouptitle">会议预定</div>
        <ul class="sidebar-menu">
            <#if usersession?? && (usersession.role == 1)>
                <li class="sidebar-menuitem"><a href="/admin/addmeetingroom">添加会议室</a></li>
            </#if>
            <li class="sidebar-menuitem"><a href="/meetingrooms">查看会议室</a></li>
            <li class="sidebar-menuitem"><a href="/bookmeeting">预定会议</a></li>
            <li class="sidebar-menuitem"><a href="/searchmeetings">搜索会议</a></li>
        </ul>
    </div>
</div>

然后引入即可：<#include './leftMenu.ftl'>
```

# 注册准备

- ```java
        @RequestMapping("/register")
        public String register(Model model){
            List<Department> dept = departmentService.getAllDept();
            model.addAttribute("dept",dept);
            return "register";
        }
    
        public List<Department> getAllDept(){
            return departmentMapper.getAllDept();
        }
    
    	List<Department> getAllDept();
    
    	<select id="getAllDept" resultType="com.xiaobai.entity.Department">
    		select * from department;
    	</select>
    ```

- ```html
                                <tr>
                                    <td>确认密码：</td>
                                    <td>
                                        <input type="password" id="confirm" maxlength="20" onchange="check()"/>
                                        <div style="color: red" id="confirmInfo"></div>
                                    </td>
                                </tr>
                                <tr>
                                    <td>联系电话：</td>
                                    <td>
                                        <input type="text" id="phone" maxlength="20"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td>电子邮件：</td>
                                    <td>
                                        <input type="text" id="email" maxlength="20"/>
                                    </td>
                                </tr>
                                							<td>所在部门：</td>
                                    <td>
                                        <select name="deptid">
                                            <#if dept??>
                                                <#list dept as dep>
                                                    <option value="${dep.departmentId}">${dep.departmentName}</option>
                                                </#list>
                                            </#if>
                                         </select>
                                    </td>
                                </tr>
                                
                                
                                
                                
            <script>
                function check() {
                    var password = document.getElementById('password');
                    var confirm = document.getElementById('confirm');
                    var confirmInfo = document.getElementById('confirmInfo');
                    if (password.value != confirm.value) {
                        confirmInfo.innerHTML = '两次输入密码不一致';
                    } else {
                        confirmInfo.innerHTML = '';
                    }
                }
            </script>
    ```

# 用户注册

- ```html
    <form action="/doReg" method="post">
    ```

- ```java
    @RequestMapping("/doReg")
    public String doReg(Model model,Employee employee){
        Integer result = employeeService.doReg(employee);
        if(result ==1){
            return "redirect:/";
        }else{
            model.addAttribute("error","注册失败");
            model.addAttribute("employee",employee);
            return  "forward:/register";
        }
    }
    
    
    
    public Integer doReg(Employee employee){
        //判断重复名
        Employee emp = employeeMapper.loadEmpByUsername(employee.getUsername());
        if (emp!=null){
            return -1;
        }
        employee.setRole(1);
        employee.setStatus(0);
        return employeeMapper.doReg(employee);
    }
    
    
    Integer doReg(Employee employee);
    
    
    
    <insert id="doReg" parameterType="com.xiaobai.entity.Employee">
        insert into employee (employeename, username, phone, email, ststus, departmentid, password, role)
        value (#{employeeName}, #{username}, #{phone}, #{email}, #{ststus}, #{departmentid},#{password},#{role})
        </insert>
    ```

- 要有字段才可以表单提交

    - ```html
                        <form action="/doReg" method="post">
                            <fieldset>
                                <legend>员工信息</legend>
                                <div style="red">${error!''}</div>
                                <table class="formtable" style="width:50%">
                                    <tr>
                                        <td>姓名：</td>
                                        <td>
                                            <input type="text" id="employeename" maxlength="20" name="employename" <#if employee??>${employee.employeename}</#if>/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>账户名：</td>
                                        <td>
                                            <input type="text" id="accountname" maxlength="20" name="username" <#if employee??>${employee.username}</#if>/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>密码：</td>
                                        <td>
                                            <input type="password" id="password" maxlength="20" placeholder="请输入6位以上的密码" name="password"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>确认密码：</td>
                                        <td>
                                            <input type="password" id="confirm" maxlength="20" onchange="check()"/>
                                            <div style="color: red" id="confirmInfo"></div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>联系电话：</td>
                                        <td>
                                            <input type="text" id="phone" maxlength="20" name="phone" <#if employee??>${employee.phone}</#if>/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>电子邮件：</td>
                                        <td>
                                            <input type="text" id="email" maxlength="20" name="email"<#if employee??>${employee.email}</#if>/>
                                        </td>
                                    </tr>
                                    <td>所在部门：</td>
                                    <td>
                                        <select name="departmentid">
                                            <#if dept??>
                                                <#list dept as dep>
                                                    <option value="${dep.departmentId}">${dep.departmentName}</option>
                                                </#list>
                                            </#if>
                                        </select>
                                    </td>
                                    </tr>
                                    <tr>
                                        <td colspan="6" class="command">
                                            <input type="submit" class="clickbutton" value="注册"/>
                                            <input type="reset" class="clickbutton" value="重置"/>
                                        </td>
                                    </tr>
                                </table>
                            </fieldset>
                        </form>
        ```


# 权限处理

- ```java
    public class PermissInterceptor implements HandlerInterceptor {
        AntPathMatcher antPathMatcher = new AntPathMatcher();
    
        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
            String requestURI = request.getRequestURI();
            if ("/".equals(requestURI) || "/doLogin".equals(requestURI) || "/register".equals(requestURI) || "/doReg".equals(requestURI)) {
                return true;
            }
            HttpSession session = request.getSession(true);
            Employee usersession = (Employee) session.getAttribute("usersession");
            if (antPathMatcher.match("/admin/**", requestURI)) {
                if (usersession != null) {
                    if (usersession.getRole() == 2) {
                        return true;
                    } else {
                        response.getWriter().write("forbidden");
                        return false;
                    }
                }
            } else {
                if (usersession != null) {
                    return true;
                }
            }
            return true;
        }
    }
    ```

- ```xml
    <!--拦截器-->
    <mvc:interceptors>
        <mvc:interceptor>
            <mvc:mapping path="/**"/>
            <!--排除静态资源-->
            <mvc:exclude-mapping path="/images/**"/>
            <mvc:exclude-mapping path="/styles/**"/>
            <bean class="com.xiaobai.interceptor.PermissInterceptor"/>
        </mvc:interceptor>
    </mvc:interceptors>
    ```

- ```java
    @Controller
    @RequestMapping("/admin")
    public class DepartmentController {
    
        @RequestMapping("/departments")
        public String department() {
            return "departments";
        }
    
    }
    ```

# 注册审批

- 第一部分

- ```java
        public static final Integer STAATUS=0;
    
        @Autowired
        private EmployeeService employeeService;
    
        @RequestMapping("/approveaccount")
        public String approveaccount(Model model){
            model.addAttribute("emps",employeeService.getAllEmpsStatus(STAATUS));
            return "approveaccount";
        }
    
    
       public List<Employee> getAllEmpsStatus(Integer status) {
            return employeeMapper.getAllEmpsStatus(status);
       }
    
    
    List<Employee> getAllEmpsStatus(Integer status);
    
    
    	<select id="getAllEmpsStatus" resultType="com.xiaobai.entity.Employee">
    		select * from employee where status =#{status}
    	</select>
    ```

- ```html
    <#if emps??>
        <#list emps as emp>
            <tr>
                <td>${emp.employeename}</td>
                <td>${emp.username}</td>
                <td>${emp.phone}</td>
                <td>${emp.email}</td>
                <td>
                    <input type="button" class="clickbutton" value="通过"/>
                    <input type="button" class="clickbutton" value="删除"/>
                </td>
            </tr>
    	</#list>
    </#if>
    ```

- 第二部分

- ```html
    <td>
        <a type="button" class="clickbutton"
           href="/admin/updatestatus?employeeid=${emp.employeeid}&status=1">通过</a>
        <a type="button" class="clickbutton"
           href="/admin/updatestatus?employeeid=${emp.employeeid}&status=2">不通过</a>
    </td>
    ```

- ```java
        @RequestMapping("/updatestatus")
        public String updatestatus(Integer status,Integer employeeid){
            Integer result = employeeService.updatestatus(status, employeeid);
            return  "redirect:/admin/approveaccount";
        }
    
    
        public Integer updatestatus(Integer status, Integer employeeid) {
           return employeeMapper.updatestatus(status, employeeid);
        }
    
    
    Integer updatestatus(@Param("status") Integer status, @Param("employeeid") Integer employeeid);
    
    
    	<update id="updatestatus" parameterType="com.xiaobai.entity.Employee">
    		update employee
    		set status = #{status}
    		where employeeid = #{employeeid}
    	</update>
    ```


# 部门管理

- 第一部分添加

    - ```html
        <fieldset>
            <legend>添加部门</legend>
            部门名称:
            <input type="text" name="departmentname" id="departmentname" maxlength="20"/>
            <input type="submit" class="clickbutton" value="添加"/>
        </fieldset>
        
        
        <#if deps ??>
            <#list deps as dep>
                <tr>
                    <td>${dep.departmentId}</td>
                    <td>${dep.departmentName}</td>
                    <td>
                        <a class="clickbutton" href="#">编辑</a>
                        <a class="clickbutton" href="#">删除</a>
                    </td>
                </tr>
                </#list>
            </#if>
        ```

    - ```java
        @Controller
        @RequestMapping("/admin")
        public class DepartmentController {
        
            @Autowired
            private DepartmentService departmentService;
        
            @RequestMapping("/departments")
            public String department(Model model) {
                model.addAttribute("deps",departmentService.getAllDept());
                return "departments";
            }
            @RequestMapping("/adddepartment")
            public String adddepartment(String departmentname){
                departmentService.adddepartment(departmentname);
                return "redirect:/admin/departments";
            }
        
        }
        
        
        
        public List<Department> getAllDept(){
            return departmentMapper.getAllDept();
        }
        
        public Integer adddepartment(String departmentname) {
            Department dep = departmentMapper.getDepByName(departmentname);
            if (dep != null) {
                return -1;
            }
            return departmentMapper.adddepartment(departmentname);
        }
        
        
        
        List<Department> getAllDept();
        
        Integer adddepartment(String departmentname);
        
        Department getDepByName(String departmentname);
        
        
        
        <select id="getAllDept" resultType="com.xiaobai.entity.Department">
            select *
            from department;
        </select>
        
        <insert id="adddepartment">
            insert into department (departmentname)
            values (#{departmentname});
        </insert>
        
        <select id="getDepByName" resultType="com.xiaobai.entity.Department">
            select  * from department where departmentname = #{departmentname}
        </select>
        ```

- 第二部分删除

    - ```html
        <a class="clickbutton" href="/admin/deletedep?departmentid=${dep.departmentId}">删除</a>
        ```

    - ```java
        @RequestMapping("/deletedep")
        public String deletedep(Integer departmentid){
            departmentService.deletedep(departmentid);
            return "redirect:/admin/departments";
        }
        
        
        
        public Integer deletedep(Integer departmentid) {
            return departmentMapper.deletedep(departmentid);
        }
        
        
        
        Integer deletedep(Integer departmentid);
        
        
        
        <delete id="deletedep">
            delete from department where departmentid = #{departmentid}
        </delete>
        ```

- 第三部分修改

    - ```html
        <script src="https://cdn.bootcdn.net/ajax/libs/jquery/3.6.0/jquery.js"></script>
        
        
        <a class="clickbutton" href="#" id="edit${dep.departmentId}"
           onclick="editDep(${dep.departmentId})">编辑</a>
        <a class="clickbutton" style="display: none" href="#" id="cancel${dep.departmentId}"
           onclick="cancelDep(${dep.departmentId})">取消</a>
        
        
        
        
        <script>
            var depname;
        
            function cancelDep(depid) {
                var editBtn = $('#edit' + depid);
                var cancelBtn = $('#cancel' + depid);
                var ele = $('#depname' + depid);
                cancelBtn.css('display', 'none');
                editBtn.html('编辑');
                ele.html(depname);
            }
        
            function editDep(depid) {
                var editBtn = $('#edit' + depid);
                var cancelBtn = $('#cancel' + depid);
                var ele = $('#depname' + depid);
                depname = ele.html();
                if (cancelBtn.css('display') == 'none') {
                    cancelBtn.css('display', 'inline');
                    editBtn.html('确定');
                    var depName = ele.text();
                    ele.html('<input type="text" value="' + depName + '"/>')
                } else {
                    var children = ele.children('input');
                    var val = children.val();
                    $.post('/admin/updatedep', {id: depid, name: val}, function (msg) {
                        //刷新
                        if (msg == 'success') {
                            cancelBtn.css('display', 'none');
                            editBtn.html('编辑');
                            ele.html(val);
                        }
                    })
                }
            }
        </script>
        ```
        
    - ```java
        @RequestMapping("/updatedep")
        public String updatedep(Integer id,String name){
            Integer result = departmentService.updatedep(id,name);
            if (result == 1){
                return "success";
            }
            return "error";
        }
        
        
        
        public Integer updatedep(Integer id,String  name){
            return departmentMapper.updatedep(id,name);
        }
        
        
        
        Integer updatedep(@Param("id") Integer id,@Param("name") String namae);
        
        
        
        <update id="updatedep" parameterType="com.xiaobai.entity.Department">
            update department set departmentname =#{name} where departmentid = #{id}
        </update>
        ```

# 员工搜索

- 显示

    - ```java
        @RequestMapping("/admin")
        @Controller
        public class EmployeeController {
        
            public static final Integer PAGE_SIZE = 10;
            @Autowired
            private EmployeeService employeeService;
        
        
            @RequestMapping("/searchemployees")
            public String getAllEmployees(Employee employee, @RequestParam(defaultValue = "1") Integer page, Model model) {
                List<Employee> emps = employeeService.getAllEmps(employee, page, PAGE_SIZE);
                Long total = employeeService.getTotal(employee);
                model.addAttribute("emps", emps);
                model.addAttribute("total", total);
                model.addAttribute("page", page);
                model.addAttribute("pagenum", total % PAGE_SIZE == 0 ? total / PAGE_SIZE : total / PAGE_SIZE + 1);
                return "searchemployees";
            }
        
        }
        
        
        
            public List<Employee> getAllEmps(Employee employee, Integer page, Integer pageSize) {
                page = (page - 1) * pageSize;
                return employeeMapper.getAllEmps(employee, page, pageSize);
            }
        
            public Long getTotal(Employee employee) {
                return employeeMapper.getTotal(employee);
            }
        
        
        
            List<Employee> getAllEmps(@Param("emp") Employee employee, @Param("page") Integer page,@Param("pagesize") Integer pageSize);
        
            Long getTotal(Employee employee);
        
        
        
        
        
        	<select id="getAllEmps" resultType="com.xiaobai.entity.Employee">
        		select * from employee where status = #{emp.status}
        		<if test="emp.employeename != null">
        			and employeename = #{emp.employeename}
        		</if>
        		<if test="emp.username!=null">
        			and username = #{emp.username}
        		</if>
        		limit #{page},#{pagesize}
        	</select>
        
        	<select id="getTotal" resultType="com.xiaobai.entity.Employee">
        		select count(*) from employee where status = #{status}
        		<if test="employeename!=null">
        			and employeename = #{employeename}
        		</if>
        		<if test="username!=null">
        			and username = #{username}
        		</if>
        	</select>
        ```

    - ```html
        <div class="header-info">
            共<span class="info-number">${total}</span>条结果，
            分成<span class="info-number">${pagenum}</span>页显示，
            当前第<span class="info-number">${page}</span>页
        </div>
        
        
        
        <#if emps??>
            <#list emps as emp>
                <tr>
                    <td>${emp.employeename}</td>
                    <td>${emp.username}</td>
                    <td>${emp.phone}</td>
                    <td>${emp.email}</td>
                    <td>
                        <a class="clickbutton" href="#">关闭账号</a>
                    </td>
                </tr>
                </#list>
            </#if>
        ```

- 优化

    - ```html
        <form action="/admin/searchemployees" method="get">
            <fieldset>
                <legend>搜索会议</legend>
                <table class="formtable">
                    <tr>
                        <td>姓名：</td>
                        <td>
                            <input name="employeename" type="text" id="employeename" maxlength="20" value="<#if employee??>${employee.employeename!''}</#if>"/>
                        </td>
                        <td>账号名：</td>
                        <td>
                            <input name="username" type="text" id="accountname" maxlength="20" value="<#if employee??>${employee.employeename!''}</#if>"/>
                        </td>
                        <td>状态：</td>
                        <td>
                            <#if employee??>
                                <#if employee.status==0>
                                    <input type="radio" id="status" name="status" value="1"/><label>已批准</label>
                                    <input checked="checked" type="radio" id="status" name="status" value="0"/><label>待审批</label>
                                    <input type="radio" id="status" name="status" value="2"/><label>已关闭</label>
                                    <#elseif employee.status==1>
                                        <input checked="checked" type="radio" id="status" name="status" value="1"/><label>已批准</label>
                                        <input type="radio" id="status" name="status" value="0"/><label>待审批</label>
                                        <input type="radio" id="status" name="status" value="2"/><label>已关闭</label>
                                        <#elseif employee.status==2>
                                            <input type="radio" id="status" name="status" value="1"/><label>已批准</label>
                                            <input type="radio" id="status" name="status" value="0"/><label>待审批</label>
                                            <input checked="checked" type="radio" id="status" name="status" value="2"/><label>已关闭</label>
                                            </#if>
                                        <#else>
                                            <input type="radio" id="status" name="status" value="1" checked="checked"/><label>已批准</label>
                                            <input type="radio" id="status" name="status" value="0"/><label>待审批</label>
                                            <input type="radio" id="status" name="status" value="2"/><label>已关闭</label>
                                            </#if>
                                        </td>
                                    </tr>
        ```

        







# 查看会议室











# 会议室详情

















# 添加会议室















# 会议室预定











