$(document).ready(function () {
    let id = window.localStorage.getItem('id');
let userType = window.localStorage.getItem('role');

var typedto = {
    id,
    userType
}

    $.ajax({
        type: "POST",
        url: "http://localhost:8080/api/terms/todo",
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify(typedto),
        success: function (response) {
            console.log("SUCCESS:\n", response);

            for (let term of response) {

                let row = "<tr>";
                row += "<td>" + term.trainingDTO.name + "</td>";
                row += "<td>" + term.trainingDTO.desc + "</td>";
                row += "<td>" + term.trainingDTO.trainingType + "</td>";
                row += "<td>" + term.trainingDTO.duration + "</td>";
                row += "<td>" + term.date + "</td>";
                row += "<td>" + term.price + "</td>";
                /*row += "<td>" + gradee + "</td>";*/
                row += "</tr>";

                $('#terms').append(row);
            }
        },
        error: function (response) {
            console.log("ERROR:\n", response);
        }});
})