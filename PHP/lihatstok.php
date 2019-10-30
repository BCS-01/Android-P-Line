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
$id_ruko = $_POST['id_ruko'];
$n_menu = $_POST['n_menu'];
$sql = "SELECT stok FROM menu_kantin where id_ruko = '".$id_ruko. "' AND n_menu = '".$n_menu. "'";
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

