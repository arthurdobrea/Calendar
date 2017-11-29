<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>--%>
<%--<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>--%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
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

    <title>Edit event</title>

    <link href="${contextPath}/resources/css/autocomplete.css" rel="stylesheet">
    <link href='http://fonts.googleapis.com/css?family=Oswald:300' rel='stylesheet' type='text/css'>
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/jquery.datetimepicker.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/subscription.css" rel="stylesheet">

    <script src="${contextPath}/resources/js/ui-bootstrap-tpls-2.5.0.min.js"></script>
    <script src="${contextPath}/resources/js/bootstrapmodal.js"></script>
    <script src="${contextPath}/resources/jquery/jquery.js"></script>
    <script src="${contextPath}/resources/jquery/jquery-ui.js"></script>
    <script src="${contextPath}/resources/jquery/jquery.datetimepicker.js"></script>
    <link href="${contextPath}/resources/jquery/jquery-ui.css" rel="stylesheet">
    <script src="${contextPath}/resources/scripts/jquery.autocomplete.min.js"></script>

    <script>
        $(document).ready(function() {
            $('#w-input-search').autocomplete({
                serviceUrl: "/getUserFullName",
                onSelect: function(inp){
                    console.log(inp.value);
                    if (document.getElementById("t-participants").value.indexOf(inp.value)<0)
                        document.getElementById("t-participants").value+=inp.value+",";
                    else
                        alert("User "+ inp.value+" is in the list ");
                    document.getElementById("w-input-search").value="";
                },
                paramName: "userFullName",
                delimiter: ",",
                width: "31%",
                transformResult: function(response) {
                    return {
                        suggestions: $.map($.parseJSON(response), function(item) {
                            return { value: item.toString(), data: item.id};
                        })
                    };
                }
            });
        });
    </script>
</head>
<body>


<div class="modal-dialog ">
    <div class="modal-content">

        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                    aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">SUBSCRIBE</h4>
        </div>
        <div class="modal-body">
            <form action="${contextPath}/subscribe" method="POST">
                <div class="subscribe-form">

                    <div class="row">
                        <div class="col-sm-12">
                            <label for="EventType-checkbox" id="eventtype-box-label">subscription</label>
                            <div class="checkbox-group" style="text-align: center" id="EventType-checkbox">
                                <c:set var="mainchecked" value="0"/>

                                <c:forEach items="${eventsList}" var="eventType">
                                    <c:set var="checked" value="0"/>
                                    <c:forEach items="${userSubscription}" var="subs">
                                        <c:if test="${subs==eventType}">
                                            <c:set var="checked" value="1"/>
                                        </c:if>
                                    </c:forEach>

                                    <c:if test="${checked==1}">
                                        <c:set var="mainchecked" value="1"/>
                                        <label class="checkbox-inline">
                                            <input type="checkbox" name="checkboxName" value="${eventType}"
                                                   id="checkboxSubs" checked/>${eventType}
                                        </label>
                                    </c:if>
                                    <c:if test="${checked==0}">
                                        <label class="checkbox-inline">
                                            <input type="checkbox" name="checkboxName" value="${eventType}"
                                                   id="checkboxSubs"/>${eventType}
                                        </label>
                                    </c:if>
                                </c:forEach>
                                <c:if test="${mainchecked==0}">
                                    <label class="checkbox-inline">
                                        <input type="checkbox" name="checkboxName" value="" hidden checked/>
                                    </label>
                                </c:if>
                            </div>
                        </div>
                    </div>
                    <br>
                    <div class="row">
                        <div class="col-sm-12" style="text-align: center; bottom: 20px">
                            <div class="checkbox-group"  id="subs-checkbox">
                                <label class="checkbox-inline">
                                    <input type="checkbox"  name="checksubs"/>send me new events</label>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-sm-12" style="text-align: center">
                            <input type="submit" id="sendButton" value="send">
                        </div>
                    </div>

                </div>

            </form>
    </div>
</div>
</div>

<script src="${contextPath}/resources/js/jquery.datetimepicker.full.min.js"></script>
    <script src="${contextPath}/resources/js/eventValidator.js"></script>



</body>
</html>
