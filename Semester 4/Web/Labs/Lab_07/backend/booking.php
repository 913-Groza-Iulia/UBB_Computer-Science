<?php

require_once "./connection.php";

$roomsId = $_POST['room[]'];
$userName = $_POST['name'];
$check_in_date = $_POST['check_in_date'];
$check_out_date = $_POST['check_out_date'];

global $connection;

$sql = "INSERT INTO bookings(userName, check_in_date, check_out_date) values ('$userName', '$check_in_date', '$check_out_date')";
$result = mysqli_query($connection, $sql);

if ($result) {
    echo "Booking made successfully!";
} else {
    echo "Something went wrong. Please try again later.";
}
mysqli_close($connection);
