<div class="page-header">
    <div class="header-banner">
        <img src="/images/header.png" alt="Meeting"/>
    </div>
    <div class="header-title">
        欢迎访问Meeting会议管理系统
    </div>
    <div class="header-quicklink">
        欢迎
        <#if currentuser??>
            <strong>您，${currentuser.employeeName!''}</strong>
        </#if>
        <a href="/changepassword">[修改密码]</a>
    </div>
</div>