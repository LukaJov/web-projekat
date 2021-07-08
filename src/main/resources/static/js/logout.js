$(document).on('click', '#logout', function () {
    event.preventDefault();
    window.localStorage.clear();
    window.location.href = "index.html";
});
