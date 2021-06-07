$(document).ready(function () {    // Čeka se trenutak kada je DOM(Document Object Model) učitan da bi JS mogao sa njim da manipuliše.

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/fitnesscenters",
        dataType: "json",
        success: function (response) {
            console.log("SUCCESS:\n", response);

            for (let fitnesscenter of response) {
                let row = "<tr>";
                row += "<td>" + fitnesscenter.name + "</td>";
                row += "<td>" + fitnesscenter.address + "</td>";
                row += "<td>" + fitnesscenter.phoneNumber + "</td>";
                row += "<td>" + fitnesscenter.emailAddress + "</td>";
                row += "</tr>";

                $('#fitnesscenters').append(row);
            }
        },
        error: function (response) {
            console.log("ERROR:\n", response);
        }
    });
});