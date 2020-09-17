<?php
$sts = $_GET['sts'];
$email = $_GET['id'];
require_once('connect.php');
$sql = "UPDATE citizen_data SET status = '$sts' where id = '$email'";
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
