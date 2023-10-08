let curr_x;
function setX(value) {
    let select = document.getElementById("x_value");
    select.style.background = 'white';
    document.getElementById("x_value").value = value;
    curr_x = value;
}

document.getElementById("submitButton").addEventListener("click", function(event) {
    event.preventDefault();
    let time = new Date();
    time = time.getTimezoneOffset() / 60 * (-1);
    let x = document.getElementById("x_value").value;
    let y = document.getElementById("y_field").value;
    let r = document.getElementById("r_field").value;
    const formData = new FormData();
    formData.append("x_value", x);
    formData.append("y_field", y);
    formData.append("r_field", r);
    formData.append("time", time);

    const request = new XMLHttpRequest();
    const url = "action.php";
    request.open("POST", url);
    request.send(formData);

    request.onload = function () {
        document.getElementById("mainTB").innerHTML = request.responseText;
    }
});

document.getElementById("submitButton").disabled = true;