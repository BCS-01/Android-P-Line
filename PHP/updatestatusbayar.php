<?php
$hostname_localhost ="localhost";
$database_localhost ="p-line";
$username_localhost ="root";
$password_localhost ="";
$localhost = mysql_connect($hostname_localhost,$username_localhost,$password_localhost)
or
trigger_error(mysql_error(),E_USER_ERROR);
 
mysql_select_db($database_localhost, $localhost);
 
// query the application data
$id_user =  $_POST['id_user'];
$n_menu =  $_POST['n_menu'];
$ruko =  $_POST['ruko'];
$id_transaksi =  $_POST['id_transaksi'];
$result = "update transaksi set pembayaran = 'sudah' where id_user = '".$id_user. "' AND n_menu = '".$n_menu. "' AND id_ruko = '".$ruko. "' AND id_transaksi = '".$id_transaksi."'";
$query_exec = mysql_query($result) or die(mysql_error())
?>

