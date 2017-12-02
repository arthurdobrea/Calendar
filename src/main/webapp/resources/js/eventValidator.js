$(document).ready(function () {
    $("#datetimepicker1").datetimepicker({});
    $("#datetimepicker2").datetimepicker({});

    // $("#datepicker1").datepicker({});
    // $("#datepicker2").datepicker({});
});

function allDayChecked() {
    $("#timepicker1").prop('disabled', true);
    $("#timepicker1").prop('placeholder', '--:--');
    $("#timepicker2").prop('disabled', true);
    $("#timepicker2").prop('placeholder', '--:--');
}


function allDayUnchecked() {
    $("#timepicker1").prop('disabled', false);
    $("#timepicker1").prop('placeholder', 'HH:mm');
    $("#timepicker2").prop('disabled', false);
    $("#timepicker2").prop('placeholder', 'HH:mm');
}