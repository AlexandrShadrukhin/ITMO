let curr_x;

function setX(value) {
    const xValueElement = document.getElementById("x_value");
    xValueElement.style.background = 'white';
    xValueElement.value = value;
    curr_x = value;
}

document.getElementById("submitButton").addEventListener("click", function(event) {
    event.preventDefault();

    const time = new Date().getTimezoneOffset() / 60 * (-1);

    const formData = new FormData();
    formData.append("x_value", document.getElementById("x_value").value);
    formData.append("y_field", document.getElementById("y_field").value);
    formData.append("r_field", document.getElementById("r_field").value);
    formData.append("time", time);

    fetch("action.php", {
        method: "POST",
        body: formData
    })
        .then(response => response.json())
        .then(data => {
            const fields = [
                document.getElementById("x_value").value,
                document.getElementById("y_field").value,
                document.getElementById("r_field").value,
                data.Shot,
                data.Current_time,
                data.Lead_time
            ];
            addToTable(fields);
        })
        .catch(error => {
            const errorElement = document.getElementById("error_message");
            errorElement.textContent = "Error loading page / " + error;
            errorElement.classList.add("errorElement");
        });
});

const result_table = document.getElementById("result");

function addToTable(rowData) {
    const row = result_table.insertRow(1);
    for (let i = 0; i < rowData.length; i++) {
        const cell = row.insertCell(i);
        cell.innerHTML = rowData[i];
    }
}

document.getElementById("submitButton").disabled = true;
