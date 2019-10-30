<?php
$hostname_localhost ="localhost";
$database_localhost ="p-line";
$username_localhost ="root";
$password_localhost ="";
$localhost = mysql_connect($hostname_localhost,$username_localhost,$password_localhost)
or
trigger_error(mysql_error(),E_USER_ERROR);
 
mysql_select_db($database_localhost, $localhost);
 
$id_transaksi = $_POST['id_transaksi'];
$id_user = $_POST['id_user'];
$n_menu = $_POST['n_menu'];
$ruko = $_POST['id_ruko'];
$harga = $_POST['harga'];
$result = "INSERT INTO transaksi( id_transaksi,id_user,n_menu,id_ruko,harga) VALUES('".$id_transaksi."','".$id_user."','".$n_menu."','".$ruko."',".$harga.")";
$query_exec = mysql_query($result)
?>