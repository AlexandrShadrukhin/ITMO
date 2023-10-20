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

validateInput($x, $y, $r, $time);

$shot = validate($x, $y, $r) ? "true" : "false";

$time2 = microtime(true);
$leadTime = microtime(true) * 1000000 - $currTime;
$leadTime = number_format($leadTime);
$Current_Time = date("H:i:s d-m-Y", time() + $time * 3600);

$result = array(
    'X' => $x,
    'Y' => $y,
    'R' => $r,
    'Shot' => $shot,
    'Current_time' => $Current_Time,
    'Lead_time' => $leadTime
);

header("Content-Type: application/json");
echo json_encode($result);
exit();
