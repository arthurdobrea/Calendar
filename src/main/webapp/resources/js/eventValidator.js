//validation for the beginning of the event. startEvent must be after the current date and time
function eventStartValidation() {

    var se = new Date();
    var startYear = se.getFullYear();
    var startMonth = se.getMonth()+1;
    var startDate = se.getDate();
    var startHour = se.getHours();
    var startMinute = se.getMinutes();

    if(startMonth < 10) {
        startMonth = "0" + startMonth;
    }

    if(startDate < 10) {
        startDate = "0" + startDate;
    }

    if(startHour < 10) {
        startHour = "0" + startHour;
    }

    if(startMinute < 10) {
        startMinute = "0" + startMinute;
    }

    var startEvent = startYear + "-" + startMonth + "-" + startDate + "T" + startHour + ":" + startMinute + ":00";
    document.getElementById("eventStarts").setAttribute('min', startEvent);
    //document.getElementById("eventStarts").setAttribute('value', startEvent);
}

//validation for the end of the event. endEvent must be at least 10 minutes after the start of the event
function eventEndsValidation() {
    var end = document.getElementById("eventStarts").value;
    var ee = new Date(end);
    var endYear = ee.getFullYear();
    var endMonth = ee.getMonth()+1;
    var endDate = ee.getDate();
    var endHour = ee.getHours();
    var endMinute = ee.getMinutes()+10;

    if(endMonth < 10) {
        endMonth = "0" + endMonth;
    }

    if(endDate < 10) {
        endDate = "0" + endDate;
    }

    if(endHour < 10) {
        endHour = "0" + endHour;
    }

    if(endMinute < 10) {
        endMinute = "0" + endMinute;
    }

    var endEvent = endYear + "-" + endMonth + "-" + endDate + "T" + endHour + ":" + endMinute + ":00";
    document.getElementById("eventEnds").setAttribute('value', endEvent);
    document.getElementById("eventEnds").setAttribute('min', endEvent);
}



