let form = document.getElementById("form");
function openForm() {
    form.classList.add("open-form");
}
function closeForm() {
    form.classList.remove("open-form");

}
window.onclick = function(event) {
    if (event.target == form) {
        form.classList.remove("open-form");
    }
}