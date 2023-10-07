<?php
session_start();
$_SESSION['attempts'] = array();

header('Location: index.php');