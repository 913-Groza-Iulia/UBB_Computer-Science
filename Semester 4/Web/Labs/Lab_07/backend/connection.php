<?php

$server = "localhost";
$user = "root";
$password = "";
$database = "hotelroomsdb";

$connection = new mysqli($server, $user, $password, $database) or die("Unable to connect");
