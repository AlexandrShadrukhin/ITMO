<?php
session_start();
?>
<html lang="">
<head>
    <title>Моя лаба</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<script defer src="validation.js"></script>
<div class="container">
    <div class="header">
        <p>Шадрухин Александр P3225</p>
        <p>Вариант 3528</p>
    </div>
    <div class="image-container">
        <img width="350px" height="300px" src="diagramm.png" alt="Грустнааааа, но картинки не будет">
    </div>
    <div class="form-container">
        <div class="form-row">
            <label for="x_value" class="x-text">Выберите X: </label>
            <select id="x_value" onchange="setX(this.value)">
                <option value="-3">-3</option>
                <option value="-2">-2</option>
                <option value="-1">-1</option>
                <option value="0">0</option>
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
                <option value="5">5</option>
            </select>
        </div>
        <input type="hidden" id="x_value" name="x_value" value="-3">
        <div class="form-row">
            <h3>Y (от -3 до 5)</h3>
            <label for="y_field"></label><input type="text" name="Y" id="y_field" required maxlength="8">
        </div>
        <div class="form-row">
            <h3>R (от 2 до 5)</h3>
            <input type="text" name="R" id="r_field" required maxlength="8">
        </div>
        <div class="button-container">
            <button form="kekw" id="submitButton" onclick="send()">submit</button>
            <button type="submit" form="clear">clear</button>
        </div>
        <form id="clear" action="clear.php"></form>
        <script>
            let curr_x;
            function setX(value) {
                console.log(curr_x);
                let select = document.getElementById("x_value");
                select.style.background = 'white';
                document.getElementById("x_value").value = value;
                curr_x = value;
            }
        </script>
        <script>
            function send() {
                let time = new Date();
                time = time.getTimezoneOffset() / 60 * (-1);
                console.log(time);
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
                    console.log(request.responseText);
                    document.getElementById("mainTB").innerHTML = request.responseText;
                }
            }
        </script>
        <script>
            document.getElementById("submitButton").disabled = true;
        </script>
    </div>
    <div class="table-container">
        <table border='1' cellpadding='0' cellspacing='0' width='100%'>
            <tr>
                <th>X</th>
                <th>Y</th>
                <th>R</th>
                <th>Shot</th>
                <th>Current time</th>
                <th>Lead time(μs)</th>
            </tr>
            <tbody id="mainTB">
            <?php include 'table.php'; ?>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>