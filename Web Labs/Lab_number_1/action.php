<?php
session_start();

if (!isset($_SESSION['attempts'])) {
    $_SESSION['attempts'] = array();
}

$x = (int)$_POST["x_value"];
$y = (float)$_POST["y_field"];
$r = (float)$_POST["r_field"];
$time = (int)$_POST["time"];
$shot = "false";
$currTime = microtime(true) * 1000000;
$currDate = date("d-m-y H:i:s");
$time1 = microtime(true);

include 'serverValidation.php';

$isInputValid = validateInput($x, $y, $r, $time);

if (!$isInputValid) {
    http_response_code(400);
    die("Ошибка валидации");
}

$result = array(
    'X' => $x,
    'Y' => $y,
    'R' => $r,
    'Shot' => validate($x, $y, $r) ? "true" : "false",
    'Current_time' => date("H:i:s d-m-Y", time() + $time * 3600),
    'Lead_time' => number_format(microtime(true) * 1000000 - $currTime)
);

header("Content-Type: application/json");
echo json_encode($result);
exit();
