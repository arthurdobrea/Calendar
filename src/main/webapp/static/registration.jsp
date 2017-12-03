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

    <title>Create an account</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/registration.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/header-style.css" rel="stylesheet">
    <link href='http://fonts.googleapis.com/css?family=Oswald:300' rel='stylesheet' type='text/css'>

    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="${contextPath}/resources/js/bootstrap.min.js"></script>
    <script>
        $(document).ready( function() {
            $(".file-upload input[type=file]").change(function(){
                $("#filename").val("Avatar uploaded");
            });
        });

        function disableSubmitButton() {
            $("form").submit(function() {
                $(this).submit(function() {
                    return false;
                });
                return true;
            });
        }
    </script>
</head>
<body>
<div class="container">
    <form:form method="POST" modelAttribute="userForm" class="form-signin-reg form-signin form-signin-reg"
               enctype="multipart/form-data">
        <table class="registration-wrapper">
            <tr>
                <td colspan="4">
                    <div class="endava_logo"></div>
                </td>
            </tr>
            <tr>
                <td colspan="2" width="50%">
                    <spring:bind path="username">
                        <div class="form-group form-group-reg ${status.error ? 'has-error' : ''}">
                            <c:set var="placeholder" value="USERNAME"/>
                            <c:set var="placeColor" value="#48545B"/>

                            <div class="register-hint">
                                <form:input id="register-fields" type="text" path="username" class="form-control"
                                            placeholder='${placeholder}' autofocus="true"/>

                                <c:if test="${status.error}">
                                    <span class="hint-body"><form:errors path="username"/></span>
                                </c:if>
                            </div>
                        </div>
                    </spring:bind>
                </td>
                <td colspan="2" width="50%">
                    <spring:bind path="email">
                        <div class="form-group form-group-reg ${status.error ? 'has-error' : ''}">
                            <c:set var="placeholder" value="EMAIL"/>
                            <c:set var="placeColor" value="#48545B"/>

                            <div class="register-hint">
                                <form:input id="register-fields" type="email" path="email" class="form-control"
                                            placeholder="${placeholder}" autofocus="true"/>
                                <c:if test="${status.error}">
                                    <span class="hint-body"><form:errors path="email"/></span>
                                </c:if>
                            </div>
                        </div>
                    </spring:bind>
                </td>
            </tr>
            <tr>
                <td>
                    <spring:bind path="firstname">
                        <div class="form-group form-group-reg ${status.error ? 'has-error' : ''}">
                            <c:set var="placeholder" value="FIRST NAME"/>
                            <c:set var="placeColor" value="#48545B"/>

                            <div class="register-hint">
                                <form:input id="register-fields" type="text" path="firstname" class="form-control"
                                            placeholder="${placeholder}" autofocus="true"/>

                                <c:if test="${status.error}">
                                    <span class="hint-body"><form:errors path="firstname"/></span>
                                </c:if>
                            </div>
                        </div>
                    </spring:bind>
                </td>
                <td>
                    <spring:bind path="lastname">
                        <div class="form-group form-group-reg ${status.error ? 'has-error' : ''}">
                            <c:set var="placeholder" value="LAST NAME"/>
                            <c:set var="placeColor" value="#48545B"/>

                            <div class="register-hint">
                                <form:input id="register-fields" type="text" path="lastname" class="form-control"
                                            placeholder="${placeholder}" autofocus="true"/>

                                <c:if test="${status.error}">
                                    <span class="hint-body"><form:errors path="lastname"/></span>
                                </c:if>
                            </div>
                        </div>
                    </spring:bind>
                </td>
                <td colspan="2">
                    <spring:bind path="position">
                        <div class="form-group form-group-reg ${status.error ? 'has-error' : ''}">
                            <c:set var="placeholder" value="POSITION"/>
                            <c:set var="placeColor" value="#48545B"/>

                            <div class="register-hint">
                                <form:input id="register-fields" type="text" path="position" class="form-control"
                                            placeholder="${placeholder}" autofocus="true"/>

                                <c:if test="${status.error}">
                                    <span class="hint-body"><form:errors path="position"/></span>
                                </c:if>
                            </div>
                        </div>
                    </spring:bind>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <spring:bind path="password">
                        <div class="form-group form-group-reg ${status.error ? 'has-error' : ''}">
                            <c:set var="placeholder" value="PASSWORD"/>
                            <c:set var="placeColor" value="#48545B"/>

                            <div class="register-hint">
                                <form:input id="register-fields" type="password" path="password" class="form-control"
                                            placeholder="${placeholder}" autofocus="true"/>

                                <c:if test="${status.error}">
                                    <span class="hint-body"><form:errors path="password"/></span>
                                </c:if>
                            </div>
                        </div>
                    </spring:bind>
                </td>

                <td colspan="2">
                    <spring:bind path="confirmPassword">
                        <div class="form-group form-group-reg ${status.error ? 'has-error' : ''}">
                            <c:set var="placeholder" value="CONFIRM PASSWORD"/>
                            <c:set var="placeColor" value="#48545B"/>

                            <div class="register-hint">
                                <form:input id="register-fields" type="password" path="confirmPassword"
                                            class="form-control" placeholder="${placeholder}" autofocus="true"/>

                                <c:if test="${status.error}">
                                    <span class="hint-body"><form:errors path="confirmPassword"/></span>
                                </c:if>
                            </div>
                        </div>
                    </spring:bind>
                </td>
            </tr>
            <tr>
                <td colspan="4">
                    <spring:bind path="multipartFile">
                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <input type="text" id="filename" class="filename" value="Upload an avatar" disabled>
                            <div class="file-upload">
                                <label>
                                    <form:input type="file" path="multipartFile" class="form-control"
                                                autofocus="true"/>
                                    <span>Choose image</span>
                                </label>
                            </div>

                            <form:errors path="multipartFile"/>
                        </div>
                    </spring:bind>
                </td>
            </tr>
            <tr>
                <td colspan="4">
                    <button id="btn-reg" class="btn btn-lg btn-block" type="submit" onclick="disableSubmitButton()">Register</button>
                </td>
            </tr>
        </table>
    </form:form>
</div>
</body>
</html>