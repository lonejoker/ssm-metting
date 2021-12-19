<!DOCTYPE html>
<html>
    <head>
        <title>CoolMeeting会议管理系统</title>
        <link rel="stylesheet" href="/styles/common.css"/>
    </head>
    <body>
        <#include 'top.ftl'>
        <div class="page-body">
            <#include 'leftMenu.ftl'>
            <div class="page-content">
                <div class="content-nav">
                    个人中心 > <a href="/notifications">最新通知</a>
                </div>
                <table class="listtable">
                    <caption>
                        未来7天我要参加的会议:
                    </caption>
                    <tr class="listheader">
                        <th style="width:300px">会议名称</th>
                        <th>会议室</th>
                        <th>起始时间</th>
                        <th>结束时间</th>
                        <th style="width:100px">操作</th>
                    </tr>
                    <#if nts??>
                        <#list nts as nt>
                            <tr>
                                <td>${nt.meetingname}</td>
                                <td>${nt.roomname}</td>
                                <td>${nt.starttime?string('yyyy-MM-dd HH:mm:ss')}</td>
                                <td>${nt.endtime?string('yyyy-MM-dd HH:mm:ss')}</td>
                                <td>
                                    <a class="clickbutton" href="/meetingdetails?meetingid=${nt.meetingid}">查看详情</a>
                                </td>
                            </tr>
                        </#list>
                    </#if>

                </table>
                <table class="listtable">
                    <caption>
                        已取消的会议:
                    </caption>
                    <tr class="listheader">
                        <th style="width:300px">会议名称</th>
                        <th>会议室</th>
                        <th>起始时间</th>
                        <th>结束时间</th>
                        <th style="width:100px">操作</th>
                    </tr>
                    <#if qxs??>
                        <#list qxs as qx>
                            <tr>
                                <td>${qx.meetingname}</td>
                                <td>${qx.roomname}</td>
                                <td>${qx.starttime?string('yyyy-MM-dd HH:mm:ss')}</td>
                                <td>${qx.endtime?string('yyyy-MM-dd HH:mm:ss')}</td>
                                <td>
                                    <a class="clickbutton" href="/meetingdetails?meetingid=${qx.meetingid}">查看详情</a>
                                </td>
                            </tr>
                        </#list>
                    </#if>

                </table>

            </div>
        </div>
    </body>
</html>