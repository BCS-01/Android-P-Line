<?php 

$db_con = mysqli_connect('localhost', 'root', '', 'android') or die ("connection error");
$username=$_POST[username];
$password=$_POST[password];
$query = mysqli_prepare($dbcon,"select status from user where username = ? and password = ?");
mysqli_stmt_bind_param($$query,"ss",$username,$password);
mysqli_stmt_execute($query);

mysqli_stmt_store_result$query);
mysqli_stmt_bind_result($query,$username,$password,$status);


while($row = mysqli_fetch_assoc($results)){
		$user=$row["status"]
}
echo json_encode($user);
mysqli_stmt_close($resutls);
mysqli_close($con);

?>
