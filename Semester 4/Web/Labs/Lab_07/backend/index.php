<?php
require_once './connection.php';

global $connection;

$items_per_page = isset($_GET['items_per_page']) ? intval($_GET['items_per_page']) : 4;
$page = isset($_GET['page']) ? intval($_GET['page']) : 1;
$offset = ($page - 1) * $items_per_page;

$sql = "SELECT * FROM rooms LIMIT $items_per_page OFFSET $offset";
$result = mysqli_query($connection, $sql);

if ($result) {
    $rooms = array();
    while ($row = mysqli_fetch_assoc($result)) {
        array_push($rooms, array(
            'roomId' => $row['roomId'],
            'roomName' => $row['roomName'],
            'roomDescription' => $row['roomDescription'],
            'roomPrice' => $row['roomPrice'],
            'roomCategory' => $row['roomCategory'],
            'roomHotel' => $row['roomHotel'],
        ));
    }
    mysqli_free_result($result);

    $count_result = mysqli_query($connection, "SELECT COUNT(*) as total FROM rooms");
    $count_row = mysqli_fetch_assoc($count_result);
    $total_items = $count_row['total'];
    mysqli_free_result($count_result);

    echo json_encode([
        'rooms' => $rooms,
        'total_items' => $total_items,
        'items_per_page' => $items_per_page,
        'current_page' => $page
    ]);
} else {
    echo json_encode(["error" => "Error executing query " . mysqli_error($connection)]);
}

mysqli_close($connection);
