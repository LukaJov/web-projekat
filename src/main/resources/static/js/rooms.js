
$(document).ready(function () {
     var fitCenterId = window.localStorage.getItem('fitCenterId');

    $.ajax({
        type: "GET",
        url: "/api/fitnesscenters/" + fitCenterId + "/rooms",
        dataType: "json",
        success: function (response) {
            console.log("SUCCESS:\n", response);

            for (let room of response) {
                let row = "<tr>";
                row += "<td>" + room.capacity + "</td>";
                row += "<td>" + room.label + "</td>";
                let btn = "<button class='delete' data-id=" + room.id + ">Delete</button>";
                row += "<td>" + btn + "</td>";
                btn = "<button class='change' data-id=" + room.id + ">Change</button>";
                row += "<td>" + btn + "</td>";


                $('#rooms').append(row);
            }

        },
        error: function (response) {
            console.log("ERROR:\n", response);
        }
    });
})