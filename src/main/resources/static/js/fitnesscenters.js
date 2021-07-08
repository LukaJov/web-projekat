$(document).on('click', '.more', function () {

    window.localStorage.setItem('fitCenterId', this.dataset.id);
    window.location.href = "rooms.html";
    // ajax poziv za dobavljanje traženog zaposlenog sa backend-a i prikaz na stranici
   /* $.ajax({
        type: "GET",
        url: "/api/fitnesscenters/" + fitCenterId + "/rooms",
        dataType: "json",
        success: function (response) {
            console.log("SUCCESS:\n", response);

            for (let room of response) {
                let row = "<tr>";
                row += "<td>" + room.capacity + "</td>";
                row += "<td>" + room.label + "</td>";
                let btn = "<button class='delete' data-id=" + room.id + ">Delete</button>";
                row += "<td>" + btn + "</td>";
                row += "</tr>";
                btn = "<button class='change' data-id=" + room.id + ">Change</button>";
                row += "<td>" + btn + "</td>";
                row += "</tr>";


                $('#rooms').append(row);
            }

        },
        error: function (response) {
            console.log("ERROR:\n", response);
        }
    });*/
});



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
                let btn = "<button class='more' data-id=" + fitnesscenter.id + ">See more</button>";
                row += "<td>" + btn + "</td>";
                btn = "<button class='delete' data-id=" + fitnesscenter.id + ">Delete</button>";
                row += "<td>" + btn + "</td>";
                btn = "<button class='change' data-id=" + fitnesscenter.id + ">Change</button>";
                row += "<td>" + btn + "</td>";


                $('#fitnesscenters').append(row);
            }
        },
        error: function (response) {
            console.log("ERROR:\n", response);
        }
    });
});

$(document).on('click', '.delete', function () {


    let userType =  window.localStorage.getItem('role');
    let centerId = this.dataset.id;


    $.ajax({
        type: "DELETE",
        url: "http://localhost:8080/api/fitnesscenters/" + centerId +"?userType=" + userType,
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

