$(document).ready(function () {
    var userType = window.localStorage.getItem('role');
    if(userType!=3)
    {
        alert("Nedozvoljen pristup")
        window.location.href = "index.html";
    }
    var fitCenterId = window.localStorage.getItem('fitCenterId');
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/"+ fitCenterId + "/trainers/active",
        dataType: "json",
        success: function (response) {
            console.log("SUCCESS:\n", response);

            for (let trainer of response) {
                let row = "<tr>";
                row += "<td>" + trainer.name + "</td>";
                row += "<td>" + trainer.surname + "</td>";
                row += "<td>" + trainer.phoneNumber + "</td>";
                row += "<td>" + trainer.emailAddress + "</td>";
                row += "<td>" + trainer.birthday + "</td>";
                // kreiramo button i definisemo custom data atribut id = id zaposlenog
                let btn = "<button class='red' data-id=" + trainer.id + ">Remove</button>";
                row += "<td>" + btn + "</td>";

                $('#trainers').append(row);
            }
        },
        error: function (response) {
            console.log("ERROR:\n", response);
        }
    });
});

$(document).on("click", "#red", function (event) {
    var fitCenterId = window.localStorage.getItem('fitCenterId');
    var id = this.dataset.id;
    $.ajax({
        type: "DELETE",
        url: "http://localhost:8080/api/"+ fitCenterId + "/trainers/active/" + id,
        dataType: "json",
        success: function (response) {
            console.log("SUCCESS:\n", response);

            $('[data-id="' + id + '"]').parent().parent().remove();
            },

        error: function(response) {
            console.log("ERROR:\n", response);
        }
    });
})