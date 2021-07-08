$(document).ready(function () {

    let centerId = window.localStorage.getItem('fitCenterId');
    //let userType =  window.localStorage.getItem('role');
    let roomId = window.localStorage.getItem('roomId');


    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/fitnesscenters/" + centerId + "/rooms/"+ roomId,
        dataType: "json",
        success: function (response) {
            console.log("SUCCESS:\n", response);
           document.getElementById("capacity").defaultValue = response.capacity;
           document.getElementById("label").defaultValue = response.label;

        },
        error: function (response) {
            console.log("ERROR:\n", response);
        }
    });
});
$(document).on('click', '.change', function () {
    event.preventDefault();

   let id = window.localStorage.getItem('roomId');
    let centerId = window.localStorage.getItem('fitCenterId');
    let capacity = $("#capacity").val();
    let label = $("#label").val();
    let userType = window.localStorage.getItem('role');

    let roomDTO = {
        capacity,
        label
    }

    $.ajax({
        type: "PUT",
        url: "http://localhost:8080/api/fitnesscenters/" + centerId+ "/rooms/" + id + "?userType=" + userType,
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify(roomDTO),
        success: function (response) {
            console.log("SUCCESS:\n", response);
            window.location.href = "rooms.html"

        },
        error: function (response) {
            console.log("ERROR:\n", response);
        }
    });
});
