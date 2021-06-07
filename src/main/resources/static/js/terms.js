$(document).ready(function () {    // Čeka se trenutak kada je DOM(Document Object Model) učitan da bi JS mogao sa njim da manipuliše.

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/terms",
        dataType: "json",
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
                row += "<td>" + term.price + "</td>";
                row += "</tr>";

                $('#terms').append(row);
            }
        },
        error: function (response) {
            console.log("ERROR:\n", response);
        }
    });
});

$(document).on("click", "button", function (event) {
    event.preventDefault();

    $("#terms tr").detach();

    let name = $("#name").value();
    let desc = $("#desc").value();
    let trainingType = $("#trainingType").value();
    let duration = $("#duration").value();
    let date = $("#date").value();
    let price = $("#price").value();
    let sortBy = $("#sortBy").value();
    let sortDir = $("#sortDir").value();
    //dodaj sort i query params u zahtev

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/terms",
        dataType: "json",
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
                row += "</tr>";

                $('#terms').append(row);
            }
        },
        error: function (response) {
            console.log("ERROR:\n", response);
        }
    });
});