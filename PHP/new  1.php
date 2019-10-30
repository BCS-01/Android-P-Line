<?php
$hostname_localhost ="localhost";
$database_localhost ="p-line";
$username_localhost ="root";
$password_localhost ="";
$localhost = mysql_connect($hostname_localhost,$username_localhost,$password_localhost)
or
trigger_error(mysql_error(),E_USER_ERROR);
 
mysql_select_db($database_localhost, $localhost);

$result = "select * from pesanan where transaksi.id_transaksi = pesanan.id_transaksi";
$query_exec = mysql_query($result)
?>