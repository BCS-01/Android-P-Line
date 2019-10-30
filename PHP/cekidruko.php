<?php
$hostname_localhost ="localhost";
$database_localhost ="p-line";
$username_localhost ="root";
$password_localhost ="";
$localhost = mysql_connect($hostname_localhost,$username_localhost,$password_localhost)
or
trigger_error(mysql_error(),E_USER_ERROR);
 
mysql_select_db($database_localhost, $localhost);
 
$id_user = $_POST['id_user'];
$result = "select ruko from user where id_user = '".$id_user."'";
$query_exec = mysql_query($result) or die(mysql_error());
$rows = mysql_num_rows($query_exec);
//echo $rows;
while($row=mysql_fetch_row($query_exec)){
	echo "$row[0]";
}
?>