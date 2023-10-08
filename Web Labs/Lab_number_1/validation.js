function blockingButton() {
    const yValid = validateY();
    const rValid = validateR();
    const submitButton = document.getElementById("submitButton");
    const errorMessage = document.getElementById("error-message");


    if (!yValid || !rValid) {
        submitButton.disabled = true;
        errorMessage.innerText = "Введенные данные некорректны!";
    } else {
        submitButton.disabled = false;
        errorMessage.innerText = "";
    }
}

function isNumeric(str) {
    if (typeof str != "string") return false;
    return !isNaN(str) && !isNaN(parseFloat(str));
}

function validateY() {
    let y = document.getElementById("y_field").value;
    y = y.replace(",", ".");
    document.getElementById("y_field").value = y;

    if (isNumeric(y)) {
        return y >= -3 && y <= 5;
    } else {
        return false;
    }
}

function validateR() {
    let r = document.getElementById("r_field").value;
    r = r.replace(",", ".");
    if (isNumeric(r)) {
        return r >= 2 && r <= 5;
    } else {
        return false;
    }
}

document.addEventListener("input", blockingButton);