<?php
function validateInput($x, $y, $r, $time) {
    $errors = array();

    if (!filter_var($x, FILTER_VALIDATE_INT) || $x < -5 || $x > 3) {
        $errors[] = "Неверное значение X";
    }

    if (!filter_var($y, FILTER_VALIDATE_FLOAT) || $y < -3 || $y > 5) {
        $errors[] = "Неверное значение Y";
    }

    if (!filter_var($r, FILTER_VALIDATE_FLOAT) || $r < 2 || $r > 5) {
        $errors[] = "Неверное значение R";
    }

    if (!filter_var($time, FILTER_VALIDATE_INT) || $time < 0 || $time > 24) {
        $errors[] = "Неверное значение Time";
    }

    if (!empty($errors)) {
        http_response_code(400);
        exit(implode(", ", $errors));
    }
}

function validate($x, $y, $r) {
    if ($x==0 && $y<=$r/2 && $y>=-$r){
        return true;
    } elseif ($y==0 && $x<=$r && $x>=-$r){
        return true;
    } elseif ($x<0 && $y>0){
        if($x>=-$r && $y<=$r/2){
            return true;
        }
    } elseif ($x<0 && $y<0){
        if ($y>=(-2)*$x-$r){
            return true;
        }
    } elseif ($x>0 && $y<0){
        if (sqrt($x*$x+$y*$y)<=$r){
            return true;
        }
    }
    return false;
}
