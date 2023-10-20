let curr_x;

function setX(value) {
    let select = document.getElementById("x_value");
    select.style.background = 'white';
    document.getElementById("x_value").value = value;
    curr_x = value;
}

document.getElementById("submitButton").addEventListener("click", function (event) {
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

    fetch("action.php", {
        method: "POST",
        body: formData
    })
        .then(response => response.json())
        .then(data => {
            addToTable(data.X, data.Y, data.R, data.Shot, data.Current_time, data.Lead_time);
        })
        .catch(error => {
            console.error("Error loading page / " + error);
        });

});
const result_table = document.getElementById("result")

function addToTable(x, y, r, result, exAt, leadTime) {
    let row = result_table.insertRow(1);
    let x_cell = row.insertCell(0);
    let y_cell = row.insertCell(1);
    let r_cell = row.insertCell(2);
    let res_cell = row.insertCell(3);
    let Current_time = row.insertCell(4);
    let leadTime_cell = row.insertCell(5);
    x_cell.innerHTML = x;
    y_cell.innerHTML = y;
    r_cell.innerHTML = r;
    res_cell.innerHTML = result;
    Current_time.innerHTML = exAt;
    leadTime_cell.innerHTML = leadTime;
}

document.getElementById("submitButton").disabled = true;

