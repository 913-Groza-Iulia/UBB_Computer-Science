<?php
require_once "./connection.php";

$userName = $_POST['name'];
$check_in_date = $_POST['check_in_date'];
$check_out_date = $_POST['check_out_date'];

global $connection;

$sql = "DELETE FROM bookings WHERE userName = ? AND check_in_date = ? AND check_out_date = ?";
$stmt = $connection->prepare($sql);
$stmt->bind_param("sss", $userName, $check_in_date, $check_out_date);
$stmt->execute();

if ($stmt->affected_rows > 0) {
    echo "Booking cancelled successfully!";
} else {
    echo "No booking found with the provided details.";
}

$stmt->close();
$connection->close();
