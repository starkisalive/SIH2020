<?php
$url = $_GET['url'];
$budget = $_GET['budget'];
$ch = $_GET['choice'];
require_once('connect.php');
$sql = "UPDATE assign_contr SET budget = '$budget', choice = '$ch' where id = '$url'";
$r = mysqli_query($conn,$sql);
$result = array();
if($r){
	$result['status'] = "Sucess";
	echo json_encode($result);
}else{

	$result['status'] = "Unsucess";
	echo json_encode($result);
}
mysqli_close($conn);
?>
