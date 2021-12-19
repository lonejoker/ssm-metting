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
                    个人中心 > 我的会议
                </div>
                <table class="listtable">
                    <caption>我将参加的会议：</caption>
                    <tr class="listheader">
                        <th>会议名称</th>
                        <th>会议室名称</th>
                        <th>会议开始时间</th>
                        <th>会议结束时间</th>
                        <th>会议预定时间</th>
                        <th>预定者</th>
                        <th>操作</th>
                    </tr>
                    <#if sms??>
                        <#list sms as sm>
                            <tr>
                                <td>${sm.meetingname}</td>
                                <td>${sm.roomname}</td>
                                <td>${sm.starttime?string('yyyy-MM-dd HH:mm:ss')}</td>
                                <td>${sm.endtime?string('yyyy-MM-dd HH:mm:ss')}</td>
                                <td>${sm.reservationtime?string('yyyy-MM-dd HH:mm:ss')}</td>
                                <td>${sm.reservationistname}</td>
                                <td>
                                    <a class="clickbutton" href="/meetingdetails?meetingid=${sm.meetingid}">查看详情</a>
                                </td>
                            </tr>
                        </#list>
                    </#if>
                </table>
            </div>
        </div>
    </body>
</html>