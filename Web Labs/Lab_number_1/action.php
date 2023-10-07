<?php
session_start();

if (!isset($_SESSION['attempts'])) {
    $_SESSION['attempts'] = array();
}

$x = (int)$_POST["x_value"];
$y = (float)$_POST["y_field"];
$r = (int)$_POST["r_field"];
$time = (int)$_POST["time"];
$shot = "false";
$currTime = microtime(true) * 1000000;
$currDate = date("d-m-y H:i:s");
$time1 = microtime(true);
if ($x==0 && $y<=$r/2 && $y>=-$r){
    $shot = "true";
} elseif ($y==0 && $x<=$r && $x>=-$r){
    $shot = "true";
} elseif ($x<0 && $y>0){
    if($x>=-$r && $y<=$r/2){
        $shot = "true";
    }
}elseif ($x<0 && $y<0){
    if ($y>=(-2)*$x-$r){
        $shot = "true";
    }
}elseif ($x>0 && $y<0){
    if (sqrt($x*$x+$y*$y)<=$r){
        $shot = "true";
    }
}
$time2 = microtime(true);
$leadTime = microtime(true) * 1000000-$currTime;
$leadTime = number_format($leadTime);


$result = array(
    'X' => $x,
    'Y' => $y,
    'R' => $r,
    'Shot' => $shot,
    'Current time' => date("H:i:s d-m-Y",time()+$time*3600),
    'Lead time' => $leadTime
);
$_SESSION['attempts'][] = $result;

foreach ($_SESSION['attempts'] as $iterator){
    echo "<tr>";
    foreach ($iterator as $val){
        echo "<th>";
        echo $val;
        echo "</th>";
    }
    echo "</tr>";
}
exit();
