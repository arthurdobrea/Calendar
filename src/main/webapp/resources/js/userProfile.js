$(document).ready(function(){
    $('#my_events').show();
    $('#events_invited').hide();
    $('#switch_events').prop('checked', false);
    $('#total_events_created').show();
    $('#total_events_invited').hide();
});

function create_event() {
    $(".add_event_modal").load("/createEvent #AddEvent", function () {
        $("#AddEvent").modal();

        $("#datetimepicker1").datetimepicker({
            dayOfWeekStart: 1,
            closeOnDateSelect:true,
            step: 10,
        });
        $("#datetimepicker2").datetimepicker({
            dayOfWeekStart: 1,
            closeOnDateSelect:true,
            step: 10,
        });

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