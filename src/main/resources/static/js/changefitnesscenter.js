$(document).ready(function () {

    let centerId = window.localStorage.getItem('fitCenterId');
    //let userType =  window.localStorage.getItem('role');


    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/fitnesscenters/" + centerId,
        dataType: "json",
        success: function (response) {
            console.log("SUCCESS:\n", response);
            document.getElementById("name").defaultValue = response.name;
            document.getElementById("address").defaultValue = response.address;
            document.getElementById("phoneNumber").defaultValue = response.phoneNumber;
            document.getElementById("emailAddress").defaultValue = response.emailAddress;
        },
        error: function (response) {
            console.log("ERROR:\n", response);
        }
    });
});




$(document).on('click', '.change', function () {
    event.preventDefault();


    let centerId = window.localStorage.getItem('fitCenterId');
    let name = $("#name").val();
    let address = $("#address").val();
    let phoneNumber = $("#phoneNumber").val();
    let emailAddress = $("#emailAddress").val();
    let userType = window.localStorage.getItem('role');

    let fitCenterDTO = {
        name,
        address,
        phoneNumber,
        emailAddress
    }

    $.ajax({
        type: "PUT",
        url: "http://localhost:8080/api/fitnesscenters/" + centerId + "?userType=" + userType,
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify(fitCenterDTO),
        success: function (response) {
            console.log("SUCCESS:\n", response);
            window.location.href = "fitnesscenters.html"

        },
        error: function (response) {
            console.log("ERROR:\n", response);
        }
    });
});