$(document).ready(function () {

    let userType = window.localStorage.getItem('role');
    let termId = window.localStorage.getItem('termId');
    let centerId = window.localStorage.getItem('fitCenterId');
    if(centerId==null)
    {
        centerId = 1;
    }
    if(userType!=1)
    {
        alert("Nedozvoljen pristup!");
        window.location.href = "index.html";
    }
    let id = window.localStorage.getItem('id');
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/" + centerId + "/terms/" + termId + "?userType=" + userType,
        dataType: "json",
        success: function (response) {
            console.log("SUCCESS:\n", response);
            let row = "<tr>";
            row += "<th>" + "Name" + "</th>";
            row += "<td>" + response.trainingDTO.name + "</td>";
            row += "</tr>";
            $('#term').append(row);
            row = "<tr>";
            row += "<th>" + "Description" + "</th>";
            row += "<td>" + response.trainingDTO.desc + "</td>";
            row += "</tr>";
            $('#term').append(row);
            row = "<tr>";
            row += "<th>" + "Training type" + "</th>";
            row += "<td>" + response.trainingDTO.trainingType + "</td>";
            row += "</tr>";
            $('#term').append(row);
            row = "<tr>";
            row += "<th>" + "Duration" + "</th>";
            row += "<td>" + response.trainingDTO.duration + "</td>";
            row += "</tr>";
            $('#term').append(row);
            row = "<tr>";
            row += "<th>" + "Date" + "</th>";
            row += "<td>" + response.date + "</td>";
            row += "</tr>";
            $('#term').append(row);
            row = "<tr>";
            row += "<th>" + "Price" + "</th>";
            row += "<td>" + response.price + "</td>";
            row += "</tr>";
            $('#term').append(row);
            row = "<tr>";
            row += "<th>" + "Empty spots" + "</th>";
            row += "<td>" + response.emptySpots + "</td>";
            row += "</tr>";
            $('#term').append(row);
            let btn = "<button class='green' data-id=" + response.id + ">Sign up</button>";
            if(response.emptySpots>0) {
                row = "<tr>";
                row += "<th>" + "</th>";
                row += "<td>" + btn + "</td>";
                row += "</tr>"
            }

            $('#term').append(row);


        },
        error: function (response) {
            console.log("ERROR:\n", response);
        }
    });
})