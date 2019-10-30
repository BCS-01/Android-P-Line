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
$stok = $_POST['stok'];
$sql = "update menu_kantin set stok = '".$stok. "'  where id_ruko = '".$id_ruko. "' AND n_menu = '".$n_menu. "'";
$result = mysqli_query($con, $sql);
?>

