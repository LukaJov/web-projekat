/*$(document).ready(function () {    // Čeka se trenutak kada je DOM(Document Object Model) učitan da bi JS mogao sa njim da manipuliše.

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
});*/

$(document).on("click", "#search", function (event) {
    event.preventDefault();

    $("#terms").find("tr:gt(0)").remove();

    function encodeQueryData(data) {
        const ret = [];
        for (let d in data)
            ret.push(encodeURIComponent(d) + '=' + encodeURIComponent(data[d]));
        return ret.join('&');
    }
    let sortBy = $("#sortBy").val();
    let sortDir = $("#sortDir").val();


    let data = {trainingName:$("#trainingName").val(), trainingDesc:$("#trainingDesc").val(), trainingType: $("#trainingType").val(),
        date: $("#date").val(), price: $("#price").val()
    }

    //dodaj sort i query params u zahtev
    for(d in data)
    {
        if(!data[d])
        {
            delete data[d];
        }
    }

    let queryString = encodeQueryData(data);
    queryString += '&sort=' + sortBy + ',' + sortDir;

    let userType = window.localStorage.getItem('role');
    let id = window.localStorage.getItem('id');
    if(userType==1)
    {
        queryString += '&id=' + id + '&userType=' + userType;
    }

    let centerId = window.localStorage.getItem('fitCenterId');
    if(centerId==null)
    {
        centerId = 1;
    }

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/"+ centerId + "/terms" + "?" + queryString,
        dataType: "json",
        success: function (response) {
            console.log("SUCCESS:\n" + queryString, response);

            for (let term of response) {
                let row = "<tr>";
                row += "<td>" + term.trainingDTO.name + "</td>";
                row += "<td>" + term.trainingDTO.desc + "</td>";
                row += "<td>" + term.trainingDTO.trainingType + "</td>";
                row += "<td>" + term.trainingDTO.duration + "</td>";
                row += "<td>" + term.date + "</td>";
                row += "<td>" + term.price + "</td>";
                let btn = "<button class='more' data-id=" + term.id + ">See more</button>";
                if(window.localStorage.getItem('role')==1) {
                    row += "<td>" + btn + "</td>";
                }
                row += "</tr>";

                $('#terms').append(row);
            }
        },
        error: function (response) {
            console.log("ERROR:\n", response);
        }
    });
});

$(document).on("click", "#multi", function (event) {
    event.preventDefault();

    $("#terms").find("tr:gt(0)").remove();

    function encodeQueryData(data) {
        const ret = [];
        for (let d in data)
            ret.push(encodeURIComponent(d) + '=' + encodeURIComponent(data[d]));
        return ret.join('&');
    }
    let sortBy = $("#sortBy").val();
    let sortDir = $("#sortDir").val();


    let data = {trainingName:$("#trainingName").val(), trainingDesc:$("#trainingDesc").val(), trainingType: $("#trainingType").val(),
        date: $("#date").val(), price: $("#price").val()
    }

    //dodaj sort i query params u zahtev
    for(d in data)
    {
        if(!data[d])
        {
            delete data[d];
        }
    }

    let queryString = encodeQueryData(data);
    queryString += '&sort=' + sortBy + ',' + sortDir;

    let userType = window.localStorage.getItem('role');
    let id = window.localStorage.getItem('id');
    if(userType==1)
    {
        queryString += '&id=' + id + '&userType=' + userType;
    }
    let centerId = window.localStorage.getItem('fitCenterId');
    if(centerId==null)
    {
        centerId = 1;
    }

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/" + centerId + "/terms/multi" + "?" + queryString,
        dataType: "json",
        success: function (response) {
            console.log("SUCCESS:\n" + queryString, response);

            for (let term of response) {
                let row = "<tr>";
                row += "<td>" + term.trainingDTO.name + "</td>";
                row += "<td>" + term.trainingDTO.desc + "</td>";
                row += "<td>" + term.trainingDTO.trainingType + "</td>";
                row += "<td>" + term.trainingDTO.duration + "</td>";
                row += "<td>" + term.date + "</td>";
                row += "<td>" + term.price + "</td>";
                let btn = "<button class='more' data-id=" + term.id + ">See more</button>";
                if(window.localStorage.getItem('role')==1) {
                    row += "<td>" + btn + "</td>";
                }
                row += "</tr>";

                $('#terms').append(row);
            }
        },
        error: function (response) {
            console.log("ERROR:\n", response);
        }
    });
});