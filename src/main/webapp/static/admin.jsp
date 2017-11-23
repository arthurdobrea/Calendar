<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="border-radius" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="border" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%! public static String isTrueButtonClick; %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Edit account</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/style.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Roboto:300&amp;subset=cyrillic,latin-ext" rel="stylesheet">


    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="${contextPath}/resources/js/bootstrap.min.js"></script>
    <script src="<c:url value="/resources/js/jquery.min.js" />"></script>
    <script src="<c:url value="/resources/js/jquery.autocomplete.min.js" />"></script>
    <link href="<c:url value="/resources/css/filterStyle.css" />" rel="stylesheet">
</head>
<body style="width:1600px;">

<div class="generic-container" style="width: 1450px">
    <div class="panel panel-default box_style_shadow", style="padding-left: 30px; padding-right: 30px; padding-bottom: 30px">
        <table id = "id" class="table table-hover", style="padding-left: 300px">
            <div style="padding-top: 30px" class="test">FILTERS</div>

            <thead class="color222">
                <th style="border: none"><input type="text"  id="w-input-search-username" onkeyup="searchFunction()" value="" placeholder = "By username" class="user_button_style"></th>
                <th style="border: none"><input type="text"  id="w-input-search-firstname" onkeyup="searchFunction2()" value="" placeholder="By first name" class="user_button_style"></th>
                <th style="border: none"><input type="text"  id="w-input-search-lastname" onkeyup="searchFunction3()" value="" placeholder="By last name" class="user_button_style"></th>
                <th style="border: none"><input type="text"  id="w-input-search-email" onkeyup="searchFunction4()" value="" placeholder="By email" class="email_button_style"></th>
                <th style="border: none"><input type="text"  id="w-input-search-assignment_name" value="" placeholder="By assignment" class="assignment_button_style"></th>
                <th style="border: none"><select id="w-input-search-role" onchange="searchFunction6()" class="roles_button_style">
                        <option value="">By role</option>
                        <option>ROLE_GUEST</option>
                        <option>ROLE_USER</option>
                        <option>ROLE_ADMIN</option>
                        <option>ROLE_SUPREME_ADMIN</option>
                </select>
                </th>
            </thead>
        </table>
    </div>
    <div style="height: 1px"></div>
    <div class="panel panel-default box_style_shadow", style="padding-left: 30px; padding-right: 30px; padding-bottom: 30px">

        <table id = "administrationTable" class="table table-hover", style="padding-left: 300px">

            <div style="padding-top: 30px" class="test">USER ADMINISTRATION</div>
            <tbody>
            <tr class="thstyle">
                <th>USERNAME</th>
                <th>FIRST NAME</th>
                <th>LAST NAME</th>
                <th>EMAIL</th>
                <th>ASSIGNMENT NAME</th>
                <th>ROLE</th>
                <th width="62"></th>
                <th width="62"></th>
            </tr>




                <c:forEach items="${users}" var="user">

                    <tr>

                    <td style="padding-top: 15px" align="left">${user.username}</td>
                    <td style="padding-top: 15px" align="left">${user.firstname}</td>
                    <td style="padding-top: 15px" align="left">${user.lastname}</td>
                    <td style="padding-top: 15px" align="left">${user.email}</td>
                        <td style="padding-top: 15px" align="left">blabla</td>
                        <c:set var="roles" value="${user.roles}"/>
                        <c:set var="role" value="${fn:substringAfter(roles, \"name='\")}"/>
                        <td style="padding-top: 15px" align="left">${fn:substringBefore(role, "\'}")}</td>
                        <td style="width: 90px">


                                    <button type="button" class="btn_edit_image" data-toggle="modal" data-target="#myModal_edit" id = "${user.username}"></button>

                                    <c:if test="${pageContext.request.isUserInRole('ADMIN')}">
                                        <div style="padding-top: 15px; padding-right: 2px;">

                                        </div>
                                    </c:if>

                            <c:if test="${pageContext.request.isUserInRole('SUPREME_ADMIN')}">
                                    <%--<button type="editButton" class="btn_edit_image" data-toggle="modal" data-target="#myModal_edit" name="usernameYes" value= "${user.username}"></button>--%>
                            </c:if>

                            <c:choose>
                                <c:when test="${pageContext.request.remoteUser.equals(user.username)}"></c:when>
                                <c:otherwise>
                                    <c:if test="${pageContext.request.isUserInRole('SUPREME_ADMIN')}">
                                        <button type="deleteButton" style="float: right" class="btn_delete_image" data-toggle="modal" data-target="#myModal"  id="${user.username}"></button></div>
                                    </c:if>
                                </c:otherwise>
                            </c:choose>

                </c:forEach>
                </div>
            </tbody>
        </table>
    </div>
</div>



<!-- Modal delete-->
<div class="modal" id="myModal" role="dialog">
    <div class="modal-dialog">
        <!-- Modal content-->
    <div class="modal-content" style="width: 400px; border-radius: 0px; padding-bottom: 37px">
    <div class="modal_content"> <button type="button" style="margin-top: 7px;" class="btn_close_modal" data-dismiss="modal"></button>
        <div align="left" style="padding-top: 7px">DELETE USER </div>
        <div align="left" style="padding-top: 25px">Are u sure you want to delete this user?</div>
            <div style="padding-top: 30px">
                <button type="button" class="btn_not_today_modal" data-dismiss="modal">NOT TODAY</button></div>
                <a id="modal_delete" class="btn_delete_modal" style="text-decoration: none;">DELETE</a>
            </div>
    </div>
</div>
</div>

<!-- Modal edit-->
<div class="modal" id="myModal_edit" role="dialog" style="overflow: hidden">
    <div class="modal-dialog">
        <!-- Modal content-->
        <div class="modal-content" style="width: 600px; border-radius: 0px; padding-bottom: 37px">
            <div class="modal_content"> <button type="button" style="margin-top: 7px;" class="btn_close_modal" data-dismiss="modal"></button>
                <div align="left" style="padding-top: 7px">EDIT USER </div>

                <%--<form:form method="POST" class="form-signin">--%>
                    <%--<form:input type="hidden" path="id" id="id"/>--%>
                    <%--<h2 class="form-signin-heading">Edit user </h2>--%>

                    <%--<spring:bind path="id">--%>
                        <%--<div class="form-group ${status.error ? 'has-error' : ''}">--%>
                            <%--<form:input type="hidden" path="id" class="form-control" placeholder="ID"--%>
                                        <%--autofocus="true"></form:input>--%>
                            <%--<form:errors path="id"></form:errors>--%>
                        <%--</div>--%>
                    <%--</spring:bind>--%>

                    <%--<spring:bind path="username">--%>
                        <%--<div class="form-group ${status.error ? 'has-error' : ''}">--%>
                            <%--<form:input type="username" path="username" class="form-control" placeholder="Username" readonly="true"--%>
                                        <%--autofocus="true"></form:input>--%>
                            <%--<form:errors path="username"></form:errors>--%>
                        <%--</div>--%>
                    <%--</spring:bind>--%>

                    <%--<spring:bind path="firstname">--%>
                        <%--<div class="form-group ${status.error ? 'has-error' : ''}">--%>
                            <%--<form:input type="text" path="firstname" class="form-control" placeholder="First name"--%>
                                        <%--autofocus="true"></form:input>--%>
                            <%--<form:errors path="firstname"></form:errors>--%>
                        <%--</div>--%>
                    <%--</spring:bind>--%>

                    <%--<spring:bind path="lastname">--%>
                        <%--<div class="form-group ${status.error ? 'has-error' : ''}">--%>
                            <%--<form:input type="text" path="lastname" class="form-control" placeholder="Last name"--%>
                                        <%--autofocus="true"></form:input>--%>
                            <%--<form:errors path="lastname"></form:errors>--%>
                        <%--</div>--%>
                    <%--</spring:bind>--%>

                    <%--<spring:bind path="email">--%>
                        <%--<div class="form-group ${status.error ? 'has-error' : ''}">--%>
                            <%--<form:input type="email" path="email" class="form-control" placeholder="Email"--%>
                                        <%--autofocus="true"></form:input>--%>
                            <%--<form:errors path="email"></form:errors>--%>
                        <%--</div>--%>
                    <%--</spring:bind>--%>

                    <%--<spring:bind path="password">--%>
                        <%--<div class="form-group ${status.error ? 'has-error' : ''}">--%>
                            <%--<form:input type="password" path="password" class="form-control" placeholder="New password"--%>
                                        <%--autofocus="true"></form:input>--%>
                            <%--<form:errors path="password"></form:errors>--%>
                        <%--</div>--%>
                    <%--</spring:bind>--%>

                    <%--<spring:bind path="roles">--%>
                        <%--<div class="form-group ${status.error ? 'has-error' : ''}">--%>
                            <%--<form:select path="roles" items="${list_of_roles}" multiple="true" itemValue="name" itemLabel="name" class="form-control input-sm"--%>
                                         <%--autofocus="true"></form:select>--%>
                            <%--<form:errors path="roles"></form:errors>--%>
                        <%--</div>--%>
                    <%--</spring:bind>--%>


                    <%--<button class="btn btn-lg btn-primary btn-block" type="submit" autofocus="true" >Submit</button>--%>
                <%--</form:form>--%>
        </div>
    </div>
</div>
</div>



    <script>
        $(document).ready(function() {
            $('#w-input-search-username').autocomplete({
                serviceUrl: '${pageContext.request.contextPath}/getUsernames',
                paramName: "userName",
                transformResult: function(response) {
                    return {
                        suggestions: $.map($.parseJSON(response), function(item) {
                            return { value: item.username};
                        })
                    };
                }
            });
            $(".btn_delete_image").on('click', function() {
                var $modalDeleteButton = $('#modal_delete');
                $modalDeleteButton.attr('href', '/delete-user-' + $(this).attr('id'));
            });

            $(".btn_edit_image").on('click', function () {
                console.log("testetetete");
                var $userEditButton = $(this);
                var usernameFromScript = $userEditButton.attr('id');
                var url = "/editUser-" + usernameFromScript;
                $.ajax({
                    method: "GET",
                    url: url
                }).done(function(user){
                    console.log(user);
                }).fail(function(err, status, errorText){
                    console.log("Status: " + status);
                    console.log("Error text: " + errorText);
                });


            })

        });
    </script>
    <script>
        $(document).ready(function() {
            $('#w-input-search-firstname').autocomplete({
                serviceUrl: '${pageContext.request.contextPath}/getFirstnames',
                paramName: "firstName",
                transformResult: function(response) {
                    return {
                        suggestions: $.map($.parseJSON(response), function(item) {
                            return { value: item.firstname};
                        })
                    };
                }
            });
        });
    </script>
    <script>
        $(document).ready(function() {
            $('#w-input-search-lastname').autocomplete({
                serviceUrl: '${pageContext.request.contextPath}/getLastnames',
                paramName: "lastName",
                transformResult: function(response) {
                    return {
                        suggestions: $.map($.parseJSON(response), function(item) {
                            return { value: item.lastname};
                        })
                    };
                }
            });
        });
    </script>
    <script>
        $(document).ready(function() {
            $('#w-input-search-email').autocomplete({
                serviceUrl: '${pageContext.request.contextPath}/getEmails',
                paramName: "email",
                transformResult: function(response) {
                    return {
                        suggestions: $.map($.parseJSON(response), function(item) {
                            return { value: item.email};
                        })
                    };
                }
            });
        });
    </script>
    <script>
        $(document).ready(function() {
            $('#w-input-search-role').autocomplete({
                serviceUrl: '${pageContext.request.contextPath}/getRoles',
                paramName: "role",
                transformResult: function(response) {
                    return {
                        suggestions: $.map($.parseJSON(response), function(item) {
                            return { value: item.roles};
                        })
                    };
                }
            });
        });
    </script>

    <script>
        function searchFunction() {
            // Declare variables
            var input, filter, table, tr, td, i;
            input = document.getElementById("w-input-search-username");
            filter = input.value.toUpperCase();
            table = document.getElementById("administrationTable");
            tr = table.getElementsByTagName("tr");

            // Loop through all table rows, and hide those who don't match the search query
            for (i = 0; i < tr.length; i++) {
                td = tr[i].getElementsByTagName("td")[0];
                if (td) {
                    if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
                        tr[i].style.display = "";
                    } else {
                        tr[i].style.display = "none";
                    }
                }
            }
        }
    </script>
    <script>
        function searchFunction2() {
            // Declare variables
            var input, filter, table, tr, td, i;
            input = document.getElementById("w-input-search-firstname");
            filter = input.value.toUpperCase();
            table = document.getElementById("administrationTable");
            tr = table.getElementsByTagName("tr");

            // Loop through all table rows, and hide those who don't match the search query
            for (i = 0; i < tr.length; i++) {
                td = tr[i].getElementsByTagName("td")[1];
                if (td) {
                    if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
                        tr[i].style.display = "";
                    } else {
                        tr[i].style.display = "none";
                    }
                }
            }
        }
    </script>
    <script>
        function searchFunction3() {
            // Declare variables
            var input, filter, table, tr, td, i;
            input = document.getElementById("w-input-search-lastname");
            filter = input.value.toUpperCase();
            table = document.getElementById("administrationTable");
            tr = table.getElementsByTagName("tr");

            // Loop through all table rows, and hide those who don't match the search query
            for (i = 0; i < tr.length; i++) {
                td = tr[i].getElementsByTagName("td")[2];
                if (td) {
                    if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
                        tr[i].style.display = "";
                    } else {
                        tr[i].style.display = "none";
                    }
                }
            }
        }
    </script>
    <script>
        function searchFunction4() {
            // Declare variables
            var input, filter, table, tr, td, i;
            input = document.getElementById("w-input-search-email");
            filter = input.value.toUpperCase();
            table = document.getElementById("administrationTable");
            tr = table.getElementsByTagName("tr");

            // Loop through all table rows, and hide those who don't match the search query
            for (i = 0; i < tr.length; i++) {
                td = tr[i].getElementsByTagName("td")[3];
                if (td) {
                    if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
                        tr[i].style.display = "";
                    } else {
                        tr[i].style.display = "none";
                    }
                }
            }
        }
    </script>
    <script>
        function searchFunction6() {
            // Declare variables
            var input, filter, table, tr, td, i;
            input = document.getElementById("w-input-search-role");
            filter = input.value.toUpperCase();
            table = document.getElementById("administrationTable");
            tr = table.getElementsByTagName("tr");

            // Loop through all table rows, and hide those who don't match the search query
            for (i = 0; i < tr.length; i++) {
                td = tr[i].getElementsByTagName("td")[5];
                if (td) {
                    if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
                        tr[i].style.display = "";
                    } else {
                        tr[i].style.display = "none";
                    }
                }
            }
        }
    </script>

</body>
</html>