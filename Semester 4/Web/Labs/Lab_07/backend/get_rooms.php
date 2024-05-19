<?php
require_once 'connection.php';

global $connection;

$sql = "SELECT roomName, roomCategory, roomHotel FROM rooms";
$result = mysqli_query($connection, $sql);

$rooms = array();
if ($result) {
    while ($row = mysqli_fetch_assoc($result)) {
        $rooms[] = $row;
    }
    mysqli_free_result($result);
} else {
    echo "Error executing query " . mysqli_error($connection);
}
mysqli_close($connection);

header('Content-Type: application/json');
echo json_encode($rooms);
