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



    <%--<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>--%>
    <%--<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>--%>
    <%--<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>--%>
    <%--<script src='${contextPath}/resources/js/moment.js'></script>--%>
    <%--<script src="${contextPath}/resources/js/eventValidator.js"></script>--%>
    <%--<script src="${contextPath}/resources/js/jquery.datetimepicker.full.min.js"></script>--%>
    <%--<link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">--%>
    <%--<script src="${contextPath}/resources/js/bootstrapmodal.js"></script>--%>
    <%--<link href="${contextPath}/resources/css/event.css" rel="stylesheet">--%>
    <%--<link href="${contextPath}/resources/css/serghei.css" rel="stylesheet">--%>
    <%--<link href="${contextPath}/resources/css/style.css" rel="stylesheet">--%>
    <%--<link href="${contextPath}/resources/css/adminStyle.css" rel="stylesheet">--%>


    <%--<script src="${contextPath}/resources/js/userProfile.js"></script>--%>
    <%--<link href="${contextPath}/resources/css/header-style.css" rel="stylesheet">--%>
    <%--<script src="<c:url value="/resources/js/jquery.autocomplete.min.js" />"></script>--%>
    <%--<link href="<c:url value="/resources/css/filterStyle.css" />" rel="stylesheet">--%>
    <%--<script src="<c:url value="/resources/scripts/sockjs-0.3.4.min.js"/>"></script>--%>
    <%--<script src="<c:url value="/resources/scripts/stomp.js"/>"></script>--%>
    <%--<script src="<c:url value="/resources/scripts/connectToServer.js"/>"></script>--%>


    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src='${contextPath}/resources/js/moment.js'></script>
    <script src="${contextPath}/resources/js/eventValidator.js"></script>
    <script src="${contextPath}/resources/js/jquery.datetimepicker.full.min.js"></script>
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <script src="${contextPath}/resources/js/bootstrapmodal.js"></script>
    <link href="${contextPath}/resources/css/event.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/serghei.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/style.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/adminStyle.css" rel="stylesheet">

    <link href="<c:url value="/resources/css/filterStyle.css" />" rel="stylesheet">
    <link href="${contextPath}/resources/css/header-style.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/jquery.datetimepicker.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/jquery.datetimepicker.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/jquery-ui.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Roboto:300&amp;subset=cyrillic,latin-ext" rel="stylesheet">
    <script src='${contextPath}/resources/js/jquery.min.js'></script>
    <script src='${contextPath}/resources/js/jquery-ui.min.js'></script>
    <script src="${contextPath}/resources/js/bootstrap.min.js"></script>
    <script src="${contextPath}/resources/js/userProfile.js"></script>
    <script src='${contextPath}/resources/js/fullcalendar.js'></script>
    <script src="<c:url value="/resources/scripts/sockjs-0.3.4.min.js"/>"></script>
    <script src="<c:url value="/resources/scripts/stomp.js"/>"></script>
    <script src="<c:url value="/resources/scripts/connectToServer.js"/>"></script>
    <script src="${contextPath}/resources/scripts/jquery.autocomplete.min.js"></script>

    <%--<script src="${contextPath}/resources/scripts/jquery.autocomplete.min.js"></script>--%>

    <link href="${contextPath}/resources/css/validetta.css" rel="stylesheet" type="text/css" media="screen" >
    <%--<script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>--%>
    <script type="text/javascript" src="${contextPath}/resources/js/validetta.js"></script>





</head>
<body style="width:1600px;">

<c:import url="header.jsp" />

<div class="generic-container" style="width: 1450px; margin-top: 5%; max-width: 100%; max-height: 100%">
    <div class="panel panel-default box_style_shadow", style="padding-left: 30px; padding-right: 30px; padding-bottom: 30px">
        <table id = "id" class="table table-hover", style="padding-left: 300px; max-width: 100%; max-height: 100%">
            <div style="padding-top: 30px; max-width: 100%; max-height: 100%" class="test">FILTERS</div>

            <thead class="color222">
            <th style="border: none; max-width: 100%; max-height: 100%"><input type="text"  id="w-input-search-username" onkeyup="searchFunction()" value="" placeholder = "By username" class="user_button_style"></th>
            <th style="border: none; max-width: 100%; max-height: 100%"><input type="text"  id="w-input-search-firstname" onkeyup="searchFunction2()" value="" placeholder="By first name" class="first_name_button_style"></th>
            <th style="border: none; max-width: 100%; max-height: 100%"><input type="text"  id="w-input-search-lastname" onkeyup="searchFunction3()" value="" placeholder="By last name" class="last_name_button_style"></th>
            <th style="border: none; max-width: 100%; max-height: 100%"><input type="text"  id="w-input-search-email" onkeyup="searchFunction4()" value="" placeholder="By email" class="email_button_style"></th>
            <th style="border: none; max-width: 100%; max-height: 100%"><input type="text"  id="w-input-search-assignment_name" value="" placeholder="By assignment" class="assignment_button_style"></th>
            <th style="border: none; max-width: 100%; max-height: 100%"><select id="w-input-search-role" onchange="searchFunction6()" class="roles_button_style">
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
    <div style="height: 1px; max-width: 100%; max-height: 100%"></div>
    <div class="panel panel-default box_style_shadow", style="padding-left: 30px; padding-right: 30px; padding-bottom: 30px; max-width: 100%; max-height: 100%">

        <table id = "administrationTable" class="table table-hover", style="padding-left: 300px; max-width: 100%; max-height: 100%">

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
                <td style="padding-top: 15px" align="left">${user.position}</td>
                    <c:set var="roles" value="${user.roles}"/>
                    <c:set var="role" value="${fn:substringAfter(roles, \"name='\")}"/>
                <td style="padding-top: 15px" align="left">${fn:substringBefore(role, "\'}")}</td>
                <td style="width: 90px; padding-top: 15px">

                    <c:if test="${pageContext.request.isUserInRole('ADMIN')}">

                        <c:set var = "SUPREME_ADMIN_ROLE" value = "${user.roles}"/>

                        <c:if test = "${not fn:contains({user.roles}, 'ROLE_SUPREME_ADMIN')}">
                            <button type="button" class="btn_edit_image" data-toggle="modal" data-target="#myModal_edit" id = "${user.username}"></button>
                        </c:if>
                        </c:if>

                    <c:if test="${pageContext.request.isUserInRole('SUPREME_ADMIN')}">
                        <button type="button" class="btn_edit_image" data-toggle="modal" data-target="#myModal_edit" id = "${user.username}"></button>
                    </c:if>

                    <c:choose>
                        <c:when test="${pageContext.request.remoteUser.equals(user.username)}"></c:when>
                    <c:otherwise>
                    <c:if test="${pageContext.request.isUserInRole('SUPREME_ADMIN')}">
                    <button type="deleteButton" class="btn_delete_image" data-toggle="modal" data-target="#myModal"  id="${user.username}"></button>

    </c:if>
    </c:otherwise>
    </c:choose>

    </c:forEach>
</div>
</tbody>
</table>
</div>
</div>

<div class="add_event_modal"></div>

<!-- Modal delete-->
<div class="modal" id="myModal" role="dialog" >
    <div class="modal-dialog" style="padding-top: 25%; padding-bottom: 25%;padding-left: 11%;padding-right: 25%">
        <!-- Modal content-->
        <div class="modal-content" style="width: 400px; border-radius: 0px; padding-bottom: 37px">
            <div class="modal_content"> <button type="button" style="margin-top: 0%" class="btn_close_modal" data-dismiss="modal"></button>
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
<div class="modal" id="myModal_edit" role="dialog" style="width: 100%; top: 15%;">
    <div class="modal-dialog">
        <!-- Modal content-->
        <div class="modal-content" style="width: 600px; border-radius: 0px; padding-bottom: 37px">
            <div id="modal-content" class="modal_content">
                <button type="button" id = "close-edit" class="btn_close_modal" data-dismiss="modal"></button>
                <div  align="left" style="padding-top: 7px; margin-bottom: 5%">EDIT USER </div>


                <form id="edit-form" name="form">
                <div id="div-edit-form" class="form-group"></div>
                <input type = "hidden" id="idOfUser" data-id="id"  class="form-control class_for_submit111 modal_window_fields_admin" placeholder="ID"/>
                <input type = "hidden" id="username" data-id="username" class="form-control modal_window_fields_admin class_for_submit111" placeholder="Username"/>
                <input type = "hidden" id="email" data-id="email" class="form-control modal_window_fields_admin class_for_submit111" placeholder="Email"/>
                <div>
                <input type = "text" id="firstname" data-id="firstname" name = "firstname" data-validetta="required,minLength[2],maxLength[20],regExp[example]" class="form-control modal_window_fields_admin class_for_submit111" placeholder="First name"/>
                </div>
                <input type = "text" id="lastname" data-id="lastname" data-validetta="required,minLength[2],maxLength[20],regExp[example]" class="form-control modal_window_fields_admin class_for_submit111" placeholder="Last name"/>
                <input type = "text" id="position" data-id="position" data-validetta="required,minLength[2],maxLength[20],regExp[fields]" class="form-control modal_window_fields_admin class_for_submit111" placeholder="Position"/>
                <select type="select" id = "roles" data-id="roles" class="modal_window_select_box_admin class_for_submit111" style="padding-left: 5px;">
                    <%--<option value="ROLE_GUEST">ROLE_GUEST</option>--%>
                    <%--<option value="ROLE_USER">ROLE_USER</option>--%>
                    <%--<option value="ROLE_ADMIN">ROLE_ADMIN</option>--%>
                </select>
                    <input type="hidden" id="imageOfUser" data-id="imageOfUser">
                </form>

            </div>
        </div>
    </div>
</div>



<script>
    $(document).ready(function() {
        $('#edit-form').validetta({
            validators: {
                regExp: {
                    regname: {
                        pattern: /^hello$/i,
                        errorMessage: 'Custom Reg Error Message!'
                    },
                    // you can add more
                    example: {
                        pattern: /^[A-Z][-a-zA-Z]+$/,
                        errorMessage: 'Please fill in the field correctly (English letters only, first letter - capital, no spaces or numbers)!'
                    },
                    fields:{
                        pattern: /^[a-zA-Z\s]*$/,
                        errorMessage: 'Please fill in the field correctly (English letters only, no numbers)!'
                    }
                }
            },
            realTime: true,
            onValid: function(event){
                event.preventDefault();
//                console.log('valid');
                var result = {};
                        $.each($('.class_for_submit111'), function (index, element) {
                            result[$(element).attr('data-id')] = element.value;
                        });
                        console.log(result);
                        $.ajax({
                                type: "POST",
                                url: "/admin",
                                data: result
                            }).done(function () {
                                $("#myModal_edit,.modal-backdrop").hide();
                                location.reload();

                            }).fail(function () {
                                alert('fail');
                                $("#myModal_edit,.modal-backdrop").hide();
                                location.reload();
                            });
            },
            onError: function(){
                console.log('not valid');
            }
        });

        $('#close-edit').on('click', function () {
            location.reload();
            $("#myModal_edit").hide();
        });

        $(".btn_delete_image").on('click', function () {
            var $modalDeleteButton = $('#modal_delete');
            $modalDeleteButton.attr('href', '/delete-user-' + $(this).attr('id'));

        });

        $(".btn_edit_image").on('click', function () {
            var $userEditButton = $(this);
            var usernameFromScript = $userEditButton.attr('id');
            var url = "/json/getUserByUsername?username=" + usernameFromScript;
            $.ajax({
                method: "GET",
                url: url
            }).done(function (user) {
                var form = document.getElementById('edit-form');
                var neededFields = ["id", "username", "email", "firstname", "lastname", "position", "image"];
                var userInfo = {};
                neededFields.forEach(function (fieldName) {
                    userInfo[fieldName] = user[0][fieldName];
                });


                var rolesField = ["roles"];
                var rolesFieldValue = {};
                rolesField.forEach(function (rolesValue) {
                    rolesFieldValue = user[0][rolesValue];
                });
                var right_text = rolesFieldValue.substring(1, rolesFieldValue.indexOf("]"));
                console.log(rolesFieldValue);

                var btn = document.createElement('input');
                btn.type = "button";
                btn.value = 'EDIT';
                btn.className = "btn_submit_edit";
                btn.id = "submit-edit";

                var array = ["ROLE_GUEST", "ROLE_USER", "ROLE_ADMIN"];

                Array.prototype.swap = function (fromIndex, toIndex) {
                    var temp = this[toIndex];
                    this[toIndex] = this[fromIndex];
                    this[fromIndex] = temp;
                };

                for (var i = 0; i < array.length; i++) {
                    if (array[i] === right_text) {
                        array.swap(0, i);
                    }
                }

                var selectList = document.getElementById('roles');
                selectList.className = 'modal_window_select_box_admin class_for_submit111';
                console.log(selectList);
                for (var i = 0; i < array.length; i++) {
                    var option = document.createElement("option");
                    option.value = array[i];
                    option.text = array[i];
                    selectList.appendChild(option);
                }
                /////////////////////////////////////////////////////
                neededFields.forEach(function (fieldName) {
                    var input_id = document.getElementById('idOfUser');
                    var input_username = document.getElementById('username');
                    var input_email = document.getElementById('email');
                    var input_firstname = document.getElementById('firstname');
                    var input_lastname = document.getElementById('lastname');
                    var input_position = document.getElementById('position');


                    input_id.className = 'modal_window_fields_admin class_for_submit111';
                    input_username.className = 'modal_window_fields_admin class_for_submit111';
                    input_email.className = 'modal_window_fields_admin class_for_submit111';
                    input_firstname.className = 'modal_window_fields_admin class_for_submit111';
                    input_lastname.className = 'modal_window_fields_admin class_for_submit111';
                    input_position.className = 'modal_window_fields_admin class_for_submit111';


                    input_id.value = userInfo["id"];
                    input_username.value = userInfo["username"];
                    input_email.value = userInfo["email"];
                    input_firstname.value = userInfo["firstname"];
                    input_lastname.value = userInfo["lastname"];
                    input_position.value = userInfo["position"];



                });


                form.appendChild(btn);
                var modalContext = $('#modal-content');
                registerSubmitEvent(btn);
            }).fail(function (err, status, errorText) {
                console.log("Status: " + status);
                console.log("Error text: " + errorText);
            });
        });

        function registerSubmitEvent(btn) {
                    btn.onclick = function () {
                        $('#edit-form').submit();
                    };
            }
        });
</script>

<script>
    $(document).ready(function() {
        $('#w-input-search-username').autocomplete({
            serviceUrl: '${pageContext.request.contextPath}/getUsernames',
            paramName: "userName",
            transformResult: function(response) {
                return {
                    suggestions: $.map($.parseJSON(response), function(item) {
                        return { value: item};
                    })
                };
            }
        });
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
                        return { value: item};
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
                        return { value: item};
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
                        return { value: item};
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

<div class="add_event_modal"></div>
</body>
</html>
