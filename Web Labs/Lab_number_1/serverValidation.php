<?php
function check_x($x): bool
{
    return $x >= -5 and $x <= 3;
}

function check_y($y): bool
{
    return $y >= -3 and $y <= 5;
}

function check_r($r): bool
{
    return $r >= 2 and $r <= 5;
}

function check_time($time): bool
{
    return $time >= 0 and $time <= 24;
}

function validateInput($x, $y, $r, $time): bool
{
    if (!(check_x($x) || check_y($y) || check_r($r) || check_time($time))) {
        http_response_code(400);
        die("Ошибка валидации");
    }
    return true;
}

function validate($x, $y, $r): bool
{
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
