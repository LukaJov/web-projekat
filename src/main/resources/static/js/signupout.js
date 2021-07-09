$(document).on('click', '.green', function () {

    // this je referenca na HTML element koji predstavlja kliknuto dugme See More
    // dataset je kolekcija svih custom data atributa datog HTML elementa iz koje uzimamo id
    // više o data atributima na: https://css-tricks.com/a-complete-guide-to-data-attributes/
    let termId = this.dataset.id;
    console.log(termId);
    let id = window.localStorage.getItem('id');
    let userType = window.localStorage.getItem('role');


    // ajax poziv za dobavljanje traženog zaposlenog sa backend-a i prikaz na stranici
    $.ajax({
        type: "PUT",
        url: "http://localhost:8080/api/terms/" + termId + "?upOrOut=true&userId=" + id + "&userType=" + userType,
        dataType: "json",
        /*contentType: "application/json",
        data: JSON.stringify(typeDTO),*/
        success: function (response) {
            console.log("SUCCESS:\n", response);
            //$('[data-id="' + termId + '"]').parent().parent().remove();
            window.location.href = "terms.html";
        },
        error: function (response) {
            console.log("ERROR:\n", response);
        }
    });
});

$(document).on('click', '.red', function () {

    // this je referenca na HTML element koji predstavlja kliknuto dugme See More
    // dataset je kolekcija svih custom data atributa datog HTML elementa iz koje uzimamo id
    // više o data atributima na: https://css-tricks.com/a-complete-guide-to-data-attributes/
    let termId = this.dataset.id;
    console.log(termId);
    let id = window.localStorage.getItem('id');
    let userType = window.localStorage.getItem('role');


    // ajax poziv za dobavljanje traženog zaposlenog sa backend-a i prikaz na stranici
    $.ajax({
        type: "PUT",
        url: "http://localhost:8080/api/terms/" + termId + "?upOrOut=false&userId=" + id + "&userType=" + userType,
        dataType: "json",
        /*contentType: "application/json",
        data: JSON.stringify(typeDTO),*/
        success: function (response) {
            console.log("SUCCESS:\n", response);
            $('[data-id="' + termId + '"]').parent().parent().remove();

        },
        error: function (response) {
            console.log("ERROR:\n", response);
        }
    });
});

