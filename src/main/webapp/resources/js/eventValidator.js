// function clickStart() {
//     $('#timepicker1').timepicker({
//         timeFormat: 'HH:mm',
//         minTime: '08:00',
//         maxHour: 23,
//         maxMinutes: 59,
//         startTime: '10:00',
//         interval: 15,
//         dropdown: true,
//     });
// }

function allDayChecked() {
    $("#timepicker1").prop('disabled', true);
    $("#timepicker2").prop('disabled', true);
}

function allDayUnchecked() {
    $("#timepicker1").prop('disabled', false);
    $("#timepicker2").prop('disabled', false);
}


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

    if (eDT <= sDT) {
        alert("The end date should be after the start date. Please choose a valid end date");
    }

    console.log(sTime);
    console.log(eTime);
    console.log(sDT);
    console.log(eDT);
    console.log(startDateTime);
    console.log(endDateTime);
}