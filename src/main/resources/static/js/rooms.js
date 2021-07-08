
$(document).ready(function () {
     var fitCenterId = window.localStorage.getItem('fitCenterId');

        var userType = window.localStorage.getItem('role');
        if (userType!= 3) {
            alert("Nedozvoljen pristup")
            window.location.href = "index.html";
        }


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

$(document).on('click', '.delete', function () {

    let centerId = window.localStorage.getItem('fitCenterId');
    let userType =  window.localStorage.getItem('role');
    let roomId = this.dataset.id;


    $.ajax({
        type: "DELETE",
        url: "http://localhost:8080/api/fitnesscenters/" + centerId + "/rooms/"+ roomId +"?userType=" + userType,
        dataType: "json",
        success: function (response) {
            console.log("SUCCESS:\n", response);
            $('[data-id="' + roomId + '"]').parent().parent().remove();

        },
        error: function (response) {
            console.log("ERROR:\n", response);
        }
    });
});

$(document).on('click', '.change', function () {

    let centerId = window.localStorage.getItem('fitCenterId');
    //let userType =  window.localStorage.getItem('role');
    let roomId = this.dataset.id;

    window.localStorage.setItem('roomId', roomId);

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/fitnesscenters/" + centerId + "/rooms/"+ roomId,
        dataType: "json",
        success: function (response) {
            console.log("SUCCESS:\n", response);
            window.location.href ="changeroom.html";

        },
        error: function (response) {
            console.log("ERROR:\n", response);
        }
    });
});
