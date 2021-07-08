$(document).on("click", "button", function (event) {
    event.preventDefault();

    $("#terms").find("tr:gt(0)").remove();


    let grd = $("#gradedUngraded").val();

    let id = window.localStorage.getItem('id');
    let userType = window.localStorage.getItem('role');

    var typedto = {
        id,
        userType
    }

    $.ajax({
            type: "POST",
            url: "http://localhost:8080/api/terms/" + grd,
            dataType: "json",
            contentType: "application/json",
            data: JSON.stringify(typedto),
            success: function (response) {
                console.log("SUCCESS:\n", response);

                for (let term of response) {
                    /*for(let grde of term.grades)
                    {
                        if (grde.givenBy.id === userId)
                        {
                            gradee = grde;
                        }
                    }*/

                    let row = "<tr>";
                    row += "<td>" + term.trainingDTO.name + "</td>";
                    row += "<td>" + term.trainingDTO.desc + "</td>";
                    row += "<td>" + term.trainingDTO.trainingType + "</td>";
                    row += "<td>" + term.trainingDTO.duration + "</td>";
                    row += "<td>" + term.date + "</td>";
                    row += "<td>" + term.price + "</td>";
                    if(grd=="doneungraded")
                    {
                        let btn = "<button class='givegrade' data-id=" + term.id + ">Give grade</button>";
                        row += "<td>" + btn + "</td>";
                    }
                    if(grd=="donegraded") {
                        row += "<td>" + term.grade + "</td>";
                    }

                    $('#terms').append(row);
                }
            },
            error: function (response) {
                console.log("ERROR:\n", response);
            }
        });
});

$(document).on('click', '.givegrade', function () {

    let userType = window.localStorage.getItem('role');
    let userId =  window.localStorage.getItem('id');
    let termId = this.dataset.id;

    window.localStorage.setItem('termId', termId);

    window.location.href ="givegrade.html";
});

$(document).ready(function () {
    var userType = window.localStorage.getItem('role');
    if (userType!= 1) {
        alert("Nedozvoljen pristup")
        window.location.href = "index.html";
    }
})