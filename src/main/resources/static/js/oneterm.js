$(document).on('click', '.more', function () {

    window.localStorage.setItem('termId', this.dataset.id);
    window.location.href = "term.html";


});
