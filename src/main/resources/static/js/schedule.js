$(document).ready(function () {
    var fitCenterId = window.localStorage.getItem('fitCenterId');
    var id =window.localStorage.getItem('id');
    var userType = window.localStorage.getItem('role');
    if (userType != 2) {
        alert("Nedozvoljen pristup")
        window.location.href = "index.html";
    }

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/" + fitCenterId+ "/terms/my/" + id + "?userType=" + userType ,
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
                let btn = "<button class='delete' data-id=" + term.id + ">Delete</button>";
                row += "<td>" + btn + "</td>";
                row += "</tr>";

                $('#terms').append(row);
            }
        },
        error: function (response) {
            console.log("ERROR:\n", response);
        }
    });
});

$(document).on('click', '#add', function () {

    let centerId = window.localStorage.getItem('fitCenterId');
    let userType =  window.localStorage.getItem('role');
    if(userType!=2)
    {
        alert("Nedozvoljen pristup!");
        window.location.href = "index.html";
    }


    window.location.href ="addterm.html";


});

$(document).on('click', '.delete', function () {

    let centerId = window.localStorage.getItem('fitCenterId');
    let userType =  window.localStorage.getItem('role');
    if(userType!=2)
    {
        alert("Nedozvoljen pristup!");
        window.location.href = "index.html";
    }
    let termId = this.dataset.id;

    $.ajax({
        type: "DELETE",
        url: "http://localhost:8080/api/" + centerId + "/terms/" + termId +"?userType=" + userType,
        dataType: "json",
        success: function (response) {
            console.log("SUCCESS:\n", response);
            $('[data-id="' + centerId + '"]').parent().parent().remove();

        },
        error: function (response) {
            console.log("ERROR:\n", response);
        }
    });
});


