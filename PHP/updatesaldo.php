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
$saldo =  $_POST['saldo'];
$result = "update saldo set saldo = '".$saldo."' where id_user = '".$id_user. "'";
$query_exec = mysql_query($result) or die(mysql_error())
?>

