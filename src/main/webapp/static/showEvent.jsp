<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description">
    <meta name="author">

    <title>show event</title>

    <link href="${contextPath}/resources/css/autocomplete.css" rel="stylesheet">
    <link href='http://fonts.googleapis.com/css?family=Oswald:300' rel='stylesheet' type='text/css'>
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/style.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/jquery.datetimepicker.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/event.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/jquery-ui.css" rel="stylesheet">

    <script src="${contextPath}/resources/js/bootstrapmodal.js"></script>
    <script src="${contextPath}/resources/scripts/jquery-1.10.2.min.js"></script>
    <script src="${contextPath}/resources/js/jquery.datetimepicker.js"></script>
    <script src="${contextPath}/resources/scripts/jquery.autocomplete.min.js"></script>
    <script src="${contextPath}/resources/js/userProfile.js"></script>
</head>

<body>
<%--show event modal--%>
<div class="modal fade" id="ShowEvent" role="dialog" >
    <div class="modal-dialog modal-lg" align="center" style="margin-top: 60px">
        <div class="modal-content" style="border-radius: 0; cursor: context-menu;">
            <div class="modal-header" style="padding-left: 25px; padding-right: 25px; border:0">
                <div class="create_event_header">
                    <p align="left" class="modal_topic endava_grey_text">EVENT<button type="button" class="close_modal" data-dismiss="modal"></button></p>
                </div>
            </div>
            <div class="modal-body" style="padding-left: 25px; padding-right: 25px">
                <form action="${contextPath}/showEvent" method="POST">
                    <div class="event-form">

                        <div class="row" id="leftblock" style="padding-right: 15px">
                            <div class="col-sm-6" style="text-align: left; overflow: scroll">
                                <table class="table_show_event">
                                    <tbody>
                                    <tr>
                                        <div class="form-group">
                                            <td><strong>Title: </strong></td>
                                            <td><span style="color: #d2322d">${event.title}</span></td>
                                        </div>
                                    </tr>
                                    <tr>
                                        <div class="form-group">
                                            <td><strong>Location: </strong></td>
                                            <td>${event.location}</td>
                                        </div>
                                    </tr>
                                    <tr>
                                        <div class="form-group">
                                            <td><strong>Type: </strong></td>
                                            <td>${event.eventType}</td>
                                        </div>
                                    </tr>
                                    <tr>
                                        <div class="form-group">
                                            <td><strong>Start: </strong></td>
                                            <td>${start}</td>
                                        </div>
                                    </tr>
                                    <tr>
                                        <div class="form-group">
                                            <td><strong>End: </strong>&nbsp;</td>
                                            <td>${end}</td>
                                        </div>
                                    </tr>
                                    <tr style="height: 5px; margin: 0; padding: ">
                                        <td colspan="2"></td>
                                    </tr>
                                    <tr>
                                        <div class="form-group">
                                            <td><strong>Created: </strong>&nbsp;</td>
                                            <td>${created}</td>
                                        </div>
                                    </tr>
                                    <tr>
                                        <div class="form-group">
                                            <td><strong>Author: </strong></td>
                                            <td>${event.author.fullName}</td>
                                        </div>
                                    </tr>

                                    </tbody>
                                </table>
                            </div>

                            <div class="row" id="rightblock">
                                <div class="col-sm-6" style="padding-top: 23px";>
                                    <div class="form-group textarea-group">
                                        <label for="ev-show-description">DESCRIPTION</label>
                                        <textarea name="description" class="form-control" rows="3" readonly style="background: none"
                                                  id="ev-show-description" required="true" value="${event.description}">${event.description}</textarea>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="row" id="bottomblock">
                            <div class="col-sm-12" >
                                <label for="t-show-participants" id="label-show-participants">PARTICIPANTS [<span
                                        style="color: #d2322d; font-size: 12px;">${event.participants.size()}</span>]</label>
                                <div class="form-group participant-group" id="t-show-participants" style="margin-bottom: 5px">
                                    <div class="row">

                                        <c:forEach items="${event.participants}" var="user">
                                            <div class="col-md-3 col-sm-4" id="t-show-participants-row">
                                                <table style="border: none">
                                                    <tr>
                                                        <td rowspan="2"><img id="avatar" src="data:image/jpeg;base64,
                                                             ${user.getImageBase64()}" onerror="this.src='/resources/icons/defaultImage.png'" alt="Your avatar"
                                                                             style="width: 40px; height: 40px; border-radius: 50%"/>
                                                        </td>
                                                        <td style="padding-left: 2px; font-size: 14px; padding-bottom: 18px;">&nbsp;&nbsp;${user.fullName}</td>
                                                    </tr>
                                                </table>
                                            </div>
                                        </c:forEach>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-12" style="text-align: center; margin-bottom: 15px">
                                <input hidden name="id" value="${event.id}">
                                <%--<c:set var="isParticipant" value="0"/>--%>
                                <c:if test="${isParticipant}">
                                    <input type="submit" class="btn_login_submit" value="UNSUBSCRIBE">
                                </c:if>
                                <c:if test="${!isParticipant}">
                                    <input type="submit" class="btn_login_submit" value="SUBSCRIBE">
                                </c:if>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

</body>
</html>


