<?php
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
