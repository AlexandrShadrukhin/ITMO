<?php

if (!isset($_SESSION['attempts'])) {
    $_SESSION['attempts'] = array();
}
$arr = $_SESSION['attempts'];
$len = count($arr);


foreach ($_SESSION['attempts'] as $iterator){
    echo "<tr>";
    foreach ($iterator as $val){
        echo "<th>";
        echo $val;
        echo "</th>";
    }
    echo "</tr>";
}