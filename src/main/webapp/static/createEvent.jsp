<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
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

    <title>Create an event</title>

    <link href="${contextPath}/resources/css/autocomplete.css" rel="stylesheet">
    <link href='http://fonts.googleapis.com/css?family=Oswald:300' rel='stylesheet' type='text/css'>
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/jquery.datetimepicker.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/event.css" rel="stylesheet">
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


    <div class=" modal-dialog modal-dialog modal-lg">
        <div class="modal-content">

            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close" ><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">ADD EVENT</h4>
            </div>
            <div class="modal-body">
                <form action="${contextPath}/createEvent" method="POST">
                    <div class="event-form">

                        <div class="row" id="leftblock" style="padding-right: 15px">
                            <div class="col-sm-6">
                                <div class="form-group">
                                    <label for="ev-title">TITLE</label>
                                    <input type="text" name="title" class="form-control" id="ev-title"
                                           placeholder="Enter title" required="true">
                                </div>
                                <div class="form-group">
                                    <label for="ev-location">LOCATION</label>
                                    <input type="text" name="location" class="form-control" id="ev-location"
                                           placeholder="Enter Location" required="true">
                                </div>
                                <div class="form-group">
                                    <label for="ev-type">EVENT TYPE</label>
                                    <select class="form-control" id="ev-type" name="eventType" required="true">
                                        <option value="">Select event type</option>
                                        <c:forEach items="${eventTypes}" var="et">
                                            <option value=${et}>${et.view()}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="row">
                                    <div class="col-sm-6">
                                        <div class="form-group">
                                            <label for="datetimepicker1"> START DATE</label>
                                            <input type="text" name="start" class="form-control" id="datetimepicker1" style="background-color: #FFFFFF"
                                                   placeholder="Choose date... " required READONLY >
                                        </div>
                                    </div>

                                    <div class="col-sm-6">
                                        <div class="form-group">
                                            <label for="datetimepicker2">END DATE</label>
                                            <input type="text" name="end" class="form-control" id="datetimepicker2"
                                                   placeholder="Choose date... " required READONLY style="background-color: #FFFFFF">
                                        </div>
                                    </div>

                                        <div class="col-sm-6" style="top: 13px">
                                            <div class="form-group" id="alldaydiv" style="padding-bottom:5px;">
                                                &nbsp; &nbsp;&nbsp;ALL DAY &nbsp;
                                                <label id="alldaylabel"> <input class="checkbox-inline"  type="checkbox" id="all-day" onclick="if(this.checked)
                                            {allDayChecked();} else {allDayUnchecked();}" ></label>
                                            </div >
                                        </div>


                                </div>


                            </div>


                            <div class="row" id="rightblock">
                                <div class="col-sm-6">
                                    <div class="form-group textarea-group">
                                        <label for="ev-description">DESCRIPTION</label>
                                        <textarea name="description" class="form-control" rows="3"
                                                  id="ev-description" required="true"></textarea>
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <div class="form-group participant-group">
                                        <div class="input-group">

                                            <input type="text" id="w-input-search" value=""
                                                   class="form-control" placeholder="Enter name...">
                                            <span class="input-group-btn" style="text-align: right">
                                                    <button class="btn btn-secondary" type="button"
                                                            id="span-btn-search">&#128269</button>
                                                </span>

                                        </div>
                                        <label for="t-participants">PARTICIPANTS</label>
                                        <textarea class="form-control" name="participants" id="t-participants"
                                                  rows="3" REQUIRED></textarea>
                                    </div>

                                    <div class="row">
                                        <div class="col-sm-12">
                                            <div class="checkbox-group" name="end" id="subs-checkbox" style="padding-bottom:15px">
                                                <label class="checkbox-inline">
                                                    <input type="checkbox"/>Send emails to
                                                    participants</label>
                                                <label class="checkbox-inline">
                                                    <input type="checkbox" name="checkSubscribe"/>Send emails to
                                                    subscribers
                                                </label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-sm-12">
                                <label for="tag-checkbox" id="tag-box-label">TAGS</label>
                                <div class="checkbox-group form-group " style="text-align: center; bottom:-10px; "
                                     id="tag-checkbox">
                                    <c:forEach items="${tags}" var="tag">
                                        <label class="checkbox-inline" style="color:${tag.tag.color()}">
                                            <input type="checkbox" name="checkboxTags"
                                                   id="checkboxTag" value="${tag.tag}"/> ${tag.tag.view()}
                                        </label>
                                    </c:forEach>
                                    <label class="checkbox-inline" style="color:${tag.tag.color()}">
                                        <input type="checkbox" name="checkboxTags"
                                                hidden value="hidden" checked/>
                                    </label>

                                </div>
                            </div>
                        </div>


                        <div class="row">
                            <div class="col-sm-12" style="text-align: center">
                                <input type="submit" id="sendButton" value="ADD">
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
