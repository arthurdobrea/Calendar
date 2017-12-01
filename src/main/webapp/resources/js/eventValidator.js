$(document).ready(function () {
    $("#datetimepicker1").datetimepicker({
        dayOfWeekStart: 1,
        closeOnDateSelect:false,
    });
    $("#datetimepicker2").datetimepicker({
        dayOfWeekStart: 1,
        closeOnDateSelect:false,
    });
});

// function allDayChecked() {
//     $("#datetimepicker1").datetimepicker({
//         timepicker: false,
//         dayOfWeekStart: 1,
//         closeOnDateSelect:true,
//         value: defaultDate,
//         format: 'Y/m/d',
//     });
//     $("#datetimepicker2").datetimepicker({
//         timepicker: false,
//         dayOfWeekStart: 1,
//         closeOnDateSelect:true,
//         value: defaultDate,
//         format: 'Y/m/d',
//     });
// }
//
//
// function allDayUnchecked() {
//     $("#datetimepicker1").datetimepicker({
//         timepicker: true,
//         dayOfWeekStart: 1,
//         closeOnDateSelect:true,
//         value: defaultDate,
//         format: 'Y/m/d H:i',
//     });
//     $("#datetimepicker2").datetimepicker({
//         timepicker: true,
//         dayOfWeekStart: 1,
//         closeOnDateSelect:true,
//         value: defaultDate,
//         format: 'Y/m/d H:i',
//     });
// }