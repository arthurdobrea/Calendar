function allDayChecked() {
    $("#timepicker1").prop('disabled', true);
    $("#timepicker2").prop('disabled', true);
}

function allDayUnchecked() {
    $("#timepicker1").prop('disabled', false);
    $("#timepicker2").prop('disabled', false);
}

// for creating an event
function eventDateTimeValidation() {
    //getting the start of event
    var sDate = $("#datepicker1").prop('value');
    var sTime = $("#timepicker1").prop('value');

    var startString = sDate + " " + sTime;
    var sDT = new Date(startString);
    var startYear = sDT.getFullYear();

    var startMonth = sDT.getMonth() + 1;
    if (startMonth < 10) startMonth = "0" + startMonth;

    var startDate = sDT.getDate();
    if (startDate < 10) startDate = "0" + startDate;

    var startDateTime = startYear + "/" + startMonth + "/" + startDate + " " + sTime;

    if ($("#all-day").prop('checked')) {
        startDateTime = startYear + "/" + startMonth + "/" + startDate;
    }

    $("#datetimepicker1").prop('value', startDateTime);


//getting the end of the event
    var eDate = $("#datepicker2").prop('value');
    var eTime = $("#timepicker2").prop('value');

    var endString = eDate  + " " + eTime;
    var eDT = new Date(endString);

    var endYear = eDT.getFullYear();
    var endMonth = eDT.getMonth() +1;
    if (endMonth < 10) endMonth = "0" + endMonth;

    var endDate = eDT.getDate();
    if (endDate < 10) endDate = "0" + endDate;

    endDateTime = endYear + "/" + endMonth + "/" + endDate + " " + eTime;

    if ($("#all-day").prop('checked')) {
        var endDateTime = endYear + "/" + endMonth + "/" + endDate;
    }

    $("#datetimepicker2").prop('value', endDateTime);

    if (eDT < sDT) {
        $("#datepicker2, #timepicker2").css("color", "red");
        alert("The end date should be after the start date. Please choose a valid end date");
    }

    console.log( $("#datetimepicker1").prop('value'));
    console.log( $("#datetimepicker2").prop('value'));
}

// for editing an event
function eventDateTimeEdit() {
    //getting the start of event
    var startString = $("#datetimepicker1").prop('value');
    var sDT = new Date(startString);
    var startYear = sDT.getFullYear();
    var startMonth = sDT.getMonth() + 1;
    if (startMonth < 10) startMonth = "0" + startMonth;
    var startDate = sDT.getDate();
    if (startDate < 10) startDate = "0" + startDate;
    var startHour = sDT.getHours();
    if (startHour < 10) startHour = "0" + startHour;
    var startMinute = sDT.getMinutes();
    if (startMinute < 10) startMinute = "0" + startMinute;
    var startDateTime = startYear + "/" + startMonth + "/" + startDate + " " + startHour + ":" + startMinute;

    $("#datetimepicker1").prop('value', startDateTime);

//getting the end of the event
    var endString = $("#datetimepicker2").prop('value');
    var eDT = new Date(endString);
    var endYear = eDT.getFullYear();
    var endMonth = eDT.getMonth() +1;
    if (endMonth < 10) endMonth = "0" + endMonth;
    var endDate = eDT.getDate();
    if (endDate < 10) endDate = "0" + endDate;
    var endHour = eDT.getHours();
    if (endHour < 10) endHour = "0" + endHour;
    var endMinute = eDT.getMinutes();
    if (endMinute < 10) endMinute = "0" + endMinute;
    var endDateTime = endYear + "/" + endMonth + "/" + endDate + " " + endHour + ":" + endMinute;

    $("#datetimepicker2").prop('value', endDateTime);

    if (eDT < sDT) {
        $("#datepicker2, #timepicker2").css("color", "red");
        alert("The end date should be after the start date. Please choose a valid end date");
    }

    console.log( $("#datetimepicker1").prop('value'));
    console.log( $("#datetimepicker2").prop('value'));
}


// autocomplete participants field
function autoComplete() {
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
        maxWidth: "400px",
        transformResult: function(response) {
            return {
                suggestions: $.map($.parseJSON(response), function(item) {
                    return { value: item.toString(), data: item.id};
                })
            };
        }
    });
}


$('form').submit(function(){
    // Блокируем кнопки при отправке формы
    $('input[type=submit]', $(this)).prop("disabled", true );
    e.preventDefault();
});


//error event time
function changeColor() {
    $("#datepicker2, #timepicker2").css("color", "#48545B");
}

// for editing event form change
function changeDateTimeForm () {
    $("#datetimepicker1, #datetimepicker2").hide();
    $("#startBlock, #endBlock, #allDayBlock").show();
}



function checkHiddenInput() {
    if ( $("#datetimepicker1").css('display') == 'none' ){
        eventDateTimeValidation();
    } else {
        eventDateTimeEdit();
    }
}




