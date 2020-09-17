<?php
$url = $_GET['url'];
require_once('connect.php');
$sql = "DELETE from assign_contr where id = '$url'";
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
