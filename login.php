<?php

$servername = "localhost";
$username = "root";  
$password = "";      
$dbname = "baza"; 


$conn = new mysqli($servername, $username, $password, $dbname);


if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}
$rawPostData = file_get_contents("php://input");
parse_str($rawPostData, $_POST); 


file_put_contents("debug.txt", "RAW: $rawPostData\nPOST: " . print_r($_POST, true));
if (isset($_POST['login']) && isset($_POST['haslo'])) {
    $login = $_POST['login'];
    $haslo = $_POST['haslo'];

    
    $login = $conn->real_escape_string($login);
    $haslo = $conn->real_escape_string($haslo);

    
    $sql = "SELECT * FROM users WHERE Login = '$login' AND Haslo = '$haslo'";
    $result = $conn->query($sql);

    if ($result->num_rows > 0) {
        echo "success";
    } else {
        echo "failure";
    }
} else {
    echo "Error: Missing data";
}

$conn->close();
?>
