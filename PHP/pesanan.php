<?php
$hostname_localhost ="localhost";
$database_localhost ="p-line";
$username_localhost ="root";
$password_localhost ="";
$localhost = mysql_connect($hostname_localhost,$username_localhost,$password_localhost)
or
trigger_error(mysql_error(),E_USER_ERROR);
 
mysql_select_db($database_localhost, $localhost);
 
$id_pesanan = $_POST['id_pesanan'];
$transaksi = $_POST['id_transaksi'];
$meja = $_POST['meja'];
$id_ruko = $_POST['id_ruko'];
$result = "INSERT INTO pesanan ( id_pesanan,id_transaksi,meja,id_ruko) VALUES('".$id_pesanan."','".$transaksi."','".$meja."','".$id_ruko."')";
$query_exec = mysql_query($result)
?>