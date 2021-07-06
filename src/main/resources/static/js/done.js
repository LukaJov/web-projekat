$(document).on("click", "button", function (event) {
    event.preventDefault();

    $("#terms").find("tr:gt(0)").remove();


    let grd = $("#gradedUngraded").val();

    let userId = window.localStorage.getItem('userId');



    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/terms/" + grd,
        dataType: "json",
        success: function (response) {
            console.log("SUCCESS:\n" + queryString, response);

            for (let term of response) {
                for(let grde of term.grades)
                {
                    if (grde.givenBy.id == userId)
                    {
                        gradee = grde;
                    }
                }

                let row = "<tr>";
                row += "<td>" + term.training.name + "</td>";
                row += "<td>" + term.training.desc + "</td>";
                row += "<td>" + term.training.trainingType + "</td>";
                row += "<td>" + term.training.duration + "</td>";
                row += "<td>" + term.date + "</td>";
                row += "<td>" + term.price + "</td>";
                row += "<td>" + gradee + "</td>";
                row += "</tr>";

                $('#terms').append(row);
            }
        },
        error: function (response) {
            console.log("ERROR:\n", response);
        }
    });
});