$(document).ready(function () {

    let userType = window.localStorage.getItem('role');
    if(userType!=1)
    {
        alert("Nedozvoljen pristup!");
        window.location.href = "index.html";
    }
    let id = window.localStorage.getItem('id');
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/users/" + id + "?userType=" + userType,
        dataType: "json",
        success: function (response) {
            console.log("SUCCESS:\n", response);
            let row = "<tr>";
            row += "<th>" + "Username" + "</th>";
            row += "<td>" + response.username + "</td>";
            row += "</tr>";
            $('#user').append(row);
            row = "<tr>";
            row += "<th>" + "Name" + "</th>";
            row += "<td>" + response.name + "</td>";
            row += "</tr>";
            $('#user').append(row);
            row = "<tr>";
            row += "<th>" + "Surname" + "</th>";
            row += "<td>" + response.surname + "</td>";
            row += "</tr>";
            $('#user').append(row);
            row = "<tr>";
            row += "<th>" + "Phonenumber" + "</th>";
            row += "<td>" + response.phoneNumber + "</td>";
            row += "</tr>";
            $('#user').append(row);
            row = "<tr>";
            row += "<th>" + "Email address" + "</th>";
            row += "<td>" + response.emailAddress + "</td>";
            row += "</tr>";
            $('#user').append(row);
            row = "<tr>";
            row += "<th>" + "Birthday" + "</th>";
            row += "<td>" + response.birthday + "</td>";
            row += "</tr>";
            $('#user').append(row);


        },
        error: function (response) {
            console.log("ERROR:\n", response);
        }
    });
})