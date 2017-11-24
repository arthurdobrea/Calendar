$(document).ready(function(){
    $('#my_events').show();
    $('#events_invited').hide();
    $('#switch_events').prop('checked', false);
    $('#total_events_created').show();
    $('#total_events_invited').hide();
});

function create_event() {
    $(".add_event_modal").load("/index #AddEvent", function () {
        $("#AddEvent").modal();

        $("#datetimepicker1").datetimepicker({
            dayOfWeekStart: 1,
            closeOnDateSelect:true,
        });
        $("#datetimepicker2").datetimepicker({
            dayOfWeekStart: 1,
            closeOnDateSelect:true,
        });
    });
}

function edit_user() {
    $(".edit_user_modal").load("/userControlPanel #EditUser", function () {
        $("#EditUser").modal();
    });
}

function showMyEvents() {
    $('#my_events').show();
    $('#events_invited').hide();
    $('#total_events_created').show();
    $('#total_events_invited').hide();
}

function showEventsInvited() {
    $('#my_events').hide();
    $('#events_invited').show();
    $('#total_events_created').hide();
    $('#total_events_invited').show();
}


