function blockingButton() {
    const y = validateY();
    const r = validateR();

    if (y === "notNumber" || y === "badNumber" || r === "badNumber") {
        console.log("notNumber or badNumber");
        document.getElementById("submitButton").disabled = true;
    } else {
        console.log("good");
        document.getElementById("submitButton").disabled = false;
    }
}

function validateY() {
    let y = document.getElementById("y_field").value;
    console.log(y);
    y = y.replace(",", ".");
    document.getElementById("y_field").value = y;

    console.log(parseFloat(y));
    if (y === parseFloat(y).toString()) {
        if (y > 5 || y < -3 || Number.isNaN(y)) {
            return "badNumber";
        } else {
            return "good";
        }
    } else {
        return "badNumber";
    }
}

function validateR() {
    let r = document.getElementById("r_field").value;
    console.log(r);
    r = r.replace(",", ".");
    if (r === parseFloat(r).toString()) {
        if (r < 2 || r > 5 || Number.isNaN(r)) {
            return "badNumber";
        } else {
            return "good";
        }
    } else {
        return "badNumber";
    }
}

document.addEventListener("input", blockingButton);
