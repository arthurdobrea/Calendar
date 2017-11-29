<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

    <title>Edit account</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <%--<link href="${contextPath}/resources/css/common.css" rel="stylesheet">--%>
    <link href="${contextPath}/resources/css/style.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/serghei.css" rel="stylesheet">
    <link href='http://fonts.googleapis.com/css?family=Oswald:300' rel='stylesheet' type='text/css'>

    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</head>

<body>
<div class="modal fade" id="EditUser" role="dialog">
    <div class="modal-dialog" align="center" style="margin-top: 100px">
        <!-- Modal content-->
        <div class="row edit_profile_modal">
            <div class="modal-header edit_profile_header endava_grey_text">
                <div align="left" class="modal_topic">EDIT PROFILE<button type="button" class="close_modal" data-dismiss="modal"></button></div>
            </div>
            <div class="modal-body edit_profile_body">
                <form:form method="POST" modelAttribute="userForm" id="user_edit_form">
                    <input id="user_username" type="hidden" name="username" placeholder="Username" autofocus="true" value="${username}" readonly>

                    <spring:bind path="firstname">
                        <div class="${status.error ? 'has-error' : ''}">
                            <form:input autocomplete="false" id="user_firstname" type="text" path="firstname" name="firstname" placeholder="FIRST NAME"
                                        autofocus="true" value="${firstname}"></form:input>
                            <form:errors path="firstname"></form:errors>
                        </div>
                    </spring:bind>

                    <spring:bind path="lastname">
                        <div class="${status.error ? 'has-error' : ''}">
                            <form:input autocomplete="false" id="user_lastname"  type="text" path="lastname" name="lastname" placeholder="LAST NAME"
                                         autofocus="true" value="${lastname}"></form:input>
                            <form:errors path="lastname"></form:errors>
                        </div>
                    </spring:bind>

                    <spring:bind path="position">
                        <div class="${status.error ? 'has-error' : ''}">
                            <form:input autocomplete="false" id="user_position"  type="text" path="position" name="position" placeholder="ASSIGNMENT"
                                        autofocus="true" value="${position}"></form:input>
                            <form:errors path="position"></form:errors>
                        </div>
                    </spring:bind>

                    <spring:bind path="email">
                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <form:input autocomplete="false" id="user_email" type="text" path="email" name="email" placeholder="EMAIL"
                                        autofocus="true" value="${email}"></form:input>
                            <form:errors path="email"></form:errors>
                        </div>
                    </spring:bind>
                    <button class="btn_edit_user_submit" type="submit">EDIT</button>
                </div>
                </form:form>
        </div>
    </div>
</div>
</body>
</html>