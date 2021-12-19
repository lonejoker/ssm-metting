<!DOCTYPE html>
<html>
    <head>
        <title>CoolMeeting会议管理系统</title>
        <link rel="stylesheet" href="/styles/common.css"/>
        <style type="text/css">

        </style>
    </head>
    <body>
        <#include 'top.ftl'>
        <div class="page-body">
            <#include 'leftMenu.ftl'>
            <div class="page-content">
                <div class="content-nav">
                    个人中心 > 我的预定
                </div>
                <table class="listtable">
                    <caption>我预定的会议：</caption>
                    <tr class="listheader">
                        <th>会议名称</th>
                        <th>会议室名称</th>
                        <th>会议开始时间</th>
                        <th>会议结束时间</th>
                        <th>会议预定时间</th>
                        <th>操作</th>
                    </tr>
                    <#if nots??>
                        <#list nots as not>
                            <tr>
                                <td>${not.meetingname}</td>
                                <td>${not.roomname}</td>
                                <td>${not.starttime?string('yyyy-MM-dd HH:mm:ss')}</td>
                                <td>${not.endtime?string('yyyy-MM-dd HH:mm:ss')}</td>
                                <td>${not.reservationtime?string('yyyy-MM-dd HH:mm:ss')}</td>
                                <td>
                                    <a class="clickbutton" href="/mymeetingdetails?meetingid=${not.meetingid}">查看/撤销</a>
                                </td>
                            </tr>
                        </#list>
                    </#if>

                </table>
            </div>
        </div>
    </body>
</html>