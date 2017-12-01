$(document).ready(function () {
    $("#datetimepicker1").datetimepicker({
        dayOfWeekStart: 1,
        closeOnDateSelect:true,
    });
    $("#datetimepicker2").datetimepicker({
        dayOfWeekStart: 1,
        closeOnDateSelect:true,
    });
});

function allDayChecked() {
    $("#datetimepicker1").datetimepicker({
        timepicker: false,
        format: 'Y/m/d',
        dayOfWeekStart: 1,
        closeOnDateSelect:true,
    });
    $("#datetimepicker2").datetimepicker({
        timepicker: false,
        format: 'Y/m/d',
        dayOfWeekStart: 1,
        closeOnDateSelect:true,
    });
    document.getElementById('all-dayh').setAttribute('value', 'true');
}

function allDayUnchecked() {
    $("#datetimepicker1").datetimepicker({
        timepicker: true,
        format: 'Y/m/d H:i',
        dayOfWeekStart: 1,
        closeOnDateSelect:true,
    });
    $("#datetimepicker2").datetimepicker({
        timepicker: true,
        format: 'Y/m/d H:i',
        dayOfWeekStart: 1,
        closeOnDateSelect:true,
    });
    document.getElementById('all-dayh').setAttribute('value', 'false');
}

function eventDateTime() {
    var se = new Date(document.getElementById('datetimepicker1').value);
    var ee = new Date(document.getElementById('datetimepicker2').value);

    var startYear = se.getUTCFullYear();
    var startMonth = se.getUTCMonth() + 1;
    var startDate = se.getUTCDate();

    var endYear = ee.getUTCFullYear();
    var endMonth = ee.getUTCMonth() + 1;
    var endDate = ee.getUTCDate();

    var startHour = se.getUTCHours();
    var startMinute = se.getUTCMinutes();
    var endHour = ee.getUTCHours();
    var endMinute = ee.getUTCMinutes();

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

    var start = startYear + "-" + startMonth + "-" + startDate + "T" + startHour + ":" + startMinute + ":00.000";
    var end = endYear + "-" + endMonth + "-" + endDate + "T" + endHour + ":" + endMinute + ":00.000";

    document.getElementById('datetimepicker1h').setAttribute('value', start);
    document.getElementById('datetimepicker2h').setAttribute('value', end);
}





