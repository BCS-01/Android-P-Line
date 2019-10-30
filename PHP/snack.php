<?php
$host = "localhost"; // host of MySQL server
$user = "root"; // MySQL user
$pwd = ""; // MySQL user's password
$db = "p-line"; // database name
 
// Create connection
$con = mysqli_connect($host, $user, $pwd, $db);
 
// Check connection
if(mysqli_connect_errno($con)) {
    die("Failed to connect to MySQL: " . mysqli_connect_error());
}
 
// query the application data
$sql = "SELECT * FROM menu_kantin where jenis='snack'";
$result = mysqli_query($con, $sql);
 
// an array to save the application data
$rows = array();
 
// iterate to query result and add every rows into array
while($row = mysqli_fetch_array($result, MYSQLI_ASSOC)) {
    $rows[] = $row;
}
 
// close the database connection
mysqli_close($con);
 
// echo the application data in json format
echo json_encode($rows);

?>

