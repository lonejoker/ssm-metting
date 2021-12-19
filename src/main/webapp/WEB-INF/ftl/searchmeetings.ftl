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
                    会议预定 > 搜索会议
                </div>
                <form action="/searchmeetings" method="post">
                    <fieldset>
                        <legend>搜索会议</legend>
                        <table class="formtable">
                            <tr>
                                <td>会议名称：</td>
                                <td>
                                    <input type="text" name="meetingname" id="meetingname" maxlength="20"/>
                                </td>
                                <td>会议室名称：</td>
                                <td>
                                    <input type="text" name="roomname" id="roomname" maxlength="20"/>
                                </td>
                                <td>预定者姓名：</td>
                                <td>
                                    <input type="text" name="reservationistname" id="reservername" maxlength="20"/>
                                </td>
                            </tr>
                            <tr>
                                <td>预定日期：</td>
                                <td colspan="5">
                                    从&nbsp;<input type="date" name="reservationtime" id="reservefromdate"
                                                  placeholder="例如：2013-10-20"/>
                                    到&nbsp;<input type="date" name="reservetodate" id="reservetodate"
                                                  placeholder="例如：2013-10-22"/>
                                </td>
                            </tr>
                            <tr>
                                <td>会议日期：</td>
                                <td colspan="5">
                                    从&nbsp;<input type="date" name="starttime" id="meetingfromdate"
                                                  placeholder="例如：2013-10-20"/>
                                    到&nbsp;<input type="date" name="endtime" id="meetingtodate"
                                                  placeholder="例如：2013-10-22"/>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="6" class="command">
                                    <input type="submit" class="clickbutton" value="查询"/>
                                    <input type="reset" class="clickbutton" value="重置"/>
                                </td>
                            </tr>
                        </table>
                    </fieldset>
                </form>
                <div>
                    <h3 style="text-align:center;color:black">查询结果</h3>
                    <div class="pager-header">
                        <div class="header-info">
                            共<span class="info-number">${total}</span>条结果，
                            分成<span class="info-number">${pagenum}</span>页显示，
                            当前第<span class="info-number">${page}</span>页
                        </div>
                        <div class="header-nav">
                            <a type="button" class="clickbutton" href="/searchmeetings?&page=1">首页</a>
                            <a type="button" class="clickbutton"
                               <#if page!=1>href="/searchmeetings?&page=${page-1}"</#if>>上页</a>
                            <a type="button" class="clickbutton"
                               <#if page!=pagenum>href="/searchmeetings?&page=${page+1}"</#if>">下页</a>
                            <a type="button" class="clickbutton" href="/searchmeetings?&page=${pagenum}">末页</a>
                            <form action="/searchmeetings?&page=${page}" style="display: inline" method="get">
                                跳到第<input name="page" type="text" id="pagenum" class="nav-number"/>页
                                <input type="submit" class="clickbutton" value="跳转"/>
                            </form>
                        </div>
                    </div>
                </div>
                <table class="listtable">
                    <tr class="listheader">
                        <th>会议名称</th>
                        <th>会议室名称</th>
                        <th>会议开始时间</th>
                        <th>会议结束时间</th>
                        <th>会议预定时间</th>
                        <th>预定者</th>
                        <th>操作</th>
                    </tr>

                    <#if sml??>
                    <#list sml as sm>
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
                        </#list>
                        </#if>
                    </tr>
                </table>
            </div>
        </div>
    </body>
</html>